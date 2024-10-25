package com.springproject.ExpenseTracker.Controller;

import com.springproject.ExpenseTracker.Dto.ExpenseDto;
import com.springproject.ExpenseTracker.Entity.Expense;
import com.springproject.ExpenseTracker.Service.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expense")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> postExpense(@RequestBody ExpenseDto dto){
        Expense createdExpense = expenseService.postExpense(dto);
        if (createdExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto){
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id,expenseDto));
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try{
            expenseService.deleteById(id);
            return ResponseEntity.ok("Deleted Successfull");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
