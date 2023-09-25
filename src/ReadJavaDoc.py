import os
import codecs

# Read HTML
from bs4 import BeautifulSoup

class ReadJavadoc:
	def __init__(self, classPath, exceptionsPath, utilPath):     
		with codecs.open(classPath, "r", encoding="utf-8") as file:
			html_content = file.read()
   
		soup = BeautifulSoup(html_content, 'html.parser')

		## Classname
		classDescription = soup.find(id = "class-description")
		if classDescription is None:
			raise Exception("[ERROR] Cannot find class-description!!")
		soupClassDescription = BeautifulSoup(str(classDescription), 'html.parser')
		classTypeSignature = soupClassDescription.find(class_ = "type-signature")
		if classTypeSignature is None:
			raise Exception("[ERROR] Cannot find class-description type-signature!!")
		classBlock = soupClassDescription.find(class_ = "block")
		if classBlock is None:
			raise Exception("[ERROR] Cannot find class-description block!!")

		## Constructors
		constructors = []
  
		classConstructors = soup.find(id = "constructor-summary")
		if classConstructors is None:
			raise Exception("[ERROR] Cannot find constructor-summary")
		soupConstructors = BeautifulSoup(str(classConstructors), 'html.parser')

		classConstructorsName = soupConstructors.find_all(class_ = "col-constructor-name")
		classConstructorDescription = soupConstructors.find_all(class_ = "col-last")
  
		if len(classConstructorsName) > 0:
			# Remove first element of description (not a real description)
			classConstructorDescription.pop(0)
			
			if len(classConstructorsName) != len(classConstructorDescription):
				raise Exception("[ERROR] Different number of names and descriptions!!")

			for constructorName in classConstructorsName:
				constructors.append({ "name": constructorName.get_text(), "description": "" })
	
			for i, constructorDescription in enumerate(classConstructorDescription):
				constructors[i]["description"] = constructorDescription.get_text()
  
		# Methods
		methods = []
  
		classMethods = soup.find(id = "method-summary-table.tabpanel")
		if classMethods is None:
				raise Exception("[ERROR] Cannot find method-summary-table.tabpanel")
		soupMethods = BeautifulSoup(str(classMethods), 'html.parser')
  
		classMethodType = soupMethods.find_all(class_ = "col-first")
		classMethodName = soupMethods.find_all(class_ = "col-second")
		classMethodDescription = soupMethods.find_all(class_ = "col-last")
  
		if len(classMethodType) > 1:
			# Remove first element of every search (not a real method)
			classMethodType.pop(0)
			classMethodName.pop(0)
			classMethodDescription.pop(0)
   
			if len(classMethodType) != len(classMethodName) or len(classMethodType) != len(classMethodDescription):
				raise Exception("[ERROR] Different number of types, names and descriptions!!")

			for functionType in classMethodType:
				methods.append({ "type": functionType.get_text(), "name": "", "description": "" })

			for i, methodName in enumerate(classMethodName):
				methods[i]["name"] = methodName.get_text()
    
			for i, methodDescription in enumerate(classMethodDescription):
				methods[i]["description"] = methodDescription.get_text()
  
		self.classType = classTypeSignature.get_text()
		self.classDescription = classBlock.get_text()
		self.constructors = constructors
		self.methods = methods

		# Exceptions
		exceptions = os.listdir(exceptionsPath)
		self.exceptions = [os.path.splitext(exception)[0] for exception in exceptions if os.path.isfile(os.path.join(exceptionsPath, exception))]
  
		# Utils
		utils = os.listdir(utilPath)
		self.utils = [os.path.splitext(util)[0] for util in utils if os.path.isfile(os.path.join(utilPath, util))]
  
	def constructClass(self):
		classText = self.classDescription + "\n" + self.classType + "\n\n"
  
		for constructor in self.constructors:
			classText += constructor["description"] + constructor["name"] + "\n\n"
  
		for method in self.methods:
			classText += method["description"] + method["type"] + " " + method["name"] + "\n\n"

		if len(self.exceptions) > 0:
			classText += "List of custom exceptions: " + ', '.join(self.exceptions) + '.\n'

		if len(self.exceptions) > 0:
			classText += "List of util classes: " + ', '.join(self.utils) + '.\n'
     
		return classText
