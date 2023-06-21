from src.CodeGeneratorExample import CodeGeneratorExample

class GenerateCodeWithExample(CodeGeneratorExample):
	def __init__(self, model, text, example, folderName, isActivePrompting):
		super().__init__(model, text, example, "GenerateCodeWithoutTests.txt", "GenerateCodeWithExample.java", False, folderName, isActivePrompting)
