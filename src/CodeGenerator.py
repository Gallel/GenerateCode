import os
import re
from datetime import datetime

#ChatGPT
from langchain.chat_models import ChatOpenAI
from langchain.chains import LLMChain
from langchain.prompts import PromptTemplate
#from langchain.chains import SimpleSequentialChain

class CodeGenerator:

	def __init__(self, folderName):
		self.answer_chain = None
		self.answer = None

		self.model = None
		self.template = None
		self.text = None
		self.outputFile = None
		self.hasTests = False
		self.saveInfo = False
		self.startTime = datetime.now()

		if folderName is not None:
			self.outputFolder = folderName + "/"
		else:
			self.outputFolder = "./output/"
  
	def BindModel(self, model):
		self.model = model
  
	def BindTemplate(self, template):
		self.template = template
  
	def BindText(self, text):
		self.text = text
  
	def BindOutputFile(self, path):
		self.outputFile = path
  
	def IncludeTests(self):
		self.hasTests = True
  
	def SaveInfo(self):
		self.saveInfo = True

	def LoadConfig(self):
		if self.model is None:
			raise Exception("[ERROR] No model name found!!")

		if self.template is None:
			raise Exception("[ERROR] No template found!!")

		# Create an llm
		llm = ChatOpenAI(openai_api_key = os.getenv('OPENAI_API_KEY'), model_name = self.model, temperature = 0)
		prompt_template = PromptTemplate(input_variables=["text"], template = self.template)
		self.answer_chain = LLMChain(llm = llm, prompt = prompt_template)
	
	def RequestCode(self):
		if self.text is None:
			raise Exception("[ERROR] No text found!!")
     
		self.answer = self.answer_chain.run(self.text)
	
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
