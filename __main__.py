import os
import sys
import time
import random
import shutil
from dotenv import load_dotenv
from datetime import datetime, timedelta

from src.GenerateCodeWithoutTests import GenerateCodeWithoutTests
from src.GenerateCodeWithCustomTests import GenerateCodeWithCustomTests
from src.GenerateCodeAndTests import GenerateCodeAndTests
from src.GenerateCodeWithExample import GenerateCodeWithExample
from src.GenerateCodeWithExampleCustomTest import GenerateCodeWithExampleCustomTest
from src.GenerateCodeWithExampleAndTest import GenerateCodeWithExampleAndTest

from src.ReadJavaDoc import ReadJavadoc
from src.CodeCompiler import CodeCompiler

startTime = datetime.now()

def LoadJavadoc(filePath, exceptionsPath, utilPath):
	print("[INFO] Loading javadoc...")
	reader = ReadJavadoc(filePath, exceptionsPath, utilPath)
	text = reader.constructClass()
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))
 
	return text

def LoadTests(testFilePath):
	print("[INFO] Loading tests...")
	with open(testFilePath, "r") as file:
		text = file.read()
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))
 
	return text

def CodeWithoutTests(model, text, package, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code without tests...")
	GenerateCodeWithoutTests(model, text, package, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))

def CodeWithCustomTests(model, text, package, tests, compiler, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code with custom tests...")
	GenerateCodeWithCustomTests(model, text, package, tests, compiler, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))

def CodeAndTests(model, text, package, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code with own tests...")
	GenerateCodeAndTests(model, text, package, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))

def CodeWithExample(model, text, package, example, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code with example...")
	GenerateCodeWithExample(model, text, package, example, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))

def CodeWithExampleCustomTests(model, text, package, tests, example, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code with example and custom tests...")
	GenerateCodeWithExampleCustomTest(model, text, package, tests, example, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))
 
def CodeWithExampleTests(model, text, package, example, folderName = None, isActivePrompting = False):
	print("[INFO] Generating code with example and own tests...")
	GenerateCodeWithExampleAndTest(model, text, package, example, folderName, isActivePrompting)
	print("[INFO] Elapsed time: " + str(datetime.now() - startTime))

def SleepTime(meanWaitTime, timeToRemove):
	realWaitTime = random.randint(meanWaitTime - 5, meanWaitTime + 5)
	print("[INFO] Wait " + str(realWaitTime) + " seconds to continue...");
	time.sleep(realWaitTime)
 
	return timeToRemove + realWaitTime

if __name__ == '__main__':
    # Load .env file
	load_dotenv()
	
	model = os.getenv('OPENAI_MODEL')
	basePath = r"D:/Escritorio/UOC/GEI/Semestre 9 (2022 - 2)/1 - TFG/Material/DSLib-master/"
	tmpDirectory = r"tmp"
	filePath = basePath + r"docs/edu/uoc/ds/adt/sequential/QueueArrayImpl.html"
	testFilePath = basePath + r"src/test/java/edu/uoc/ds/adt/sequential/QueueArrayTest.java"
	exceptionsPath = basePath + r"src/main/java/edu/uoc/ds/exceptions"
	utilPath = basePath + r"src/main/java/edu/uoc/ds/traversal"
	projectPathfile = r"/src/main/java/edu/uoc/ds/adt/sequential"
	realFilename = "QueueArrayImpl.java"
	testClassname = "edu.uoc.ds.adt.sequential.QueueArrayTest"
	package = "edu.uoc.ds.adt.sequential"
 
	# Depracated
	example = "queue"
	
	timesToIterate = 1
	waitTime = 30
 
	arg = "all"
	isActivePrompting = False
	validArgs = ["all", "1", "2", "3", "times", "example1", "example2", "example3"]
	
	# Preconditions
	if (len(sys.argv) > 1):
		arg = sys.argv[1]
  
	if (len(sys.argv) > 2):
		isActivePrompting = sys.argv[2] == "active"
  
	if arg not in validArgs:
		raise Exception("[ERROR] Invalid argument!")
	# End of preconditions

	# Load javadoc
	text = LoadJavadoc(filePath, exceptionsPath, utilPath)
	if testFilePath != "":
		tests = LoadTests(testFilePath)
	# End of load javadoc

	# Start compiler
	codeCompiler = CodeCompiler(basePath, tmpDirectory, projectPathfile, realFilename, testClassname)
	codeCompiler.CopyProject()
	# End of compiler
 
	timeToRemove = 0
 
	if arg == "1":
		CodeWithoutTests(model, text, package, None, isActivePrompting)
	elif arg == "2":
		CodeWithCustomTests(model, text, package, tests, codeCompiler, None, isActivePrompting)
	elif arg == "3":
		CodeAndTests(model, text, package, None, isActivePrompting)
	elif arg == "times":
		for i in range(timesToIterate):
			# Generate folders
			folderName = "./output/attempt_" + str(i+1)
			if os.path.exists(folderName):
				shutil.rmtree(folderName)
			os.makedirs(folderName);
   
			# Generate code
			CodeWithoutTests(model, text, package, folderName)
			timeToRemove = SleepTime(waitTime, timeToRemove)
			CodeWithCustomTests(model, text, package, tests, codeCompiler, folderName)
			timeToRemove = SleepTime(waitTime, timeToRemove)
			CodeAndTests(model, text, package, folderName)

			if i != timesToIterate - 1:
				timeToRemove = SleepTime(waitTime, timeToRemove)
	elif arg == "example1":
		CodeWithExample(model, text, package, example, None, isActivePrompting)
	elif arg == "example2":
		CodeWithExampleCustomTests(model, text, package, tests, example, None, isActivePrompting)
	elif arg == "example3":
		CodeWithExampleTests(model, text, package, example, None, isActivePrompting)
	else:
		CodeWithoutTests(model, text, package)
		timeToRemove = SleepTime(waitTime, timeToRemove)
		CodeWithCustomTests(model, text, package, tests, codeCompiler)
		timeToRemove = SleepTime(waitTime, timeToRemove)
		CodeAndTests(model, text, package)
	
	totalTime = datetime.now() - startTime
	delta = timedelta(seconds = timeToRemove)
	realTime = totalTime - delta
 
	if timeToRemove == 0:
		print("[INFO] Total elapsed time: " + str(totalTime))
	else:
		print("[INFO] Total elapsed time: " + str(totalTime) + " (" + str(timeToRemove) + " seconds waiting) -> Real time: " + str(realTime))
