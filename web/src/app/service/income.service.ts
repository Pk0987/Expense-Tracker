import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  constructor(private http:HttpClient) { }


  postIncome(incomeDto:any){
    return this.http.post(BASIC_URL+`api/income`,incomeDto);
  }

  getAllIncomes(){
    return this.http.get(BASIC_URL+`api/income/all`);
  }

  getIncomeById(id:number){
    return this.http.get(BASIC_URL+`api/income/${id}`);
  }  
  updateIncome(id:number,incomeDto:any){
    return this.http.put(BASIC_URL+`api/income/update/${id}`,incomeDto);
  }
  deleteIncome(id:number){
    return this.http.delete(BASIC_URL+`api/income/delete/${id}`);
  }
}
