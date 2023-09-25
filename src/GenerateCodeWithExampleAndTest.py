from src.CodeGeneratorExample import CodeGeneratorExample

class GenerateCodeWithExampleAndTest(CodeGeneratorExample):
	def __init__(self, model, text, package, example, folderName, isActivePrompting):
		super().__init__(model, text, package, example, "GenerateCodeAndTests.txt", "GenerateCodeWithExampleAndTest.java", True, folderName, isActivePrompting)
		super().Generate()
