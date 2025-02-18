package org.capgemini.advanced_problems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EncryptDecryptCSV {

    // Define a simple XOR encryption key (you can change this key as needed)
    private static final char XOR_KEY = 'K';  // Simple XOR key for encryption

    // Define the fields to encrypt
    private static final int EMAIL_INDEX = 2;  // Index for Email field
    private static final int SALARY_INDEX = 3; // Index for Salary field

    // XOR Encryption method
    public static String xorEncrypt(String data) {
        StringBuilder encryptedData = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encryptedData.append((char) (data.charAt(i) ^ XOR_KEY));
        }
        return encryptedData.toString();
    }

    // XOR Decryption method (Same as encryption since XOR is reversible)
    public static String xorDecrypt(String encryptedData) {
        StringBuilder decryptedData = new StringBuilder();
        for (int i = 0; i < encryptedData.length(); i++) {
            decryptedData.append((char) (encryptedData.charAt(i) ^ XOR_KEY));
        }
        return decryptedData.toString();
    }

    // Method to encrypt and write CSV
    public static void encryptAndWriteCSV(List<String[]> data, String outputFile) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            for (String[] row : data) {
                // Encrypt sensitive fields (Email and Salary)
                row[EMAIL_INDEX] = xorEncrypt(row[EMAIL_INDEX]);
                row[SALARY_INDEX] = xorEncrypt(row[SALARY_INDEX]);
                writer.writeNext(row);
            }
            System.out.println("Encrypted CSV written successfully.");
        }
    }

    // Method to read and decrypt CSV
    public static void readAndDecryptCSV(String inputFile) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {
            List<String[]> rows = reader.readAll();

            for (String[] row : rows) {
                // Decrypt sensitive fields (Email and Salary)
                row[EMAIL_INDEX] = xorDecrypt(row[EMAIL_INDEX]);
                row[SALARY_INDEX] = xorDecrypt(row[SALARY_INDEX]);
                // Print the decrypted data
                System.out.println(String.join(", ", row));
            }
            System.out.println("Decrypted CSV read successfully.");
        }
    }

    public static void main(String[] args) throws IOException, CsvException {
        // Sample data with sensitive fields
        List<String[]> data = List.of(
                new String[]{"1", "John Doe", "john.doe@example.com", "50000"},
                new String[]{"2", "Jane Smith", "jane.smith@example.com", "60000"}
        );

        // Encrypt and write CSV
        encryptAndWriteCSV(data, "encrypted_data.csv");

        // Read and decrypt the CSV
        readAndDecryptCSV("encrypted_data.csv");
    }
}
