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

startTime = datetime.now()

def LoadJavadoc(filePath):
	print("Loading javadoc...")
	reader = ReadJavadoc(filePath)
	text = reader.constructClass()
	print("Elapsed time: " + str(datetime.now() - startTime))
 
	return text

def LoadTests(testFilePath):
	print("Loading tests...")
	with open(testFilePath, "r") as file:
		text = file.read()
	print("Elapsed time: " + str(datetime.now() - startTime))
 
	return text

def CodeWithoutTests(model, text, folderName = None):
	print("Generating code without tests...")
	generator = GenerateCodeWithoutTests(model, text, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))

def CodeWithCustomTests(model, text, tests, folderName = None):
	print("Generating code with custom tests...")
	generator = GenerateCodeWithCustomTests(model, text, tests, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))

def CodeAndTests(model, text, folderName = None):
	print("Generating code with own tests...")
	generator = GenerateCodeAndTests(model, text, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))

def CodeWithExample(model, text, example, folderName = None):
	print("Generating code with example...")
	generator = GenerateCodeWithExample(model, text, example, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))

def CodeWithExampleCustomTests(model, text, tests, example, folderName = None):
	print("Generating code with example and custom tests...")
	generator = GenerateCodeWithExampleCustomTest(model, text, tests, example, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))
 
def CodeWithExampleTests(model, text, example, folderName = None):
	print("Generating code with example and own tests...")
	generator = GenerateCodeWithExampleAndTest(model, text, example, folderName)
	generator.Generate()
	print("Elapsed time: " + str(datetime.now() - startTime))

def SleepTime(meanWaitTime, timeToRemove):
	realWaitTime = random.randint(meanWaitTime - 5, meanWaitTime + 5)
	print("Wait " + str(realWaitTime) + " seconds to continue...");
	time.sleep(realWaitTime)
 
	return timeToRemove + realWaitTime

if __name__ == '__main__':
    # Load .env file
	load_dotenv()
	
	model = os.getenv('OPENAI_MODEL')
	basePath = r"D:/Escritorio/UOC/Semestre 9 (2022 - 2)/1 - TFG/Material/DSLib-master/"
	filePath = basePath + r"docs/edu/uoc/ds/adt/sequential/QueueArrayImpl.html"
	testFilePath = basePath + r"src/test/java/edu/uoc/ds/adt/sequential/QueueArrayTest.java"
	example = "queue"
	
	timesToIterate = 10
	waitTime = 30
 
	arg = "all"
	validArgs = ["all", "1", "2", "3", "times", "example1", "example2", "example3"]
	
	if (len(sys.argv) > 1):
		arg = sys.argv[1]
  
	if arg not in validArgs:
		raise Exception("[ERROR] Invalid argument!")
  
	text = LoadJavadoc(filePath)
	if testFilePath != "":
		tests = LoadTests(testFilePath)
 
	timeToRemove = 0
 
	if arg == "1":
		CodeWithoutTests(model, text)
	elif arg == "2":
		CodeWithCustomTests(model, text, tests)
	elif arg == "3":
		CodeAndTests(model, text)
	elif arg == "times":
		for i in range(timesToIterate):
			# Generate folders
			folderName = "./output/attempt_" + str(i+1)
			if os.path.exists(folderName):
				shutil.rmtree(folderName)
			os.makedirs(folderName);
   
			# Generate code
			CodeWithoutTests(model, text, folderName)
			timeToRemove = SleepTime(waitTime, timeToRemove)
			CodeWithCustomTests(model, text, tests, folderName)
			timeToRemove = SleepTime(waitTime, timeToRemove)
			CodeAndTests(model, text, folderName)

			if i != timesToIterate - 1:
				timeToRemove = SleepTime(waitTime, timeToRemove)
	elif arg == "example1":
		CodeWithExample(model, text, example)
	elif arg == "example2":
		CodeWithExampleCustomTests(model, text, tests, example)
	elif arg == "example3":
		CodeWithExampleTests(model, text, example)
	else:
		CodeWithoutTests(model, text)
		timeToRemove = SleepTime(waitTime, timeToRemove)
		CodeWithCustomTests(model, text, tests)
		timeToRemove = SleepTime(waitTime, timeToRemove)
		CodeAndTests(model, text)
	
	totalTime = datetime.now() - startTime
	delta = timedelta(seconds = timeToRemove)
	realTime = totalTime - delta
 
	if timeToRemove == 0:
		print("Total elapsed time: " + str(totalTime))
	else:
		print("Total elapsed time: " + str(totalTime) + " (" + str(timeToRemove) + " seconds waiting) -> Real time: " + str(realTime))
