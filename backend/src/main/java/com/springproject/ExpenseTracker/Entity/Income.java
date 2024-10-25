package com.springproject.ExpenseTracker.Entity;

import com.springproject.ExpenseTracker.Dto.IncomeDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;


    public IncomeDto getIncomeDto(){
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(id);
        incomeDto.setDate(date);
        incomeDto.setAmount(amount);
        incomeDto.setCategory(category);
        incomeDto.setTitle(title);
        incomeDto.setDescription(description);

        return incomeDto;
    }

}
