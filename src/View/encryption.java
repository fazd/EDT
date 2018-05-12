/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author jaisi
 */
public class encryption {

    // Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
    private final static String alg = "AES";
    // Definición del modo de cifrado a utilizar
    private final static String cI = "AES/CBC/PKCS5Padding";
    private static String key = "92AE31A79FEEB2A3"; //llave
    private static String iv = "0123456789ABCDEF"; // vector de inicialización

    /**
     * Función que se encarga de cifrar la linea mediante el algoritmo usado
     * @param sinCifrar Linea que se quiere cifrar
     * @return Linea cifrada
     * @throws Exception Si el cifrado falla
     */
    
    
    public static String cifra(String sinCifrar) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(cI);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(sinCifrar.getBytes());
            return new String(Base64.getEncoder().encode(encrypted));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("null");
        }
        return null;
    }

    
    /**
     * Función que se encarga de descifrar con las llaves 
     * @param cifrado Linea que está cifrada
     * @return Linea descifrada
     * @throws Exception Si el descifrado falla
     */
    
    public static String descifra(String cifrado) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        byte[] enc = Base64.getDecoder().decode(cifrado);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(decrypted);
    }
    
    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "QvodwHtVr7SjwHa?!";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }
}
