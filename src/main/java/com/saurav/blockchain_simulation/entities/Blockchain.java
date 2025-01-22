package com.saurav.blockchain_simulation.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Blockchain {

    @Id
    private int id = 1;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Block> blocks;

    private int difficulty;

    public Blockchain() {
        this.blocks = new ArrayList<>();
        this.difficulty = 4; // Example difficulty level
        this.blocks.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block(0, "Genesis Block", "0");
    }

    public boolean isChainValid() {
        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);

            if (!currentBlock.getCurrentHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
                return false;
            }
        }
        return true;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
