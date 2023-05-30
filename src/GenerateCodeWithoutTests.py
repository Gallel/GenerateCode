from src.CodeGenerator import CodeGenerator

class GenerateCodeWithoutTests(CodeGenerator):
	def __init__(self, model, text, folderName):
		super().__init__(folderName)
  
		super().BindModel(model)
		super().LoadPrompt("GenerateCodeWithoutTests.txt")
		super().BindText(text)
		super().BindOutputFile("GenerateCodeWithoutTests.java")
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()

	def Generate(self):
		super().RequestCode()
		super().SaveAnswer()
