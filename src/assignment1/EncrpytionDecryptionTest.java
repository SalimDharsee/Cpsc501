package assignment1;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncrpytionDecryptionTest {

	// Tested if the decryptFile class would throw an exception when given illegal arguments 
	@Test (expected = IllegalArgumentException.class)
	public void testNoArgumentsDecryption() {
		
		decryptFile aDecrypt = new decryptFile();
		decryptFile.checkArgs(null);
	}

	// Tested if the decryptFile class would throw an exception when given illegal arguments 
	@Test (expected = IllegalArgumentException.class)
	public void testOneArugmentsDecryption(){
		
		String[] testStrings = {"hello"};
		decryptFile aDecrypt = new decryptFile();
		aDecrypt.checkArgs(testStrings);
		
	}	
	
	// Tested if the decryptFile class would run correctly given proper arguments 
	@Test 
	public void testTwoArgumentsDecrpytion(){
		
		String [] testStrings = {"hello", "world"};
		decryptFile aDecrypt = new decryptFile();
		aDecrypt.checkArgs(testStrings);
		
	}
	
	// Tested if the secureFile class would throw an exception when given illegal arguments 
	@Test (expected = IllegalArgumentException.class)
	public void testNoArgumentsEncryption(){
		
		secureFile aSecure = new secureFile();
		aSecure.checkArgs(null);
		
	}
	
	// Tested if the secureFile class would throw an exception when given illegal arguments 
	@Test (expected = IllegalArgumentException.class)
	public void testOneArgumentsEncrpytion(){
		
		String[] testStrings = {"hello"};
		secureFile aSecure = new secureFile();
		aSecure.checkArgs(testStrings);
		
	}
	
	// Tested if the secureFile class would run correctly given proper arguments 
	public void testTwoArgumentsEncrpytion(){
		
		String[] testStrings = {"hello", "World"};
		secureFile aSecure = new secureFile();
		aSecure.checkArgs(testStrings);
		
	}

}
