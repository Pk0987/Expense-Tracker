import { Component } from '@angular/core';
import { SiteNgZorroAntdModule } from '../../nz-zorro.module';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from '../../service/income.service';
import { error } from '@ant-design/icons-angular';

@Component({
  selector: 'app-income',
  standalone: true,
  imports: [
    SiteNgZorroAntdModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
  ],
  templateUrl: './income.component.html',
  styleUrl: './income.component.scss',
})
export class IncomeComponent {
  incomeForm!: FormGroup;
  incomes: any;

  categoryList: any[] = [
    'Salary',
    'Business',
    'Bitcoin',
    'Stocks',
    'Investment',
    'Rental',
    'Savings',
    'Youtube',
    'Freelance',
    'Others',
  ];

  constructor(
    private fb: FormBuilder,
    private incomeService: IncomeService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.incomeForm = this.fb.group({
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      amount: [null, [Validators.required]],
      date: [null, [Validators.required]],
      category: [null, [Validators.required]],
    });
    this.getAllIncomes();
  }

  submitForm() {
    this.incomeService.postIncome(this.incomeForm.value).subscribe(
      (data) => {
        this.message.success('Income Added Successfully', { nzDuration: 5000 });
        this.getAllIncomes();
        this.incomeForm.reset();
      },
      (error) => {
        this.message.error('Please fill all the fields', { nzDuration: 5000 });
      }
    );
  }

  getAllIncomes() {
    this.incomeService.getAllIncomes().subscribe((data) => {
      this.incomes = data;
      console.log(data);
    });
  }

  deleteincome(id: any) {
    this.incomeService.deleteIncome(id).subscribe(
      (data) => {
        this.message.success('Income Deleted Successfully', {
          nzDuration: 5000,
        });
        this.getAllIncomes();
      },
      (error) => {
        this.message.error('Error in deleting income', { nzDuration: 5000 });
      }
    );
  }

  updateincome(id: any) {
    this.router.navigate([`income/${id}/edit`]);
  }
}
