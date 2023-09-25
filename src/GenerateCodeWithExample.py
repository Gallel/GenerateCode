from src.CodeGeneratorExample import CodeGeneratorExample

class GenerateCodeWithExample(CodeGeneratorExample):
	def __init__(self, model, text, package, example, folderName, isActivePrompting):
		super().__init__(model, text, package, example, "GenerateCodeWithoutTests.txt", "GenerateCodeWithExample.java", False, folderName, isActivePrompting)
		super().Generate()
