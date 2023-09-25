import os
import shutil
import subprocess

import xml.etree.ElementTree as ET

class CodeCompiler():
	def __init__(self, projectDirectory, tmpDirectory, pathfile, filename, classname):
		self.projectDirectory = projectDirectory
		self.tmpDirectory = os.path.abspath(tmpDirectory)
		self.pathfile = tmpDirectory + pathfile
		self.filename = filename
		self.classname = classname

	def bindGenerateFile(self, outputFile):
		self.outputFile = outputFile
    
	def GenerateDirectory(self):
		if os.path.exists(self.tmpDirectory):
			try:
				shutil.rmtree(self.tmpDirectory)
				print(f"[INFO] Removing recursively at folder path {os.path.abspath(self.tmpDirectory)}...")
			except Exception as ex:
				raise Exception(f"[ERROR] Cannot remove recursively at folder path {os.path.abspath(self.tmpDirectory)}: {ex}")

		try:
			os.makedirs(self.tmpDirectory)
		except Exception as ex:
			print(f"[ERROR] Cannot create folder at path {os.path.abspath(self.tmpDirectory)}: {ex}")
			
	def CopyProject(self):
		if not os.path.exists(self.projectDirectory):
			raise Exception(f"[ERORR] Source folder path {self.projectDirectory} does not exist!!")
		
		self.GenerateDirectory()
		
		print(f"[INFO] Copying all the files...")
		
		for item in os.listdir(self.projectDirectory):
			src_item = os.path.join(self.projectDirectory, item)
			dst_item = os.path.join(self.tmpDirectory, item)

			if os.path.isdir(src_item):
				shutil.copytree(src_item, dst_item)
			else:
				shutil.copy2(src_item, dst_item)

		print(f"[INFO] All files copyied from {self.projectDirectory} to {self.tmpDirectory}")
		
	def ReplaceFile(self):
		if self.outputFile is None:
			raise Exception("[ERROR] outputFile does not exist!! Set it with the binding function before!!")
     
		filepath = os.path.join(self.pathfile, self.filename)
		
		if os.path.exists(filepath):
			try:
				os.remove(filepath)
				print(f"[INFO] File {self.filename} deleted successfully from {self.pathfile}.")
			except Exception as e:
				print(f"[ERROR] Could not delete {self.filename} from {self.pathfile}!! Reason: {e}")
		else:
			print(f"[ERROR] File {self.filename} does not exist in {self.pathfile}!!")
		
		try:
			shutil.copy2(self.outputFile, self.pathfile + "/" + self.filename)
			print(f"[INFO] File copied successfully from {self.outputFile} to {self.pathfile}/{self.filename}.")
		except Exception as e:
			print(f"[ERROR] Could not copy file from {self.outputFile} to {self.pathfile}/{self.filename}. Reason: {e}")

	def CompileProject(self):
		if not os.path.exists(self.tmpDirectory):
			raise Exception(f"[ERORR] Folder path {self.tmpDirectory} does not exist!!")
		
		try:
			os.chdir(self.tmpDirectory)
			print(f"[INFO] Compiling the project at path {os.getcwd()}...")
			subprocess.run("mvn clean install -DskipTests", check = True, shell = True)
			print(f"[INFO] Project compiled successfully at path {os.getcwd()}")
		except subprocess.CalledProcessError:
			print(f"[ERROR] Cannot compile the project at path: {os.getcwd()}!!")

	def TestClassname(self):
		if not os.path.exists(self.tmpDirectory):
			raise Exception(f"[ERORR] Folder path {self.tmpDirectory} does not exist!!")
		
		try:
			os.chdir(self.tmpDirectory)
			print(f"[INFO] Starting tests for class {self.classname}")
			subprocess.run(f"mvn test -Dtest={self.classname}", check = True, shell = True)
			print(f"[INFO] Project compiled successfully at path {os.getcwd()}")
		except subprocess.CalledProcessError:
			print(f"[ERROR] Cannot execute tests for class {self.classname} at path {os.getcwd()}!!")

	def ShowResults(self):
		report_path = os.path.join(self.tmpDirectory, "target", "surefire-reports", f"TEST-{self.classname}.xml")
		if os.path.exists(report_path):
			tree = ET.parse(report_path)
			root = tree.getroot()

			total_tests = root.get("tests")
			total_failures = root.get("failures")
			total_errors = root.get("errors")

			print(f"Total tests: {total_tests}")
			print(f"Total failures: {total_failures}")
			print(f"Total errors: {total_errors}")

			for testcase in root.findall("testcase"):
				class_name = testcase.get("classname")
				test_name = testcase.get("name")
				time_taken = testcase.get("time")
				print(f"Test: {test_name} in {class_name} took {time_taken} seconds")

				failure = testcase.find("failure")
				if failure is not None:
					print(f"Test {test_name} failed with message: {failure.get('message')}")

		else:
			print(f"[ERROR] Report file not found at {report_path}")
