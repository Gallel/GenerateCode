from src.CodeGenerator import CodeGenerator

class GenerateCodeAndTests(CodeGenerator):
	def __init__(self, model, text, folderName):
		super().__init__(folderName)

		template = """You are a code generator and validator with tests. Given the text of what the client want, it is your job to write the code that complies with that you have been asked.
		Don't generate comments, only the code required following the instructions. You have to declare every attribute needed correctly. The code has to be tested with your own tests, covering all the possible cases.
		But don't show the code of tests, just write which of them are passed and which of them are failed. If a test failed, write the reason in its input. If the message received is not complete, just resend it again.
		Generate: {text}
		Instructions for you are written inside parenthesis. Your answers always has to follow this pattern, you always have to use the tags:
			<code>
			(write the code here)
			</code>

			<tests>
				<test id="1" result="OK" case="(write what this test do here)" expected="(write what the test expect here)" result="(write what the test obtain as a result here)" reason=""/>
				<test id="2" result="FAILED" case="(write what this test do here)" expected="(write what the test expect here)" result="(write what the test obtain as a result here)" reason="(write the reason here)"/>
			</tests>
		"""
  
		super().BindModel(model)
		super().BindTemplate(template)
		super().BindText(text)
		super().BindOutputFile("GenerateCodeAndTests.java")
		super().IncludeTests()
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()

	def Generate(self):
		super().RequestCode()
		super().SaveAnswer()
