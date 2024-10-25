package com.springproject.ExpenseTracker.Service.expense;

import com.springproject.ExpenseTracker.Dto.ExpenseDto;
import com.springproject.ExpenseTracker.Entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDto expenseDto);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, ExpenseDto expenseDto);
    void deleteById(Long id);
}
