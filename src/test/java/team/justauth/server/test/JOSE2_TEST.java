package team.justauth.server.test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.NoSuchPaddingException;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.lang.JoseException;

import team.justauth.server.security.JWEUtil;

public class JOSE2_TEST {
	
	//decodingTest
	public void test() throws JoseException, NoSuchAlgorithmException{
	String rsa_key=	"{\"n\": \"00:c2:4b:af:0f:2d:2b:ad:36:72:a7:91:0f:ee:30:a0:95:d5:3a:46:82:86:96:7e:42:c6:fe:8f:20:97:af:49:f6:48:a3:91:53:ac:2e:e6:ec:9a:9a:e0:0a:fb:1c:db:44:40:5b:8c:fc:d5:1c:cb:b6:9b:60:c0:a8:ac:06:f1:6b:29:5e:2f:7b:09:d9:93:32:da:3f:db:53:9c:2e:ea:3b:41:7f:6b:c9:7b:88:9f:2e:c5:dd:42:1e:7f:8f:04:f6:60:3c:fe:43:6d:32:10:ce:8d:99:cb:76:f7:10:97:05:af:28:1e:39:0f:78:35:50:7b:8e:28:22:a4:7d:11:51:22:d1:0e:ab:6b:6f:96:cb:cf:7d:eb:c6:aa:a2:6a:2e:97:2a:93:af:a5:89:e6:c8:bc:9f:fd:85:2b:0f:b4:c0:e4:ca:b5:a7:9a:01:05:81:93:6b:f5:8d:1c:f7:f3:77:0e:6e:53:34:92:0f:48:21:34:33:44:14:5e:4a:00:41:3a:7d:cb:38:82:c1:65:e0:79:ea:a1:05:84:b2:6e:40:19:77:1a:0e:38:4b:28:1f:34:b5:cb:ac:c5:2f:58:51:d7:ec:a8:08:0e:7c:c0:20:c1:5e:a1:4d:b1:30:17:63:0e:e7:58:8e:7f:6e:9f:a4:77:8b:1e:a2:d2:2e:1b:e9\","
			+ "\"e\": \"65537\","
			+"\"d\": \"37:b6:4b:f4:26:17:a8:0b:3c:c5:1f:ab:59:b9:47:d2:ae:d9:8e:ee:4e:79:48:ab:0d:34:61:06:0f:78:8b:d4:ba:ef:6b:f4:7a:22:d8:c4:6f:70:89:5d:9c:b3:a1:8b:e8:88:57:dd:07:9e:c2:2b:12:52:a3:eb:b9:a8:24:01:7e:53:2b:7a:34:50:d7:0c:75:d8:69:a3:87:dd:4b:fc:c1:c3:2f:bd:0e:57:16:8d:ea:de:8e:de:ff:e4:9a:9f:aa:e8:d2:5f:b3:27:ef:f9:ca:50:97:2e:fd:99:1c:34:dd:0c:bb:dd:d0:b9:bf:4f:dc:9d:de:94:50:66:2c:58:7e:c2:31:8b:41:56:49:6a:e6:11:14:53:a1:45:0d:15:8b:26:79:0f:c9:dc:ac:dc:c7:bc:55:2c:96:ed:a7:29:09:04:ee:00:74:60:e1:bc:97:7b:0a:b6:f2:83:82:79:65:e0:aa:88:9f:90:b0:0d:76:4d:3c:08:7e:a5:05:19:d4:8b:54:d3:f1:c1:a3:e3:a5:1e:aa:d6:c4:94:ad:6c:b3:8f:85:06:8a:6f:52:f8:a3:c3:e0:8d:67:35:2f:d4:18:fc:70:f4:71:bf:18:88:d6:a7:b7:04:8e:d3:06:ca:83:c3:2d:21:98:65:c9:41:2c:77:bf:4c:7c:8c:2c:01\","
			+"\"p\":  \"00:fa:d6:06:46:5c:04:70:e6:ec:47:02:96:02:a5:e2:41:9d:bd:7b:97:28:a4:c5:3b:b5:9b:0a:6b:7d:b6:44:8a:28:1e:d1:ef:cb:44:ef:eb:4d:08:74:80:f5:cf:3b:b7:40:10:60:c9:18:1e:a5:76:4b:41:37:06:b2:71:03:60:25:77:db:d0:b2:21:dc:b0:32:90:a2:10:9a:d5:e6:e3:11:42:a1:9a:7a:26:3c:d3:12:56:db:25:07:69:be:ae:2c:b9:33:6c:29:e3:65:b9:5b:05:84:05:e6:da:c4:f4:3f:ab:84:60:6e:f0:5f:ba:a8:98:8f:72:2c:c8:40:d1\","
			+"\"q\": \"00:c6:4b:ac:fe:40:1c:dc:6c:78:07:cc:3e:db:4e:d5:d0:17:3b:8f:04:f0:ae:c4:22:0d:8b:0a:4d:0f:9e:fe:c7:e6:38:b5:53:ba:a9:e8:f0:47:28:14:25:95:6a:79:ab:db:86:97:82:c5:1e:bd:80:a5:aa:a2:b7:a5:c7:48:17:c4:d9:c7:4f:50:2a:69:67:15:4c:0b:f5:e6:fb:20:23:5d:ea:ae:6c:c6:74:ba:cc:f8:06:2b:41:1f:b6:3f:2a:93:fa:f9:e1:ee:93:c3:92:ad:49:c7:8f:db:72:ff:6b:f0:f0:d6:2f:83:ce:1c:82:16:89:57:01:9f:49:2f:99\","
			+"\"dp\": \"57:d4:c1:75:b9:9a:c4:7d:d7:96:35:cd:99:37:c4:b5:fd:29:f0:30:c9:c6:88:59:94:09:a9:e8:61:a8:84:ef:6b:84:ff:35:dc:13:53:7f:2d:06:1c:e5:5b:2d:29:57:cd:52:ee:d0:fb:65:1f:c3:00:2e:e1:b9:b2:99:e7:f8:ae:a5:fd:8e:62:11:81:59:21:1b:8b:e4:0c:93:81:b9:58:bd:e0:20:5b:4d:30:57:28:40:c9:93:79:b9:09:4f:ab:d1:5d:b4:2e:26:b5:e3:e5:7f:54:ef:4c:1a:a6:84:70:16:fa:cf:59:89:49:bb:ee:75:1d:25:79:90:d5:41\","
			+"\"dq\": \"00:ab:eb:a8:8c:b7:21:4e:aa:6c:56:b6:6a:38:d1:dc:e6:91:7d:fd:bd:96:be:af:25:a7:00:49:6a:0e:85:16:f8:51:4e:11:48:0a:aa:8d:5e:e5:12:86:85:1f:4a:35:3b:1f:15:4d:fe:fe:d0:6c:14:41:8d:f3:8d:ad:99:5d:93:de:03:c2:9d:ad:2f:58:3b:1b:67:d7:66:d7:60:1a:b9:0f:10:0d:32:19:cd:d2:b7:2a:c2:8e:75:e3:fc:aa:3f:4c:15:68:d8:cd:74:27:37:e0:2d:fb:6b:6a:24:05:f7:9b:e9:f2:89:37:89:57:86:21:eb:e9:17:6a:f6:94:e1\","
			+"\"qi\": \"0a:ed:5f:30:67:d5:e5:6e:4a:7a:35:49:fe:16:2f:1e:91:2b:39:c3:01:d3:d4:c0:4d:b3:fc:08:b0:66:e9:44:10:9e:5b:5a:ea:83:a5:9c:95:7a:58:70:35:28:e5:4d:ba:19:de:0d:66:f9:db:5c:f6:5b:24:27:9d:0b:2d:44:40:eb:33:3a:19:e2:1d:c0:b0:16:99:d1:c1:52:84:02:d6:67:06:32:f8:4d:cb:42:9f:7c:8a:e0:ad:df:40:6f:e4:8c:f6:f6:9e:1d:bd:43:e3:38:91:a2:d0:9e:60:ff:9d:8c:fb:72:5b:df:95:30:17:d2:f2:cb:7d:92:56:0a\","
			+"}";
	
	
//	PublicJsonWebKey
//	privateJsonWebKey
	//RsaJsonWebKey jwk = (RsaJsonWebKey) PublicJsonWebKey
	//JsonWebKey jwk = JsonWebKey.Factory.new
	// + "      \"alg\": \"RSA-OAEP\",\n"
	// + "      \"kid\": \"2011-04-29\"\n" 
	String json = "     {\n"
			+ "      \"kty\": \"RSA\",\n"
			+ "      \"n\": \"oahUIoWw0K0usKNuOR6H4wkf4oBUXHTxRvgb48E-BVvxkeDNjbC4he8rUWcJoZmds2h7M70imEVhRU5djINXtqllXI4DFqcI1DgjT9LewND8MW2Krf3Spsk_ZkoFnilakGygTwpZ3uesH-PFABNIUYpOiN15dsQRkgr0vEhxN92i2asbOenSZeyaxziK72UwxrrKoExv6kc5twXTq4h-QChLOln0_mtUZwfsRaMStPs6mS6XrgxnxbWhojf663tuEQueGC-FCMfra36C9knDFGzKsNa7LZK2djYgyD3JR_MB_4NUJW_TqOQtwHYbxevoJArm-L5StowjzGy-_bq6Gw\",\n"
			+ "      \"e\": \"AQAB\",\n"
			+ "}";
	RsaJsonWebKey jwk = (RsaJsonWebKey) PublicJsonWebKey.Factory.newPublicJwk(json);
	JsonWebEncryption jwe = new JsonWebEncryption();
	jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
	jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
	jwe.setKey(jwk.getPublicKey());
	jwe.setPayload("asd");
	String jwscs = jwe.getCompactSerialization();
	//eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.ctc5eOA2eYJ5V485zEovFU3ShOfAmp4kUYIXryJ5uilmShFNiCL7bybITKavK6kFPuZj-fNrN5czgvri6J4emQfb9xWIl1kC_DoVGpMY2aaRNNxwN8qSzR2R2xQAHqBIuIe0S84sqfh0cFpTp_GoouV74l4gElWJFOnzGcXnkDX8q7VJyeW5XcMDAc9IPDwL4hy5VJ15jt3pnrJv2QVjnVOvTnXE5QZDf7S_QLTFwQ1afMcI0zPYzmIBFVif6Igj2PIxr2PJwihrYLzBtIft6__SzQ-vSSlmGVBn0OKlQbqfWvgbw94rZPv3CPhhaLar7j8ISltP5IHaa1oXd3DPiptJGxNI1wNsL5koo6PSP_eLKsUnu3fSsPIm83mnwgTEztjg9N95OE4aRAROR8pDu0C5AM88-tOE9X9X5tcALwyVATxtPjIpM2oOgSjgATXIqU_3l2RIyFbAivpdUyAlXr-_Q-_Rp6dMoUlllK6GtTjvoeBnpd-CgERov1BgIGzDoQ.L_rBIczznVfGU2S9.iHvfAlvk-mnXNA.HwcOgWy-8QlSLYXvY-0DtA

	
//	String jwscs = jws.getCompactSerialization();
	}
	
