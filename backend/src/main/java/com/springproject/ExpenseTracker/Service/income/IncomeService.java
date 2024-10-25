package com.springproject.ExpenseTracker.Service.income;

import com.springproject.ExpenseTracker.Dto.IncomeDto;
import com.springproject.ExpenseTracker.Entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDto incomeDto);
    List<IncomeDto> getAllIncomes();
    Income updateIncome(Long id, IncomeDto incomeDto);
    IncomeDto getIncomeById(Long id);
    void deleteById(Long id);
}
