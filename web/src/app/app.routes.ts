import { Routes } from '@angular/router';
import { ExpenseComponent } from './component/expense/expense.component';
import { UpdateExpenseComponent } from './component/update-expense/update-expense.component';
import { IncomeComponent } from './component/income/income.component';
import { UpdateIncomeComponent } from './component/update-income/update-income.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path : 'dashboard', 
        component: DashboardComponent
    },
    {
        path: 'expense',
        component: ExpenseComponent
    },{
        path: 'expense/:id/edit',
        component:UpdateExpenseComponent
    },{
        path:'income',
        component:IncomeComponent
    },{
        path:'income/:id/edit',
        component:UpdateIncomeComponent
    }
];
