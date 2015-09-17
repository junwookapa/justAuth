package team.justtag.server.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import team.justtag.server.main.Config;

import com.auth0.jwt.internal.org.apache.commons.codec.DecoderException;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Hex;


public class AESSecurity {
		
	public String encoding(String str) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, Config.SECRET_KEY);
			byte[] encryptedData;
			encryptedData = cipher.doFinal(str.getBytes());
			return Hex.encodeHexString(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

	public String decoding(String str) {
		try {
			Cipher cipherx = Cipher.getInstance("AES");
			cipherx.init(Cipher.DECRYPT_MODE, Config.SECRET_KEY);
			byte[] plainText = cipherx
					.doFinal(Hex.decodeHex(str.toCharArray()));
			return new String(plainText);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}

}
