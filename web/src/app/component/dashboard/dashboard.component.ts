import { Component, ElementRef, ViewChild, viewChild } from '@angular/core';
import { StatsService } from '../../service/stats.service';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { SiteNgZorroAntdModule } from '../../nz-zorro.module';
import { CommonModule, NgStyle } from '@angular/common';
import Chart from 'chart.js/auto';
import { CategoryScale } from 'chart.js/auto';

Chart.register(CategoryScale);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NzModalModule,SiteNgZorroAntdModule,NgStyle,CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  stats:any;
  expenses:any;
  incomes:any;

  gridStyle = {
    with: '25%',
    textAlign: 'center'
  };

  @ViewChild('incomeLineChartRef') private incomeLineChartRef:ElementRef;
  @ViewChild('expenseLineChartRef') private expenseLineChartRef:ElementRef;
  
  constructor(private statsService:StatsService){ }
  ngOnInit(): void {
    this.getStats();
    this.getChartData();
  }

  createLineChart(){
    const incomectx = this.incomeLineChartRef.nativeElement.getContext('2d');
    new Chart(incomectx, {
      type: 'line',
      data: {
        labels: this.incomes.map(income=>income.date),
        datasets: [{
          label: 'Income',
          data: this.incomes.map(income=>income.amount),
          borderWidth: 1,
          backgroundColor: 'rgba(80,200,120)',
          borderColor: 'rgba(0,100,0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    const expensectx = this.expenseLineChartRef.nativeElement.getContext('2d');
    new Chart(expensectx, {
      type: 'line',
      data: {
        labels: this.expenses.map(expense=>expense.date),
        datasets: [{
          label: 'Expense',
          data: this.expenses.map(expense=>expense.amount),
          borderWidth: 1,
          backgroundColor: 'rgba(255,0,0)',
          borderColor: 'rgba(255,0,0)',
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  
  }

  getStats(){
    this.statsService.getStats().subscribe((data)=>{
      console.log(data);
      this.stats = data;
    },(error)=>{
      console.log(error);
    });
  }

  getChartData(){
    debugger;
    this.statsService.getChartData().subscribe((res)=>{
      if(res.expenseList !== null && res.incomeList !== null){
        this.expenses = res.expenseList;
        this.incomes = res.incomeList;
        this.createLineChart();
      }
    },(error)=>{
      console.log(error);
    });
  }


}
