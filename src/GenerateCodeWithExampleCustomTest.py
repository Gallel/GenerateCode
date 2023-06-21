from src.CodeGeneratorExample import CodeGeneratorExample

class GenerateCodeWithExampleCustomTest(CodeGeneratorExample):
	def __init__(self, model, text, tests, example, folderName, isActivePrompting):
		text += " The tests that you need to pass are determined by the following code: " + tests + "\n"
  
		super().__init__(model, text, example, "GenerateCodeWithCustomTests.txt", "GenerateCodeWithExampleCustomTest.java", True, folderName, isActivePrompting)