	public void superman() throws JoseException, NoSuchAlgorithmException{
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		clsKeyPairGenerator.initialize(2048);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
	
		
		PublicJsonWebKey.Factory.newPublicJwk(clsKeyPair.getPublic());
		RsaJsonWebKey asd = new RsaJsonWebKey((RSAPublicKey) clsKeyPair.getPublic());
		asd.setPrivateKey(clsKeyPair.getPrivate());
				
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
		jwe.setKey(asd.getPublicKey());
		jwe.setPayload("asd");
		String enString = jwe.getCompactSerialization();
		System.out.println(enString);
		
		JsonWebEncryption jwe2 = new JsonWebEncryption();
		jwe2.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe2.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
		jwe2.setKey(asd.getPrivateKey());
		jwe2.setCompactSerialization(enString);
		System.out.println(jwe2.getPayload());

		
	}
	
	
	public void test2(){
		String key=	"     {\n"
				+ "\"kty\": \"RSA\",\n"
				+ "\"n\": \"oahUIoWw0K0usKNuOR6H4wkf4oBUXHTxRvgb48E-BVvxkeDNjbC4he8rUWcJoZmds2h7M70imEVhRU5djINXtqllXI4DFqcI1DgjT9LewND8MW2Krf3Spsk_ZkoFnilakGygTwpZ3uesH-PFABNIUYpOiN15dsQRkgr0vEhxN92i2asbOenSZeyaxziK72UwxrrKoExv6kc5twXTq4h-QChLOln0_mtUZwfsRaMStPs6mS6XrgxnxbWhojf663tuEQueGC-FCMfra36C9knDFGzKsNa7LZK2djYgyD3JR_MB_4NUJW_TqOQtwHYbxevoJArm-L5StowjzGy-_bq6Gw\",\n"
				+ "\"e\": \"AQAB\",\n"
				+ "\"d\": \"kLdtIj6GbDks_ApCSTYQtelcNttlKiOyPzMrXHeI-yk1F7-kpDxY4-WY5N"+
               "WV5KntaEeXS1j82E375xxhWMHXyvjYecPT9fpwR_M9gV8n9Hrh2anTpTD9"+
               "3Dt62ypW3yDsJzBnTnrYu1iwWRgBKrEYY46qAZIrA2xAwnm2X7uGR1hghk"+
               "qDp0Vqj3kbSCz1XyfCs6_LehBwtxHIyh8Ripy40p24moOAbgxVw3rxT_vl"+
               "t3UVe4WO3JkJOzlpUf-KTVI2Ptgm-dARxTEtE-id-4OJr0h-K-VFs3VSnd"+
               "VTIznSxfyrj8ILL6MG_Uv8YAu7VILSB3lOW085-4qE3DzgrTjgy\",\n"
		+ "      \"p\": \"1r52Xk46c-LsfB5P442p7atdPUrxQSy4mti_tZI3Mgf2EuFVbUoDBvaRQ-"+
               "SWxkbkmoEzL7JXroSBjSrK3YIQgYdMgyAEPTPjXv_hI2_1eTSPVZfzL0lf"+
               "fNn03IXqWF5MDFuoUYE0hzb2vhrlN_rKrbfDIwUbTrjjgieRbwC6Cl0\",\n"
		+ "      \"q\": \"wLb35x7hmQWZsWJmB_vle87ihgZ19S8lBEROLIsZG4ayZVe9Hi9gDVCOBm"+
               "UDdaDYVTSNx_8Fyw1YYa9XGrGnDew00J28cRUoeBB_jKI1oma0Orv1T9aX"+
               "IWxKwd4gvxFImOWr3QRL9KEBRzk2RatUBnmDZJTIAfwTs0g68UZHvtc\",\n"
		+ "\"dp\": \"ZK-YwE7diUh0qR1tR7w8WHtolDx3MZ_OTowiFvgfeQ3SiresXjm9gZ5KL"+
               "hMXvo-uz-KUJWDxS5pFQ_M0evdo1dKiRTjVw_x4NyqyXPM5nULPkcpU827"+
               "rnpZzAJKpdhWAgqrXGKAECQH0Xt4taznjnd_zVpAmZZq60WPMBMfKcuE\",\n"
		+ "\"dq\": \"Dq0gfgJ1DdFGXiLvQEZnuKEN0UUmsJBxkjydc3j4ZYdBiMRAy86x0vHCj"+
               "ywcMlYYg4yoC4YZa9hNVcsjqA3FeiL19rk8g6Qn29Tt0cj8qqyFpz9vNDB"+
               "UfCAiJVeESOjJDZPYHdHY8v1b-o-Z2X5tvLx-TCekf7oxyeKDUqKWjis\",\n"
		+ "\"qi\": \"VIMpMYbPf47dT1w_zDUXfPimsSegnMOA1zTaX7aGk_8urY6R8-ZW1FxU7"+
               "AlWAyLWybqq6t16VFd7hQd0y6flUK4SlOydB61gwanOsXGOAOv82cHq0E3"+
               "eL4HrtZkUuKvnPrMnsUUFlfUdybVzxyjz9JF_XyaY14ardLSjf4L_FNY\"\n" + "}";
		
	}
	
