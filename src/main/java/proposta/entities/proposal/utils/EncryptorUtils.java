package proposta.entities.proposal.utils;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class EncryptorUtils {

    private static final String secret = "YXBpLXByb3Bvc3Rhcw==";
    private static final String salt = "58d35f5f46180217";

    public static String textEncrypt(String string) {
        TextEncryptor encryptor = Encryptors.queryableText(secret, salt);
        return encryptor.encrypt(string);
    }

    public static String textDecrypt(String string) {
        TextEncryptor encryptor = Encryptors.queryableText(secret, salt);
        return encryptor.decrypt(string);
    }

}
