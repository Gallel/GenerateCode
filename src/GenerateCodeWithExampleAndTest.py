from src.CodeGeneratorExample import CodeGeneratorExample

class GenerateCodeWithExampleAndTest(CodeGeneratorExample):
	def __init__(self, model, text, example, folderName):
		super().__init__(model, text, example, "GenerateCodeAndTests.txt", "GenerateCodeWithExampleAndTest.java", True, folderName)
