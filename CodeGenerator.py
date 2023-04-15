import os
import codecs
from dotenv import load_dotenv

#ChatGPT
from langchain.llms import OpenAI
from langchain.chains import LLMChain
from langchain.prompts import PromptTemplate
from langchain.chains import SimpleSequentialChain

class CodeGenerator:

	def __init__(self):
		self.api_key = None
		self.answer_chain = None
		self.answer = None

		self.LoadConfig()

	def LoadConfig(self):
		# Load .env file
		load_dotenv()

		# Get the key
		self.api_key = os.getenv('OPENAI_API_KEY')

		# Create an llm
		llm = OpenAI(temperature=0)
		template = """You are a code generator and validator with tests. Given the text of what the client want, it is your job to write the code that complies with that you have been asked.
		Don't generate comments, only the code required. Include the libraries used in the code too. The code has to be tested with your own tests, covering all the possible cases.
		But don't show the code of tests, just write which of them are passed and which of them are failed. If a test failed, write the reason in its input. If the message received is not complete, just resend it again.
		Generate: {text}
		Instructions for you are written inside parenthesis. Your answers alway has to follow this pattern:
			<code>
			(write the code here)
			</code>

			<test id="1" result="OK" case="(write what this test do here)" expected="(write what the test expect here)" result="(write what the test obtain as a result here)" reason=""/>
			<test id="2" result="FAILED" case="(write what this test do here)" expected="(write what the test expect here)" result="(write what the test obtain as a result here)" reason="(write the reason here)"/>
		"""
		prompt_template = PromptTemplate(input_variables=["text"], template=template)
		self.answer_chain = LLMChain(llm=llm, prompt=prompt_template)
	
	def ReadMessage(self):
		with codecs.open("./message.txt", "r", encoding="utf-8") as file:
			message = file.read()
		self.answer = self.answer_chain.run(message)
	
	def PrintLastAnswer(self):
		print(self.answer)

if __name__ == '__main__':
	generator = CodeGenerator()
	generator.ReadMessage()
	generator.PrintLastAnswer()
