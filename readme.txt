This is a key-value data store implemented using java

In this data-store the user will be able to create a file path of his choice ( optional ) and 
will be able to write a key(string) value(JSON Object) pair (the key and value are mapped to each other) into it.

If the user calls the init function, the data-store will be persistent i.e. even after the program is closed and restarted the 
data stored in the file will not be erased i.e. the mapping of the data will be preserved.

The data-store has main-class : Freshworks
The functions present in the datastore are :

	1. init ( String ) 
	 	The String we are passing in this function is our optional file path.
	 	If we don't pass anything, the function will use the default file path. 
		This function is used to read data from the file and to convert the data into a map, so that the file will not be over-written and the data in it will persist.
     
     DEFAULT PATH = "D:\\new.txt"   
     
	2. createJSON(String, String, JSONObject)
		We pass the optional file path, key, and JSONObject as parameters.
		If we don't pass the file path, the function will use the default file path.
		This function is used to create a key(String) value(JSON object) pair.
		It throws a custom exception when
			i) we are trying to create a key which is already present in the data store.
			ii) the key has more than 32 characters.
			iii) size of JSON object is more than 16kB.

	3. readJSON(String, String)
		We pass the optional file path and key.
		If we don't pass the file path, the function will use the default file path.
		This function returns the JSON object (value) for a given key
		It throws custom exception when, the file doesn't contain the key we want to read.

	4. deleteJSON(String, String)
		We pass the optional file path and key.
		If we don't pass the file path, the function will use the default file path.
		This function is used to delete the JSON object (value) corresponding to a given key (String).
		It throws custom exception when, the file doesn't contain the key we want to read.

	5. writeInFile(String)
		This is the private function.
		This gets invoked whenever the user does create or delete operation.
		This function writes the map in the file.
		It throws custom exception when, the file size if greater than 1GB.
		
	6. getDefaultFilePath()
		User can get the default file path using this function.

I have used ConcurrentHashMap, which is thread-safe.

The user must call the "init" function before performing any other operations, else the file will be over-written or other problems will occur.

I have also done the "JUnit test".

I have also included a Test.java file which I used for testing.

This is a maven project so a jar file can be created.

Use 'assembly:single' in 'goals' while 'maven build'.