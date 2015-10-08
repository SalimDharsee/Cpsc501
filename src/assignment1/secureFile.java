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


public class secureFile{


	private static SecretKeySpec sec_key_spec = null;
	private static Cipher sec_cipher = null;

	public static void main(String args[]) throws Exception{
		
		checkArgs(args);
		FileInputStream in_file = null;
		FileInputStream in_file2 = null;
		FileOutputStream out_file = null;
		byte[] sha_hash = null;
		byte[] aes_ciphertext = null;
		int read_bytes = 0;
		String in_seed = null;
		byte[] seedByte = null;
		
		
		try{
			//grab command lines arguments 
			in_seed = (args[1]);
			in_file = new FileInputStream(args[0]);
	
				out_file = new FileOutputStream("encrypted");

				//read file into a byte array
				byte[] msg = new byte[in_file.available()];
				read_bytes = in_file.read(msg);

				//SHA-1 Hash
				sha_hash = sha1_hash(msg);
				seedByte = in_seed.getBytes();
				byte [] seedArray = new byte[16];
				//encrypt file with AES
				//key setup - generate 128 bit key
				randomSeed(new RandomSeed(seedByte, seedArray));

				//create the cipher object that uses AES as the algorithm
				sec_cipher = Cipher.getInstance("AES");	

				//do AES encryption
				aes_ciphertext = encrypt_input(msg);
				String inc_str = new String ();
				inc_str = new String(aes_ciphertext);
				System.out.println(inc_str);
				out_file.write(aes_ciphertext);
				out_file.close();
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
		SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
		rand.setSeed(parameterObject.seedByte);
		rand.nextBytes(parameterObject.seedArray);
		sec_key_spec = new SecretKeySpec(parameterObject.seedArray, "AES");
	}
	
	public static void checkArgs(String[] args){
		if(args == null || args.length < 2){
			throw new IllegalArgumentException();
		}
	}
	// Taken from the demo code provided 
	public static byte[] sha1_hash(byte[] input_data) throws Exception{
		byte[] hashval = null;
		try{
			//create message digest object
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			
			//make message digest
			hashval = sha1.digest(input_data);
		}
		catch(NoSuchAlgorithmException nsae){
			System.out.println(nsae);
		}
		return hashval;
	}

	// Taken from the demo code provided
	public static byte[] encrypt_input(byte[] data_in) throws Exception{
		byte[] out_bytes = null;
		try{
			//set cipher object to encrypt mode
			sec_cipher.init(Cipher.ENCRYPT_MODE, sec_key_spec);

			//create ciphertext
			out_bytes = sec_cipher.doFinal(data_in);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return out_bytes;
	}
}