package com.springproject.ExpenseTracker.Dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
}
