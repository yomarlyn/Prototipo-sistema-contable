package Seguridad;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;


public class GestorContrasenas {
    
    private static final int ITERACIONES = 210000;
    private static final int TAMANO_HASH = 256;
    private static final int TAMANO_SALT = 16;

    private static final SecureRandom RANDOM = new SecureRandom();  
    
    public static String generarHash(String contrasena) {

        byte[] salt = new byte[TAMANO_SALT];
        RANDOM.nextBytes(salt);

        try {
            KeySpec especificacion = new PBEKeySpec(
                contrasena.toCharArray(),
                salt,
                ITERACIONES,
                TAMANO_HASH
            );

            SecretKeyFactory fabrica =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = fabrica.generateSecret(especificacion).getEncoded();

            return Base64.getEncoder().encodeToString(salt)
                    + ":"
                    + Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new IllegalStateException(
                "No se pudo proteger la contraseña.", e
            );
        }
    }
    
    public static boolean verificarContrasena(
            String contrasena,
            String hashGuardado) {

        try {
            String[] partes = hashGuardado.split(":");

            if (partes.length != 2) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(partes[0]);
            byte[] hashOriginal = Base64.getDecoder().decode(partes[1]);

            KeySpec especificacion = new PBEKeySpec(
                contrasena.toCharArray(),
                salt,
                ITERACIONES,
                TAMANO_HASH
            );

            SecretKeyFactory fabrica =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hashNuevo = fabrica.generateSecret(especificacion).getEncoded();

            return java.security.MessageDigest.isEqual(
                hashOriginal, hashNuevo
            );

        } catch (Exception e) {
            return false;
        }
    }
}
