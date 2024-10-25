package com.springproject.ExpenseTracker.Service.Stats;

import com.springproject.ExpenseTracker.Dto.GraphDto;
import com.springproject.ExpenseTracker.Dto.StatsDto;
import com.springproject.ExpenseTracker.Entity.Expense;
import com.springproject.ExpenseTracker.Entity.Income;
import com.springproject.ExpenseTracker.Repository.ExpenseRepository;
import com.springproject.ExpenseTracker.Repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final ExpenseRepository expenseRepository;

    private final IncomeRepository incomeRepository;

    public GraphDto getChartData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDto graphDto = new GraphDto();
        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate,endDate));

        return graphDto;
    }

    public StatsDto getStats(){
        Double totalIncome = incomeRepository.sumAllAmounts();
        Double totalExpense = expenseRepository.sumAllAmounts();

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDto statsDto = new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDto::setLatestIncome);
        optionalExpense.ifPresent(statsDto::setLatestExpense);

        statsDto.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList= expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        return statsDto;
    }
}
