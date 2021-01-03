package com.company;

import com.company.entity.Store;
import com.company.service.UserService;

import java.util.Scanner;

public class Main {

    private static UserService userService= new UserService();

    public static void main(String[] args) throws Exception {
        userService.setUserNumber();
        while(true){
            System.out.println(
                    "Select operation: \n" +
                            "1. Register user \n" +
                            "2. Delete user \n" +
                            "3. Find user by name and surname \n" +
                            "4. Update user \n" +
                            "5. Show all users \n" +
                            "6. Change user money \n"
            );
            int operationNumber = new Scanner(System.in).nextInt();
            if(operationNumber==1){
                userService.requireFieldsAndAdd();
            } else if(operationNumber==2){
                userService.requireIdAndRemove();
            } else if(operationNumber==3) {
                userService.findByNameAndSurname();
            } else if(operationNumber==4) {
                userService.requireFieldsAndUpdate();
            }else if(operationNumber==5) {
                userService.showAll();
            } else if(operationNumber==6){
                userService.changePrize();
            }else{
                System.out.println("operation is wrong");
            }

        }
    }
}
