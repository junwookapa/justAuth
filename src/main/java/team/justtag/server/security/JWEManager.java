package team.justtag.server.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import org.jose4j.jwk.RsaJsonWebKey;

import team.justtag.server.main.Config;

public class JWEManager {
	
/*	private static JWEManager uniqueInstance;
	private RsaJsonWebKey mJsonWebKey;
	private JWEManager() throws NoSuchAlgorithmException {
		generateKey();
	}

	 외부에서 사용할 함수 선언 클래스를 생성할떄 쓴다 
	public static JWEManager getInstance() throws NoSuchAlgorithmException {
		if (uniqueInstance == null) {// 있는지 체크 없으면
			uniqueInstance = new JWEManager(); // 생성한뒤
		}

		return uniqueInstance;// 성성자를 넘긴다.
	}
*/
	private RsaJsonWebKey mJsonWebKey;
	public JWEManager() throws NoSuchAlgorithmException{
		generateKey();
	}

	public void generateKey() throws NoSuchAlgorithmException {
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator
				.getInstance("RSA");
		clsKeyPairGenerator.initialize(Config.RSA_LENGTH);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		mJsonWebKey = new RsaJsonWebKey((RSAPublicKey) clsKeyPair.getPublic());
		mJsonWebKey.setPrivateKey(clsKeyPair.getPrivate());
	}

	public PublicKey getPublicKey() {
		return mJsonWebKey.getPublicKey();
	}

	public PrivateKey getPrivateKey() {
		return mJsonWebKey.getPrivateKey();
	}

	public String getPublicKeyWithJson() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		return new JWEwithRSA().publicKeyConvertJsonString(mJsonWebKey
				.getPublicKey());
	}
	

}
