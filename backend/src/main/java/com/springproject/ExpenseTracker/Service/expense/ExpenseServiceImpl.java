package com.springproject.ExpenseTracker.Service.expense;

import com.springproject.ExpenseTracker.Dto.ExpenseDto;
import com.springproject.ExpenseTracker.Entity.Expense;
import com.springproject.ExpenseTracker.Repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDto expenseDto){
        return saveOrUpdateExpense(new Expense(),expenseDto);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id){
        Optional<Expense> expenseById = expenseRepository.findById(id);
        if (expenseById.isPresent()){
            return expenseById.get();
        }else {
            throw new EntityNotFoundException("Expense in not present with id" + id);
        }
    }

    public Expense updateExpense(Long id, ExpenseDto expenseDto){
        Optional<Expense> updatedExp = expenseRepository.findById(id);
        if(updatedExp.isPresent()){
            return saveOrUpdateExpense(updatedExp.get(),expenseDto);
        }else {
            throw new EntityNotFoundException("Expense in not present with id" + id);
        }
    }

    public void deleteById(Long id){
        Optional<Expense> deleteExp = expenseRepository.findById(id);
        if(deleteExp.isPresent()){
            expenseRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Expense in not present with id" + id);
        }
    }
}
