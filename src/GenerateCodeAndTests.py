from src.CodeGenerator import CodeGenerator

class GenerateCodeAndTests(CodeGenerator):
	def __init__(self, model, text, folderName, isActivePrompting):
		super().__init__(folderName)
  
		super().BindModel(model)
		super().LoadPrompt("GenerateCodeAndTests.txt")
		super().BindText(text)
		super().BindOutputFile("GenerateCodeAndTests.java")
		super().IncludeTests()
  
		if isActivePrompting:
			super().EnableActivePrompting()
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()
