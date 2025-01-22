package com.saurav.blockchain_simulation.repository;
import com.saurav.blockchain_simulation.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockchainRepository extends JpaRepository<Blockchain, Integer> {}
