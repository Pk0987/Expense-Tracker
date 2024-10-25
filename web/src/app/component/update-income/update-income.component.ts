import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SiteNgZorroAntdModule } from '../../nz-zorro.module';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from '../../service/income.service';
import { error } from '@ant-design/icons-angular';

@Component({
  selector: 'app-update-income',
  standalone: true,
  imports: [SiteNgZorroAntdModule,CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './update-income.component.html',
  styleUrl: './update-income.component.scss'
})
export class UpdateIncomeComponent {

  incomeForm!:FormGroup;
  incomes:any;

  categoryList:any[]=[
    "Salary",
    "Business",
    "Bitcoin",
    "Stocks",
    "Investment",
    "Rental",
    "Savings",
    "Youtube",
    "Freelance",
    "Others"
  ];

  id:number= this.activatedRoute.snapshot.params['id'];
  constructor(
    private fb: FormBuilder,
    private incomeService:IncomeService,
    private message:NzMessageService,
    private router: Router,
    private activatedRoute:ActivatedRoute,
  ){}

  ngOnInit(): void {
    this.incomeForm = this.fb.group({
      title:[null, [Validators.required]],
      description:[null, [Validators.required]],
      amount:[null, [Validators.required]],
      date:[null, [Validators.required]],
      category:[null, [Validators.required]]
    });
    this.getIncomeById();
  }

  getIncomeById(){
    this.incomeService.getIncomeById(this.id).subscribe((data)=>{
      this.incomeForm.patchValue(data);
  },error=>{
    this.message.error('Income Not Found',{nzDuration:5000});
  });
  }

  submitForm() {
    this.incomeService.updateIncome(this.id,this.incomeForm.value).subscribe((data)=>{
      this.message.success( 'Income Updated Successfully',{nzDuration:5000});
      this.router.navigate(['/income']);
    },error=>{
      this.message.error('Please fill all the fields',{nzDuration:5000}); 
   });
  }
}
