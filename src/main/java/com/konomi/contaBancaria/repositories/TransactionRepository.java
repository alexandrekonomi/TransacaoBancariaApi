package com.konomi.contaBancaria.repositories;

import com.konomi.contaBancaria.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
