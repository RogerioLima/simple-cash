package com.br.simplecash.core.util.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.exception.EncryptDecryptException;

@Component
public class EncryptDecrypt {
	@Value("${encrypt.key}")
	private String encryptKey;
	
	private String IV = "AAAAAAAAAAAAAAAA";
	
	public byte[] encrypt(String text) {
		try {
			Cipher encrypt = initEncryptDecrypt(Cipher.ENCRYPT_MODE);
			return encrypt.doFinal(text.getBytes("UTF-8"));
		} catch (Exception ex) {
			throw new EncryptDecryptException("Erro ao encriptografar dados");
		}
	}
	
	public String decrypt(byte[] encriptedText) {
		try {
			Cipher decrypt = initEncryptDecrypt(Cipher.DECRYPT_MODE);
			return new String(decrypt.doFinal(encriptedText), "UTF-8");
		} catch (Exception ex) {
			throw new EncryptDecryptException("Erro ao descriptografar dados");
		}
	}
	
	private Cipher initEncryptDecrypt(int mode) throws Exception {
		Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"), "AES");
		encrypt.init(mode, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encrypt;
	}
}