	public void encoding2() throws JoseException, NoSuchAlgorithmException{
//		RsaJsonWebKey ads = new RsaJsonWebKey();		
		
		
		String key=	"     {\n"
				+ "\"kty\": \"RSA\",\n"
				+ "\"n\": \"oahUIoWw0K0usKNuOR6H4wkf4oBUXHTxRvgb48E-BVvxkeDNjbC4he8rUWcJoZmds2h7M70imEVhRU5djINXtqllXI4DFqcI1DgjT9LewND8MW2Krf3Spsk_ZkoFnilakGygTwpZ3uesH-PFABNIUYpOiN15dsQRkgr0vEhxN92i2asbOenSZeyaxziK72UwxrrKoExv6kc5twXTq4h-QChLOln0_mtUZwfsRaMStPs6mS6XrgxnxbWhojf663tuEQueGC-FCMfra36C9knDFGzKsNa7LZK2djYgyD3JR_MB_4NUJW_TqOQtwHYbxevoJArm-L5StowjzGy-_bq6Gw\",\n"
				+ "\"e\": \"AQAB\",\n"
				+ "\"d\": \"kLdtIj6GbDks_ApCSTYQtelcNttlKiOyPzMrXHeI-yk1F7-kpDxY4-WY5N"+
               "WV5KntaEeXS1j82E375xxhWMHXyvjYecPT9fpwR_M9gV8n9Hrh2anTpTD9"+
               "3Dt62ypW3yDsJzBnTnrYu1iwWRgBKrEYY46qAZIrA2xAwnm2X7uGR1hghk"+
               "qDp0Vqj3kbSCz1XyfCs6_LehBwtxHIyh8Ripy40p24moOAbgxVw3rxT_vl"+
               "t3UVe4WO3JkJOzlpUf-KTVI2Ptgm-dARxTEtE-id-4OJr0h-K-VFs3VSnd"+
               "VTIznSxfyrj8ILL6MG_Uv8YAu7VILSB3lOW085-4qE3DzgrTjgy\",\n"
		+ "      \"p\": \"1r52Xk46c-LsfB5P442p7atdPUrxQSy4mti_tZI3Mgf2EuFVbUoDBvaRQ-"+
               "SWxkbkmoEzL7JXroSBjSrK3YIQgYdMgyAEPTPjXv_hI2_1eTSPVZfzL0lf"+
               "fNn03IXqWF5MDFuoUYE0hzb2vhrlN_rKrbfDIwUbTrjjgieRbwC6Cl0\",\n"
		+ "      \"q\": \"wLb35x7hmQWZsWJmB_vle87ihgZ19S8lBEROLIsZG4ayZVe9Hi9gDVCOBm"+
               "UDdaDYVTSNx_8Fyw1YYa9XGrGnDew00J28cRUoeBB_jKI1oma0Orv1T9aX"+
               "IWxKwd4gvxFImOWr3QRL9KEBRzk2RatUBnmDZJTIAfwTs0g68UZHvtc\",\n"
		+ "\"dp\": \"ZK-YwE7diUh0qR1tR7w8WHtolDx3MZ_OTowiFvgfeQ3SiresXjm9gZ5KL"+
               "hMXvo-uz-KUJWDxS5pFQ_M0evdo1dKiRTjVw_x4NyqyXPM5nULPkcpU827"+
               "rnpZzAJKpdhWAgqrXGKAECQH0Xt4taznjnd_zVpAmZZq60WPMBMfKcuE\",\n"
		+ "\"dq\": \"Dq0gfgJ1DdFGXiLvQEZnuKEN0UUmsJBxkjydc3j4ZYdBiMRAy86x0vHCj"+
               "ywcMlYYg4yoC4YZa9hNVcsjqA3FeiL19rk8g6Qn29Tt0cj8qqyFpz9vNDB"+
               "UfCAiJVeESOjJDZPYHdHY8v1b-o-Z2X5tvLx-TCekf7oxyeKDUqKWjis\",\n"
		+ "\"qi\": \"VIMpMYbPf47dT1w_zDUXfPimsSegnMOA1zTaX7aGk_8urY6R8-ZW1FxU7"+
               "AlWAyLWybqq6t16VFd7hQd0y6flUK4SlOydB61gwanOsXGOAOv82cHq0E3"+
               "eL4HrtZkUuKvnPrMnsUUFlfUdybVzxyjz9JF_XyaY14ardLSjf4L_FNY\"\n" + "}";
		
		
		
	
		String json = "     {\n"
				+ "      \"kty\": \"RSA\",\n"
				+ "      \"n\": \"oahUIoWw0K0usKNuOR6H4wkf4oBUXHTxRvgb48E-BVvxkeDNjbC4he8rUWcJoZmds2h7M70imEVhRU5djINXtqllXI4DFqcI1DgjT9LewND8MW2Krf3Spsk_ZkoFnilakGygTwpZ3uesH-PFABNIUYpOiN15dsQRkgr0vEhxN92i2asbOenSZeyaxziK72UwxrrKoExv6kc5twXTq4h-QChLOln0_mtUZwfsRaMStPs6mS6XrgxnxbWhojf663tuEQueGC-FCMfra36C9knDFGzKsNa7LZK2djYgyD3JR_MB_4NUJW_TqOQtwHYbxevoJArm-L5StowjzGy-_bq6Gw\",\n"
				+ "      \"e\": \"AQAB\",\n" + "      \"alg\": \"RSA-OAEP\",\n"
				+ "      \"kid\": \"2011-04-29\"\n" + "}";
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);
			
//		RsaJsonWebKey jwk =(RsaJsonWebKey) PublicJsonWebKey.Factory.newPublicJwk(json);
	//	RsaJsonWebKey jwk =new RsaJsonWebKey(publicKey);
		
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
	//	jwe.setKey(jwk.getPublicKey());
		
