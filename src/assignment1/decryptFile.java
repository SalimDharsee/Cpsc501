package assignment1;
/*
 * Salim Dharsee, ID# 10062458
 * CPSC 501 Fall 2015
 * Assignment 1
 */
import java.io.*;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import java.security.interfaces.*;
import java.math.*;
import java.security.SecureRandom;


public class decryptFile{
	
	private static SecretKeySpec sec_key_spec = null;
	private static Cipher sec_cipher = null;

	public static void main(String args[]) throws Exception{
		FileInputStream in_file = null;
		FileInputStream in_file2 = null;
		FileOutputStream out_file = null;
		FileOutputStream out_file2 = null;
	
		String decrypted_str = new String();
		int read_bytes = 0;

		String in_seed = null;
		
		byte[] seedByteDec = null;
		try{
			//grab command lines arguments 
			in_seed = (args[1]);
			in_file = new FileInputStream(args[0]);
			//decrypt file
			// opening the file input and output streams to write and read files 
			out_file2 = new FileOutputStream("decrypted");
			in_file2 = new FileInputStream(args[0]);
			byte[] decmsg = new byte[in_file.available()];
			//getting the seed form command line 
			read_bytes = in_file.read(decmsg);
			seedByteDec = in_seed.getBytes();
			byte [] seedArrayDec = new byte[16];
			//decrypt file with AES
			//key setup - generate 128 bit key
			//using the seed given in the command line to generate the key 
			randomSeed(new RandomSeed(seedByteDec, seedArrayDec));

			//create the cipher object that uses AES as the algorithm
			sec_cipher = Cipher.getInstance("AES");	

			//do AES decryption
			decrypted_str = decrypt_input(decmsg);
			
			out_file2.write(decrypted_str.getBytes());
			System.out.println("decrypted: " + decrypted_str);
			

	}
	catch(Exception e){
		System.out.println(e);
	}
	finally{
		if (in_file != null){
			in_file.close();
		}
		if(out_file != null){
			out_file.close();
		}
		if(in_file2 != null){
			in_file2.close();
		}
	}
}
	private static void randomSeed(RandomSeed parameterObject)
			throws NoSuchAlgorithmException {
		SecureRandom randDec = SecureRandom.getInstance("SHA1PRNG");
		randDec.setSeed(parameterObject.seedByte);
		randDec.nextBytes(parameterObject.seedArray);
		sec_key_spec = new SecretKeySpec(parameterObject.seedArray, "AES");
	}




// Taken from the demo code provided
public static String decrypt_input(byte[] data_in) throws Exception{
	byte[] decrypted = null;
	String dec_str = null;
	try{
		//set cipher to decrypt mode
		sec_cipher.init(Cipher.DECRYPT_MODE, sec_key_spec);

		//do decryption
		decrypted = sec_cipher.doFinal(data_in);

		//convert to string
		dec_str = new String(decrypted);
	}
	catch(Exception e){
		System.out.println(e);
	}
	return dec_str;
}
}