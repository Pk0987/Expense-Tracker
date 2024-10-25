package com.springproject.ExpenseTracker.Controller;


import com.springproject.ExpenseTracker.Dto.GraphDto;
import com.springproject.ExpenseTracker.Service.Stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {

    private final StatsService statsService;


    @GetMapping("/chart")
    public ResponseEntity<GraphDto> getChartData(){
        return ResponseEntity.ok(statsService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?> getStats(){
        return ResponseEntity.ok(statsService.getStats());
    }
}
