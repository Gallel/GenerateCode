from src.CodeGenerator import CodeGenerator

class GenerateCodeWithCustomTests(CodeGenerator):
	def __init__(self, model, text, tests, folderName):
		super().__init__(folderName)
  
		text += " The tests that you need to pass are determined by the following code: " + tests + "\n"
  
		super().BindModel(model)
		super().LoadPrompt("GenerateCodeWithCustomTests.txt")
		super().BindText(text)
		super().BindOutputFile("GenerateCodeWithCustomTests.java")
		super().IncludeTests()

		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()

	def Generate(self):
		super().RequestCode()
		super().SaveAnswer()
