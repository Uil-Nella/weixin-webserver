package com.yonyou.weixin.core.mp.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * Date:     2014-10-9 下午6:46:20 <br/>
 * @author   huanggc
 * @since    UAP6.5
 * @see 	 
 */
public class MPUtils {
	/**
	 * 对C# DES返回的16进制解密
	 * @param encrypttext
	 * @param key
	 * @return
	 * @throws  
	 * @throws Exception 
	 */
	public static String decryptDES16(String encrypt16text)  {
		String key="abcdefgh";
		String ret=null; 
		
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
	        
	        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8")); 
	         
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8")); 
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv); 
	        
	        ret=new String(cipher.doFinal(HexUtil.hexToBytes(encrypt16text)));
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		
        return ret; 
	}
	
	public static byte[] encryptDES(String message) throws Exception { 
		
		String key="abcdefgh";
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
 
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8")); 
 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8")); 
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv); 
 
        return cipher.doFinal(message.getBytes("UTF-8")); 
    } 
	
	public static void main(String[] args) {
		try {
			byte[] out=MPUtils.encryptDES(String.valueOf("liuxy0|"+System.currentTimeMillis()));
			System.out.println(HexUtil.bytesToHex(out));
			System.out.println(MPUtils.decryptDES16("C7772822E6E572F59731D5BE1E31D715"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
}

