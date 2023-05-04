from src.CodeGenerator import CodeGenerator

class GenerateCodeWithoutTests(CodeGenerator):
	def __init__(self, model, text, folderName):
		super().__init__(folderName)

		template = """You are a code generator. Given the text of what the client want, it is your job to write the code that complies with that you have been asked.
		Don't generate comments, only the code required following the instructions. You have to declare every attribute needed correctly. If the message received is not complete, just resend it again.
		Generate: {text}
		Instructions for you are written inside parenthesis. Your answers always has to follow this pattern, you always have to use the tags:
			<code>
			(write the code here)
			</code>
		"""
  
		super().BindModel(model)
		super().BindTemplate(template)
		super().BindText(text)
		super().BindOutputFile("GenerateCodeWithoutTests.java")
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()

	def Generate(self):
		super().RequestCode()
		super().SaveAnswer()
