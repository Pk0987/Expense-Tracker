package com.springproject.ExpenseTracker.Service.Stats;

import com.springproject.ExpenseTracker.Dto.GraphDto;
import com.springproject.ExpenseTracker.Dto.StatsDto;

public interface StatsService {
    GraphDto getChartData();
    StatsDto getStats();
}
