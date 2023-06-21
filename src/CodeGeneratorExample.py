from src.CodeGenerator import CodeGenerator

class CodeGeneratorExample(CodeGenerator):
	def __init__(self, model, text, example, prompt, outputFile, hasTests, folderName, isActivePrompting):
		super().__init__(folderName)
		
		super().BindModel(model)
		super().LoadPrompt(prompt)
		super().BindText(text)
		super().BindOutputFile(outputFile)
		if hasTests:
			super().IncludeTests()
		super().LoadPromptExample("FollowExample.txt")
		super().BindExample(example)
  
		if isActivePrompting:
			super().EnableActivePrompting()
  
		if folderName is not None:
			super().SaveInfo()

		super().LoadConfig()
