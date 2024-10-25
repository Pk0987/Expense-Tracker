package com.springproject.ExpenseTracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/monitor")
    public String monitor(){
//        try{
//            boolean val = true;
//            while (val){
//                Runnable r =() ->{
//                    while (true){
//                        System.out.print("Thread Running...  ");
//                    }
//                };
//            new Thread(r).start();
//            Thread.sleep(5000);
//            }
//        }catch (Exception e){
//            return "Hello Wolrd";
//        }
        return "Heyy";
    };
}
