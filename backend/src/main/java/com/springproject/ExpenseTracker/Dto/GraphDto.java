package com.springproject.ExpenseTracker.Dto;

import com.springproject.ExpenseTracker.Entity.Expense;
import com.springproject.ExpenseTracker.Entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    public List<Expense> expenseList;

    public List<Income> incomeList;
}
