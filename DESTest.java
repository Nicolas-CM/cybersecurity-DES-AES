import java.security.spec.*;
import java.util.Scanner;

import javax.crypto.*;
import javax.crypto.spec.*;

class DESTest {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Seleccione una opción:");
      System.out.println("1. Encriptar (Cifrar)");
      System.out.println("2. Desencriptar (Descifrar)");
      System.out.println("3. Encriptar (Cifrar) AES-128");
      System.out.println("4. Encriptar (Cifrar) AES-256");
      System.out.print("Opción: ");
      int opcion = sc.nextInt();

      // Vectores de prueba para cifrar
      String[][] encryptTests = {
            { "1111111111111111", "1111111111111111", "F40379AB9E0EC533" },
            { "0123456789ABCDEF", "1111111111111111", "17668DFC7292532D" },
            { "1111111111111111", "0123456789ABCDEF", "8A5AE1F81AB8F2DD" },
            { "0000000000000000", "0000000000000000", "8CA64DE9C1B123A7" }
      };

      // Vectores de prueba para descifrar (cipher, key, expected plain)
      String[][] decryptTests = {
            { "F40379AB9E0EC533", "1111111111111111", "1111111111111111" },
            { "17668DFC7292532D", "0123456789ABCDEF", "1111111111111111" },
            { "8A5AE1F81AB8F2DD", "1111111111111111", "0123456789ABCDEF" },
            { "8CA64DE9C1B123A7", "0000000000000000", "0000000000000000" }
      };

      // Vectores de prueba para cifrar AES-128 (key, plaintext, expected ciphertext)
      String[][] aes128Tests = {
            { "00000000000000000000000000000000", "f34481ec3cc627bacd5dc3fb08f273e6",
                  "0336763e966d92595a567cc9ce537f5e" },
            { "00000000000000000000000000000000", "9798c4640bad75c7c3227db910174e72",
                  "a9a1631bf4996954ebc093957b234589" },
            { "00000000000000000000000000000000", "96ab5c2ff612d9dfaae8c31f30c42168",
                  "ff4f8391a6a40ca5b25d23bedd44a597" },
            { "00000000000000000000000000000000", "6a118a874519e64e9963798a503f1d35",
                  "dc43be40be0e53712f7e2bf5ca707209" }
      };

      // Vectores de prueba para cifrar AES-256 (key, plaintext, expected ciphertext)
      String[][] aes256Tests = {
            { "0000000000000000000000000000000000000000000000000000000000000000", "014730f80ac625fe84f026c60bfd547d",
                  "5c9d844ed46f9885085e5d6a4f94c7d7" },
            { "0000000000000000000000000000000000000000000000000000000000000000", "0b24af36193ce4665f2825d7b4749c98",
                  "a9ff75bd7cf6613d3731c77c3b6d0c04" },
            { "0000000000000000000000000000000000000000000000000000000000000000", "761c1fe41a18acf20d241650611d90f1",
                  "623a52fcea5d443e48d9181ab32c7421" },
            { "0000000000000000000000000000000000000000000000000000000000000000", "8a560769d605868ad80d819bdba03771",
                  "38f2c7ae10612415d27ca190d27da8b4" }
      };

