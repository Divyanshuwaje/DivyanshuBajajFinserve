import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {
            // Read JSON file
            String content = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject jsonObject = new JSONObject(content);
            
            // Extract values
            JSONObject student = jsonObject.getJSONObject("student");
            String firstName = student.getString("first_name").toLowerCase().replace(" ", "");
            String rollNumber = student.getString("roll_number").toLowerCase().replace(" ", "");
            
            // Concatenate and generate MD5 hash
            String hash = generateMD5(firstName + rollNumber);
            
            // Write to output.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write(hash);
            }
            
            System.out.println("MD5 Hash generated successfully: " + hash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate MD5 hash
    private static String generateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
