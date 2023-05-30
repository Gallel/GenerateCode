from src.CodeGenerator import CodeGenerator

class GenerateCodeAndTests(CodeGenerator):
	def __init__(self, model, text, folderName):
		super().__init__(folderName)
  
		super().BindModel(model)
		super().LoadPrompt("GenerateCodeAndTests.txt")
		super().BindText(text)
		super().BindOutputFile("GenerateCodeAndTests.java")
		super().IncludeTests()
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()

	def Generate(self):
		super().RequestCode()
		super().SaveAnswer()
