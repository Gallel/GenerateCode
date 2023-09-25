from src.CodeGenerator import CodeGenerator

class GenerateCodeAndTests(CodeGenerator):
	def __init__(self, model, text, package, folderName, isActivePrompting):
		super().__init__(folderName)
  
		super().BindModel(model)
		super().LoadPrompt("GenerateCodeAndTests.txt")
		super().BindText(text)
		super().BindPackage(package)
		super().BindOutputFile("GenerateCodeAndTests.java")
		super().IncludeTests()
  
		if isActivePrompting:
			super().EnableActivePrompting()
  
		if folderName is not None:
			super().SaveInfo()
  
		super().LoadConfig()
		super().Generate()
