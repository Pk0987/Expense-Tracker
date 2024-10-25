import { Component } from '@angular/core';
import { SiteNgZorroAntdModule } from '../../nz-zorro.module';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from '../../service/expense.service';

@Component({
  selector: 'app-update-expense',
  standalone: true,
  imports: [SiteNgZorroAntdModule,CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './update-expense.component.html',
  styleUrl: './update-expense.component.scss'
})
export class UpdateExpenseComponent {

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
  ];


  expenses: any;
  id:number= this.activatedRoute.snapshot.params['id'];
  
  
  constructor(private fb: FormBuilder,
    private expenseService:ExpenseService,
    private message:NzMessageService,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    
  ) { }
  
  ngOnInit(): void {
    this.expenseForm = this.fb.group({
      title:[null, [Validators.required]],
      description:[null, [Validators.required]],
      amount:[null, [Validators.required]],
      date:[null, [Validators.required]],
      category:[null, [Validators.required]]
    });
    this.getExpenseById();
  }
  
  
  
  getExpenseById(){
    this.expenseService.getExpenseById(this.id).subscribe((data)=>{
      this.expenseForm.patchValue(data);
    },error=>{
      this.message.error('Expense not found',{nzDuration:5000});
    });
  }

  submitForm() {
     this.expenseService.postExpense(this.expenseForm.value).subscribe((data)=>{
      this.message.success( 'Expense Updated Successfully',{nzDuration:5000});
      this.router.navigate(['expense']);
    },error=> {
      this.message.error('Please fill all the fields',{nzDuration:5000});
    });
    }
    

}