      try {
         if (opcion == 1) {
            System.out.println("\n--- Pruebas de Encriptación DES ---");
            for (int i = 0; i < encryptTests.length; i++) {
               byte[] theKey = hexToBytes(encryptTests[i][0]);
               byte[] theMsg = hexToBytes(encryptTests[i][1]);
               byte[] theExp = hexToBytes(encryptTests[i][2]);
               KeySpec ks = new DESKeySpec(theKey);
               SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
               SecretKey ky = kf.generateSecret(ks);
               Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
               cf.init(Cipher.ENCRYPT_MODE, ky);
               byte[] theCph = cf.doFinal(theMsg);
               System.out.println("Test #" + (i + 1));
               System.out.println("Key     : " + bytesToHex(theKey));
               System.out.println("Message : " + bytesToHex(theMsg));
               System.out.println("Cipher  : " + bytesToHex(theCph));
               System.out.println("Expected: " + bytesToHex(theExp));
               System.out.println("---------------------------");
            }
         } else if (opcion == 2) {
            System.out.println("\n--- Pruebas de Desencriptación DES ---");
            for (int i = 0; i < decryptTests.length; i++) {
               byte[] theCph = hexToBytes(decryptTests[i][0]);
               byte[] theKey = hexToBytes(decryptTests[i][1]);
               byte[] theExp = hexToBytes(decryptTests[i][2]);
               KeySpec ks = new DESKeySpec(theKey);
               SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
               SecretKey ky = kf.generateSecret(ks);
               Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
               cf.init(Cipher.DECRYPT_MODE, ky);
               byte[] theMsg = cf.doFinal(theCph);
               System.out.println("Test #" + (i + 1));
               System.out.println("Key     : " + bytesToHex(theKey));
               System.out.println("Cipher  : " + bytesToHex(theCph));
               System.out.println("Plain   : " + bytesToHex(theMsg));
               System.out.println("Expected: " + bytesToHex(theExp));
               System.out.println("---------------------------");
            }
         } else if (opcion == 3) {
            System.out.println("\n--- Pruebas de Encriptación AES-128 ---");
            for (int i = 0; i < aes128Tests.length; i++) {
               byte[] theKey = hexToBytes(aes128Tests[i][0]);
               byte[] theMsg = hexToBytes(aes128Tests[i][1]);
               byte[] theExp = hexToBytes(aes128Tests[i][2]);
               SecretKeySpec ky = new SecretKeySpec(theKey, "AES");
               Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");
               cf.init(Cipher.ENCRYPT_MODE, ky);
               byte[] theCph = cf.doFinal(theMsg);
               System.out.println("Test #" + (i + 1));
               System.out.println("Key     : " + bytesToHex(theKey));
               System.out.println("Message : " + bytesToHex(theMsg));
               System.out.println("Cipher  : " + bytesToHex(theCph));
               System.out.println("Expected: " + bytesToHex(theExp));
               System.out.println("---------------------------");
            }
         } else if (opcion == 4) {
            System.out.println("\n--- Pruebas de Encriptación AES-256 ---");
            for (int i = 0; i < aes256Tests.length; i++) {
               byte[] theKey = hexToBytes(aes256Tests[i][0]);
               byte[] theMsg = hexToBytes(aes256Tests[i][1]);
               byte[] theExp = hexToBytes(aes256Tests[i][2]);
               SecretKeySpec ky = new SecretKeySpec(theKey, "AES");
               Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");
               cf.init(Cipher.ENCRYPT_MODE, ky);
               byte[] theCph = cf.doFinal(theMsg);
               System.out.println("Test #" + (i + 1));
               System.out.println("Key     : " + bytesToHex(theKey));
               System.out.println("Message : " + bytesToHex(theMsg));
               System.out.println("Cipher  : " + bytesToHex(theCph));
               System.out.println("Expected: " + bytesToHex(theExp));
               System.out.println("---------------------------");
            }
         } else {
            System.out.println("Opción inválida.");
         }
      } catch (Exception e) {
         e.printStackTrace();
         return;
      }
   }

   public static byte[] hexToBytes(String str) {
      if (str == null) {
         return null;
      } else if (str.length() < 2) {
         return null;
      } else {
         int len = str.length() / 2;
         byte[] buffer = new byte[len];
         for (int i = 0; i < len; i++) {
            buffer[i] = (byte) Integer.parseInt(
                  str.substring(i * 2, i * 2 + 2), 16);
         }
         return buffer;
      }

   }

   public static String bytesToHex(byte[] data) {
      if (data == null) {
         return null;
      } else {
         int len = data.length;
         String str = "";
         for (int i = 0; i < len; i++) {
            if ((data[i] & 0xFF) < 16)
               str = str + "0"
                     + java.lang.Integer.toHexString(data[i] & 0xFF);
            else
               str = str
                     + java.lang.Integer.toHexString(data[i] & 0xFF);
         }
         return str.toUpperCase();
      }
   }
}
