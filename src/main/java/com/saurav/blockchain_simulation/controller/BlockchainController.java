package com.saurav.blockchain_simulation.controller;

import com.saurav.blockchain_simulation.entities.Block;
import com.saurav.blockchain_simulation.entities.Blockchain;
import com.saurav.blockchain_simulation.repository.BlockchainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController {

    @Autowired
    private BlockchainRepository repository;

    @PostMapping("/add")
    @Operation(summary = "Add a new block", description = "Adds a new block with transaction data to the blockchain and mines it.")
    public Block addBlock(@RequestParam String transactions) {
        Blockchain blockchain = getBlockchain();

        Block previousBlock = blockchain.getBlocks().get(blockchain.getBlocks().size() - 1);
        Block newBlock = new Block(blockchain.getBlocks().size(), transactions, previousBlock.getCurrentHash());
        newBlock.mineBlock(blockchain.getDifficulty());

        blockchain.getBlocks().add(newBlock);
        repository.save(blockchain);

        return newBlock;
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate the blockchain", description = "Validates the integrity of the blockchain to check if it has been tampered with.")
    public boolean validateChain() {
        Blockchain blockchain = getBlockchain();
        return blockchain.isChainValid();
    }

    @GetMapping("/chain")
    @Operation(summary = "Get the complete blockchain", description = "Retrieves the full list of blocks in the blockchain.")
    public List<Block> getChain() {
        return getBlockchain().getBlocks();
    }

    private Blockchain getBlockchain() {
        return repository.findById(1).orElseGet(() -> {
            Blockchain blockchain = new Blockchain();
            repository.save(blockchain);
            return blockchain;
        });
    }
}
