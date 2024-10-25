package com.springproject.ExpenseTracker.Repository;

import com.springproject.ExpenseTracker.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

@Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllAmounts();

    Optional<Income> findFirstByOrderByDateDesc();
}
