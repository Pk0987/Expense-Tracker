package com.springproject.ExpenseTracker.Service.income;

import com.springproject.ExpenseTracker.Dto.IncomeDto;
import com.springproject.ExpenseTracker.Entity.Income;
import com.springproject.ExpenseTracker.Repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDto incomeDto){
        return saveOrUpdateIncome(new Income(),incomeDto);
    }


    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setAmount(incomeDto.getAmount());
        income.setCategory(incomeDto.getCategory());
        income.setDescription(incomeDto.getDescription());

        return incomeRepository.save(income);
    }

    public List<IncomeDto> getAllIncomes(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public Income updateIncome(Long id, IncomeDto incomeDto){
        Optional<Income> updatedIncome = incomeRepository.findById(id);
        if(updatedIncome.isPresent()){
            return saveOrUpdateIncome(updatedIncome.get(),incomeDto);
        }else {
            throw new EntityNotFoundException("Income is not present with id : "+id);
        }
    }

    public IncomeDto getIncomeById(Long id){
        Optional<Income> getIncomeId = incomeRepository.findById(id);
        if(getIncomeId.isPresent()){
            return getIncomeId.get().getIncomeDto();
        }else {
            throw new EntityNotFoundException("Income is not present with id : "+id);
        }
    }

    public void deleteById(Long id){
        Optional<Income> deleteIncomeId = incomeRepository.findById(id);
        if(deleteIncomeId.isPresent()){
            incomeRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Income is not present with id : "+id);
        }
    }
}
