package team.justtag.server.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.jose4j.base64url.Base64Url;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.OctJwkGenerator;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.keys.PbkdfKey;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.zip.CompressionAlgorithmIdentifiers;

public class JOSE1_TEST {

	PrivateKey pre;
	PublicKey pub;

	
	public void test3() throws JoseException{
	PublicJsonWebKey jwk111 = EcJwkGenerator.generateJwk(EllipticCurves.P256);
	System.out.println(jwk111.getPublicKey().getEncoded().toString());
	
}
	public void test() throws ParseException, NoSuchAlgorithmException,
			JoseException {
		String json = "     {\n"
				+ "      \"kty\": \"RSA\",\n"
				+ "      \"n\": \"0vx7agoebGcQSuuPiLJXZptN9nndrQmbXEps2aiAFbWhM78LhWx4cbbfAAt\n"
				+ "            VT86zwu1RK7aPFFxuhDR1L6tSoc_BJECPebWKRXjBZCiFV4n3oknjhMstn6\n"
				+ "            4tZ_2W-5JsGY4Hc5n9yBXArwl93lqt7_RN5w6Cf0h4QyQ5v-65YGjQR0_FD\n"
				+ "            W2QvzqY368QQMicAtaSqzs8KJZgnYb9c7d0zgdAZHzu6qMQvRL5hajrn1n9\n"
				+ "            1CbOpbISD08qNLyrdkt-bFTWhAI4vMQFh6WeZu0fM4lFd2NcRwr3XPksINH\n"
				+ "            aQ-G_xBniIqbw0Ls1jF44-csFCur-kEgU8awapJzKnqDKgw\",\n"
				+ "      \"e\": \"AQAB\",\n" + "      \"alg\": \"RS256\",\n"
				+ "      \"kid\": \"2011-04-29\"\n" + "     }";

		RsaJsonWebKey jwk = (RsaJsonWebKey) PublicJsonWebKey.Factory
				.newPublicJwk(json);

		String template = "{\"e\":\"%s\",\"kty\":\"RSA\",\"n\":\"%s\"}";
		RSAPublicKey rsaPublicKey = jwk.getRsaPublicKey();
		String e = BigEndianBigInteger.toBase64Url(rsaPublicKey.getPublicExponent());
		String n = BigEndianBigInteger.toBase64Url(rsaPublicKey.getModulus());
		String formated = String.format(template, e, n);
		byte[] bytesUtf8 = StringUtil.getBytesUtf8(formated);
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		byte[] digest = sha256.digest(bytesUtf8);
		String encode = Base64Url.encode(digest);
		System.out.println(encode);
		System.out.println("NzbLsXh8uDCcd-6MNwXF4W_7noWXFZAfHkxZsRGC9Xs".equals(encode));
		
		JwtClaims jcs = new JwtClaims();
		jcs.setIssuer("usa");
		jcs.setAudience("canada");
		jcs.setExpirationTimeMinutesInTheFuture(30);
		jcs.setClaim("message", "eh");
		
		String claims = jcs.toJson();
		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims);
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		
		