		jwe.setPayload("testString");
		String jwscs = jwe.getCompactSerialization();
		//eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.ctc5eOA2eYJ5V485zEovFU3ShOfAmp4kUYIXryJ5uilmShFNiCL7bybITKavK6kFPuZj-fNrN5czgvri6J4emQfb9xWIl1kC_DoVGpMY2aaRNNxwN8qSzR2R2xQAHqBIuIe0S84sqfh0cFpTp_GoouV74l4gElWJFOnzGcXnkDX8q7VJyeW5XcMDAc9IPDwL4hy5VJ15jt3pnrJv2QVjnVOvTnXE5QZDf7S_QLTFwQ1afMcI0zPYzmIBFVif6Igj2PIxr2PJwihrYLzBtIft6__SzQ-vSSlmGVBn0OKlQbqfWvgbw94rZPv3CPhhaLar7j8ISltP5IHaa1oXd3DPiptJGxNI1wNsL5koo6PSP_eLKsUnu3fSsPIm83mnwgTEztjg9N95OE4aRAROR8pDu0C5AM88-tOE9X9X5tcALwyVATxtPjIpM2oOgSjgATXIqU_3l2RIyFbAivpdUyAlXr-_Q-_Rp6dMoUlllK6GtTjvoeBnpd-CgERov1BgIGzDoQ.L_rBIczznVfGU2S9.iHvfAlvk-mnXNA.HwcOgWy-8QlSLYXvY-0DtA

		System.out.println(jwscs);
		
		JsonWebEncryption jwe2 = new JsonWebEncryption();
	//	jwe2.setKey(jwk.getRsaPrivateKey());
		jwe2.setCompactSerialization(jwscs);
		System.out.println(jwe2.getCompactSerialization());
		
		
	}
	
	public void security() throws JoseException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException{
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		clsKeyPairGenerator.initialize(2048);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		PublicJsonWebKey.Factory.newPublicJwk(clsKeyPair.getPublic());
		RsaJsonWebKey asd = new RsaJsonWebKey((RSAPublicKey) clsKeyPair.getPublic());
		asd.setPrivateKey(clsKeyPair.getPrivate());
		
		JWEUtil sec = new JWEUtil();
		String asdc = sec.encoder(clsKeyPair.getPublic(), "asd");
		System.out.println(asdc);
		String bb = sec.decoder(clsKeyPair.getPrivate(), asdc);
		System.out.println();
	}
	
	
	
	
	

}
