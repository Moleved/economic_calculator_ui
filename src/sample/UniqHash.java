package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UniqHash {
    private static String hash;

    public static String getUniqHash() {
        Path path = Paths.get("./hash.bt");

        try {
            byte[] bytes = Files.readAllBytes(path);
            hash = new String(bytes);
        } catch (IOException ex) {
            createFile();
            createHash();
        }

        return hash;
    }

    private static void createHash() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("./hash.bt");

            fileOutput.write(generateHash());

            fileOutput.close();
        } catch (FileNotFoundException ex) {
            createFile();
            createHash();
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    private static void createFile() {
        new File("./hash.bt");
    }

    private static byte[] generateHash() {
        String uniqString = new Date().getTime() + "";
        byte[] messageBytes = uniqString.getBytes();
        byte[] result = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            result = messageDigest.digest(messageBytes);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getStackTrace());
        }

        return result;
    }
}