		OctetSequenceJsonWebKey macKey = OctJwkGenerator.generateJwk(256);
		jws.setKey(macKey.getKey());
		String jwscs = jws.getCompactSerialization();
		System.out.println(claims);
		System.out.println(macKey.toJson(JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC));
		System.out.println(jwscs);
		System.out.println("zazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		OctetSequenceJsonWebKey wrapKey = OctJwkGenerator.generateJwk(128);

		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(jwscs);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		System.out.println(wrapKey
				.toJson(JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC));
		jwe.setKey(wrapKey.getKey());
		jwe.setHeader(HeaderParameterNames.CONTENT_TYPE, "JWT");
		System.out.println(jwe.getCompactSerialization());

		jwe = new JsonWebEncryption();
		jwe.setHeader(HeaderParameterNames.ZIP, CompressionAlgorithmIdentifiers.DEFLATE);
		jwe.setPayload(jwscs);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		System.out.println("wrap : "+wrapKey
				.toJson(JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC));
		jwe.setKey(wrapKey.getKey());
		jwe.setHeader(HeaderParameterNames.CONTENT_TYPE, "JWT");
		System.out.println("comp : "+jwe.getCompactSerialization());
		/****************************************************************************************/
		
		JsonWebEncryption jwe2 = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setHeader(HeaderParameterNames.ZIP, CompressionAlgorithmIdentifiers.DEFLATE);
		jwe2.setCompactSerialization(jwe.getCompactSerialization());
	//	System.out.println(jwe2.getPayload());

	}
	public void Test2() throws JoseException{
		System.out.println("");
		System.out.println("Test2");
		String jwksJson = "{\"keys\":[\n"
				+ " {\"kty\":\"EC\",\n\"kid\":\"4\",\n"
				+ "  \"x\":\"LX-7aQn7RAx3jDDTioNssbODUfED_6XvZP8NsGzMlRo\", \n"
				+ "  \"y\":\"dJbHEoeWzezPYuz6qjKJoRVLks7X8-BJXbewfyoJQ-A\",\n"
				+ "  \"crv\":\"P-256\"},\n"
				+ " {\"kty\":\"EC\",\n\"kid\":\"5\",\n"
				+ "  \"x\":\"f83OJ3D2xF1Bg8vub9tLe1gHMzV76e8Tus9uPHvRVEU\",\n"
				+ "  \"y\":\"x_FEzRu9m36HLN_tue659LNpXW6pCyStikYjKIWI5a0\",\n"
				+ "  \"crv\":\"P-256\"},\n"
				+ " {\"kty\":\"EC\",\n\"kid\":\"6\",\n"
				+ "  \"x\":\"J8z237wci2YJAzArSdWIj4OgrOCCfuZ18WI77jsiS00\",\n"
				+ "  \"y\":\"5tTxvax8aRMMJ4unKdKsV0wcf3pOI3OG771gOa45wBU\",\n"
				+ "  \"crv\":\"P-256\"}\n" + "]}";

		JsonWebKeySet jwks = new JsonWebKeySet(jwksJson);
		JsonWebKey jwk1 = jwks.findJsonWebKey("5", null, null, null);
		System.out.println(jwk1.getKey());

		List<JsonWebKey> jwkList = new LinkedList<>();
		for (int kid = 4; kid < 7; kid++) {
			JsonWebKey jwk11 = EcJwkGenerator.generateJwk(EllipticCurves.P256);
			jwk11.setKeyId(String.valueOf(kid));
			jwkList.add(jwk11);
		}
		JsonWebKeySet jwks1 = new JsonWebKeySet(jwkList);
		System.out.println(jwks1
				.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
		JsonWebEncryption jwe1 = new JsonWebEncryption();
		jwe1.setPayload("I actually really like Canada");
		jwe1.setKey(new PbkdfKey("don't-tell-p@ul|pam!"));
		jwe1.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.PBES2_HS256_A128KW);
		jwe1.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		String compactSerialization = jwe1.getCompactSerialization();

		System.out.println(compactSerialization);
		String compactSerialization1 = "eyJhbGciOiJQQkVTMi1IUzI1NitBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwicDJjIjo4MTkyLCJwMnMiOiJRa2JMUW5pS0xVVFFWUDRsIn0."
				+ "g7s-MxHFn5WHCfO33hgWYiAtH1lB83TnufWoaFIEujEYb14pqeH9Mg."
				+ "6h172lww9VqemjMQMaVPdg."
				+ "YMg_F8aoT3ZByou3CURhKzaGX1nc5QJDo3cWyUSyow0."
				+ "Ie4iYLbdQCqwMWJf37rEZg";

		JsonWebEncryption jwe11 = new JsonWebEncryption();
		jwe11.setCompactSerialization(compactSerialization1);
		jwe11.setKey(new PbkdfKey("don't-tell-p@ul|pam!"));
		String payload = jwe11.getPayload();

		System.out.println(payload);

		JsonWebKey jwk11 = JsonWebKey.Factory.newJwk("{\"kty\":\"EC\","
				+ "\"kid\":\"my-first-key\","
				+ "\"x\":\"xlKTWTx76fl9OZou4LHpDc3oHLC_vm-db7mdsFvO1JQ\","
				+ "\"y\":\"3jXBG649Uqf7pf8RHO_jcJ8Jrhy23hjD933i6QEVNkk\","
				+ "\"crv\":\"P-256\"}");

		String compactSerialization11 = "eyJhbGciOiJFUzI1NiIsImtpZCI6Im15LWZpcnN0LWtleSJ9."
				+ "VVNBICMxIQ."
				+ "QJGB_sHj-w3yCBunJs2wxKgvZgG2Hq9PA-TDQEbNdTm2Wnj2sUSrBKZJAUREzF1FF25BbrgyohbKdGE1cB-hrA";

		JsonWebSignature jws1 = new JsonWebSignature();
		jws1.setCompactSerialization(compactSerialization11);
		jws1.setKey(jwk11.getKey());
		String payload1 = jws1.getPayload();

		System.out.println(payload1);

		PublicJsonWebKey jwk111 = EcJwkGenerator.generateJwk(EllipticCurves.P256);
		jwk111.setKeyId("my-first-key");

		JsonWebSignature jws11 = new JsonWebSignature();
		jws11.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);
		jws11.setPayload("USA #1!");
		jws11.setKey(jwk111.getPrivateKey());
		jws11.setKeyIdHeaderValue(jwk111.getKeyId());
		String compactSerialization111 = jws11.getCompactSerialization();

		System.out.println(compactSerialization111);

		System.out.println(jws11.getHeaders().getFullHeaderAsJsonString());
		System.out.println(jwk111.toJson(JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE));
		System.out.println(jwk111.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
	}

}
