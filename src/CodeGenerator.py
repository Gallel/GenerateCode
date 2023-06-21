import os
import re
from datetime import datetime

#ChatGPT
from langchain.chat_models import ChatOpenAI
from langchain.chains import LLMChain
from langchain.prompts import PromptTemplate
from langchain.chains import SimpleSequentialChain

# GuardRails
#import guardrails as gd

class CodeGenerator:

	def __init__(self, folderName):
		self.answer_chain = None
		self.answer_chain2 = None
		self.answer = None

		self.model = None
		self.template = None
		self.templateExample = None
		self.text = None
		self.example = None
		self.outputFile = None
		self.hasTests = False
		self.saveInfo = False
		self.startTime = datetime.now()
		self.activePrompting = False

		if folderName is not None:
			self.outputFolder = folderName + "/"
		else:
			self.outputFolder = "./output/"
  
	def BindModel(self, model):
		self.model = model

	def LoadPrompt(self, file):
		try:
			with open("./prompt/" + file, 'r') as file:
				self.template = file.read()
		except FileNotFoundError:
			raise Exception("[ERROR] Cannot find prompt file at path ./prompt/" + file + "!!")
  
	def LoadPromptExample(self, file):
		try:
			with open("./prompt/" + file, 'r') as file:
				self.templateExample = file.read()
		except FileNotFoundError:
			raise Exception("[ERROR] Cannot find prompt file at path ./prompt/" + file + "!!")
  
	def BindText(self, text):
		self.text = text
  
	def BindExample(self, example):
		self.example = example
  
	def BindOutputFile(self, path):
		self.outputFile = path
  
	def IncludeTests(self):
		self.hasTests = True
  
	def EnableActivePrompting(self):
		self.activePrompting = True
  
	def SaveInfo(self):
		self.saveInfo = True
  
	def HasExampleChain(self):
		return self.example != None and self.templateExample != None

	def LoadConfig(self):
		if self.model is None:
			raise Exception("[ERROR] No model name found!!")

		if self.template is None:
			raise Exception("[ERROR] No template found!!")

		# Create an llm
		llm = ChatOpenAI(openai_api_key = os.getenv('OPENAI_API_KEY'), model_name = self.model, temperature = 0)
		prompt_template = PromptTemplate(input_variables=["text"], template = self.template)
		self.answer_chain = LLMChain(llm = llm, prompt = prompt_template)
  
		# Create second chain
		if self.HasExampleChain():
			prompt_template2 = PromptTemplate(input_variables=["example"], template = self.templateExample)
			self.answer_chain2 = LLMChain(llm = llm, prompt = prompt_template2)
	
	def RequestCode(self):
		if self.text is None:
			raise Exception("[ERROR] No text found!!")
     
		if self.HasExampleChain():
			chain = SimpleSequentialChain(chains = [self.answer_chain, self.answer_chain2])
			self.answer = chain.run(input = {"text": self.text, "example": self.example})
		else:
			self.answer = self.answer_chain.run(self.text)
   
		isOk = False
   
		while self.activePrompting and not isOk:
			self.SaveAnswer()
			print("Temporary code saved at " + self.outputFolder + self.outputFile)
			userFeedback = input('Please check the generated code and provide additional information to regenerate it. If you consider it to be correct, please write "OK": ')
			if userFeedback == 'OK':
				isOk = True
			else:
				llm = ChatOpenAI(openai_api_key = os.getenv('OPENAI_API_KEY'), model_name = self.model, temperature = 0)
				prompt_template = PromptTemplate(input_variables=["text"], template = self.template)
				self.answer_chain = LLMChain(llm = llm, prompt = prompt_template)
    
				text = "Fix this code:\n " + self.answer + "\nAccording to this client feedback: " + userFeedback
    
				self.answer = self.answer_chain.run(text)
	
	def PrintLastAnswer(self):
		print(self.answer)
  
	def SaveAnswer(self):
		if self.outputFolder is None:
			raise Exception("[ERROR] Output folder non exists!!")
     
		if self.outputFile is None:
			raise Exception("[ERROR] Output file non exists!!")

		fullPath = self.outputFolder + self.outputFile
  
		if self.saveInfo == True and fullPath.endswith(".java"):
			fullPathInfo = fullPath[:-len(".java")] + "_info.txt"
  
		start_index = self.answer.find("<code>")
		end_index = self.answer.find("</code>")
  
		if start_index == -1 or end_index == -1:
			raise Exception("[ERROR] Cannot find <code> tag!!")

		start_index += len("<code>")
		code = self.answer[start_index:end_index]
  
		if self.hasTests == True:
			start_index = self.answer.find("<tests>")
			end_index = self.answer.find("</tests>")
			if start_index == -1 or end_index == -1:
				raise Exception("[ERROR] Cannot find <tests> tag!!")
			start_index += len("<tests>")
			tests = self.answer[start_index:end_index]
			
			# Remove tabs
			tests = tests.replace("\t", "")
			# Remove extra spaces
			tests = re.sub(r'>[\n ]+<', ">\n<", tests)
			tests = tests.strip()
     
		with open(fullPath, 'w') as file:
			file.write(code)
			if self.hasTests == True:
				print("=========================")
				print("========= TESTS =========")
				print("=========================")
				print(tests)
    
		if self.saveInfo == True:
			with open(fullPathInfo, "w") as file:
				file.write("<time value=\"" + (str(datetime.now() - self.startTime)) + "\"/>\n")
				if self.hasTests:
					file.write(tests + "\n")

	def Generate(self):
		self.RequestCode()
		self.SaveAnswer()
