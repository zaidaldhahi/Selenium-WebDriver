# Selenium-WebDriver

Overview:
*********
*This project is based on the class exercises given by Dr. John Robb - the University of Texas at Arlington.
*This project is implementing Selenium WebDriver testing (UI testing) using http://www.adactin.com/HotelApp/ as a testing website.

Project structure:
******************
This project contains 3 main folders:
1- src folder, contains 3 folders:
  a- Excel: which contains the test case table in .csv extension. This file is used as an input for some of the Java test cases
     in "tests" folder.
  b- functions: contains the code of the functions which are called in some of the Java test cases in "tests" folder.
  c- tests: contains the actual Java testing code.

2- SharedUIMap: contains the file .properties that has web elements locations according to their xpaths or IDs. The xpaths, IDs are
   saved in variables named according to the page name where they exist and their functions. These variables are called in the Java 
   tests codes during the test.

3- Configuration: contains the .properties file that has the URL of the website which you want to test and the URL of the SharedUIMap.
