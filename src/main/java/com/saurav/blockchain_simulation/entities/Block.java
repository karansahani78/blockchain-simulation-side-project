package com.saurav.blockchain_simulation.entities;

import jakarta.persistence.*;
import java.security.MessageDigest;
import java.time.Instant;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int index;
    private String timestamp;
    private String transactions;
    private String previousHash;
    private String currentHash;
    private int nonce;

    public Block(int index, String transactions, String previousHash) {
        this.index = index;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timestamp = Instant.now().toString();
        this.currentHash = calculateHash();
    }

    public Block() {}

    public String calculateHash() {
        String dataToHash = index + timestamp + transactions + previousHash + nonce;
        return applySHA256(dataToHash);
    }

    public void mineBlock(int difficulty) {
        String target = "0".repeat(difficulty);
        while (!currentHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currentHash = calculateHash();
        }
    }

    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    // Getter methods
    public int getIndex() {
        return index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTransactions() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }
}
