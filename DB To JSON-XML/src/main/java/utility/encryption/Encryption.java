package utility.encryption;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/10/2024, Wednesday
 **/

public class Encryption {

    final static String ENCRYPTION_PASSWORD="16bhd98u7q2";

    public static String encrypt(String inputPassword) {

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(ENCRYPTION_PASSWORD);

        return textEncryptor.encrypt(inputPassword);
    }

    public static String decrypt(String inputEncryptedPassword){
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(ENCRYPTION_PASSWORD);

        return textEncryptor.decrypt(inputEncryptedPassword);
    }
}
