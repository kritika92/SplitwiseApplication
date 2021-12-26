package com.example.demo.Splitwise.Repository;

import com.example.demo.Splitwise.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    public List<Balance> findByOweeId(String oweeId);
    public List<Balance> findByPayerId(String payerId);

}
