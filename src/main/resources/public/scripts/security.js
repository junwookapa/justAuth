loadScript('http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/aes.js');
loadScript('../jose/dist/jose.js');

var cryptographer = new Jose.WebCryptographer();
cryptographer.setKeyEncryptionAlgorithm("A128KW");
cryptographer.setContentEncryptionAlgorithm("A128CBC-HS256");

var key = 'MTIzNDU2Nzg5MHF3ZXJ0eQ';
testasd.textContent = key;

var shared_key = {"kty":"oct", "k":key};

shared_key = crypto.subtle.importKey("jwk", shared_key, {name: "AES-KW"}, true, ["wrapKey", "unwrapKey"]);

function encrypte(text){
	try{
		var encrypter = new JoseJWE.Encrypter(cryptographer, shared_key);
		 return encrypter.encrypt(text);
	}catch(err){
		return err.message;
	}
}

function decrypte(text){
	try{
		var decrypter = new JoseJWE.Decrypter(cryptographer, shared_key);
		return decrypter.decrypt(text);
	}catch(err){
		return err.message;
	}
}