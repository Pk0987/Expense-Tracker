package com.springproject.ExpenseTracker.Controller;


import com.springproject.ExpenseTracker.Dto.IncomeDto;
import com.springproject.ExpenseTracker.Entity.Income;
import com.springproject.ExpenseTracker.Service.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<Income> postIncome(@RequestBody IncomeDto incomeDto){
        Income createdIncome = incomeService.postIncome(incomeDto);
        if(createdIncome != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody IncomeDto incomeDto){
       try {
           return ResponseEntity.ok(incomeService.updateIncome(id,incomeDto));
       }catch (EntityNotFoundException exception){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            incomeService.deleteById(id);
            return ResponseEntity.ok("Deleted Successfully..!");
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

}
