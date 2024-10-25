import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from '../../service/expense.service';
import { Router } from '@angular/router';
import { SiteNgZorroAntdModule } from '../../nz-zorro.module';

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [ ReactiveFormsModule,FormsModule,CommonModule,SiteNgZorroAntdModule],
  templateUrl: './expense.component.html',
  styleUrl: './expense.component.scss'
})
export class ExpenseComponent {

  expenseForm!:FormGroup;

  categoryList:any[]=[
    "Education",
    "Entertainment",
    "Food",
    "Health",
    "Personal",
    "Shopping",
    "Transport",
    "Travel",
    "Clothing",
    "Others"
  ]


  expenses: any[]=[];

  constructor(private fb: FormBuilder,
    private expenseService:ExpenseService,
    private message:NzMessageService,
    private router:Router,
  ) { }
    
  ngOnInit(): void {
    this.getAllExpenses();
    this.expenseForm = this.fb.group({
      title:[null, [Validators.required]],
      description:[null, [Validators.required]],
      amount:[null, [Validators.required]],
      date:[null, [Validators.required]],
      category:[null, [Validators.required]]
    });
  }

  submitForm() {
      this.expenseService.postExpense(this.expenseForm.value).subscribe((data)=>{
        this.message.success( 'Expense Added Successfully',{nzDuration:5000});
        this.getAllExpenses();
        this.expenseForm.reset();
      },error=> {
      this.message.error('Please fill all the fields',{nzDuration:5000});
  });
  }

  getAllExpenses(){
    this.expenseService.getAllExpenses().subscribe((data)=>{
      this.expenses = data;
      console.log(data);
    });
  }

  updateExpense(id:number){
      this.router.navigate([`expense/${id}/edit`]);
  }


  deleteExpense(id:number){
    debugger;
    this.expenseService.deleteExpense(id).subscribe({
      next:(value)=>{
        this.message.success('Expense Deleted Successfully',{nzDuration:5000});
        this.getAllExpenses();
      },error:(error)=>{
        this.message.error('Error in deleting expense',{nzDuration:5000});
      }
    })
  }
}
