## Overview

The purpose of this utility is to make the data of Vehicle, ECU Family and Component Template from P2 documents available into DDAT

## Technology stack

* RestAssured + TestNG + Java

## Pre-requisite

Before running this utility, make sure to supply the data in appropriate format

## Instructions to be followed
1. Launch DDAT application using login credentials
2. Place the parsed P2 document in "testdata" folder (This parsed document must be in .xlsx format)
3. Navigate to config folder and open "config.properties" file using any text editor (eg. Notepad)
4. After opening "config.properties" file, change the name of filepath and sheetname parameters according to parsed P2
	(eg. filepath=./testdata/Write.xlsx , here we have to just replace "Write.xlsx" with the name of file being copied to "testdata" folder and so on with sheet name parameter)
5. Double click "data-migration.bat" file for starting the utility
==================================================================================

NOTE: check for logs of execution from auto-generated "logfile" for debugging errors and issues