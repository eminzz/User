package com.company.service;

import com.company.entity.Store;
import com.company.entity.User;

import java.io.*;
import java.util.Scanner;

public class UserService {

    public static User[] userList;


    public void setUserNumber(){
        System.out.println("enter user number:");
        userList = new User[new Scanner(System.in).nextInt()];
    }

    public static int getLastIndex(){
        for (int i = 0;i < userList.length;i++ ){
            if (userList[i]==null){
                return i;
            }
        }
        return -1;
    }

    public void add(User user){
        int lastIndex = getLastIndex();
        userList[lastIndex] = user;
        user.setId(++lastIndex);
    }

    public void remove(User user){
        for(int i=0; i<userList.length; i++){
            if(userList[i]!=null && userList[i].getId()==user.getId()){
                userList[i]=null;
                break;
            }
        }
    }

    public User findById(int id){
        for(int i=0; i<userList.length; i++){
            if(userList[i]!=null && userList[i].getId()==id){
                return userList[i];
            }
        }
        return null;
    }

    public void requireFieldsAndAdd(){
        User user = new User();

        System.out.println("enter user name:");
        user.setName(new Scanner(System.in).nextLine());

        System.out.println("enter user surname:");
        user.setSurname(new Scanner(System.in).nextLine());

        System.out.println("enter user age:");
        user.setAge(new Scanner(System.in).nextInt());


        add(user);
        System.out.println("User successfully registered");
        System.out.println(user);
    }

    public void requireIdAndRemove(){
        System.out.println("enter user id:");
        int id= new Scanner(System.in).nextInt();
        User u = findById(id);
        if(u!=null){
            remove(u);
            System.out.println("successfully user deleted");
            System.out.println(u);
        } else{
            System.out.println("there is no such user");
        }
    }

    public void findByNameAndSurname(){
        System.out.println("enter user name:");
        String name = new Scanner(System.in).nextLine();

        System.out.println("enter user surname:");
        String surname = new Scanner(System.in).nextLine();

        User foundUser= null;
        for(int i=0; i< userList.length; i++){
            if(userList[i]==null) continue;
            if(userList[i].getName().equalsIgnoreCase(name) &&
            userList[i].getSurname().equalsIgnoreCase(surname)){
                foundUser=userList[i];
                break;
            }
        }
    }

    public void requireFieldsAndUpdate(){
        System.out.println("enter user id:");
        int id = new Scanner(System.in).nextInt();

        User u =findById(id);
        if(u==null){
            System.out.println("there is no such user");
            return;
        }

        System.out.println("enter field name:");
        String fieldName = new Scanner(System.in).nextLine();

        System.out.println("enter field value:");
        String fieldValue = new Scanner(System.in).nextLine();

        if(fieldName.equalsIgnoreCase("name")){
            u.setName(fieldValue);
        }else if(fieldName.equalsIgnoreCase("surname")){
            u.setSurname(fieldValue);
        }else if(fieldName.equalsIgnoreCase("age")) {
            u.setAge(Integer.parseInt(fieldValue));
        }
    }

    public void showAll(){
        for(int i=0; i< userList.length;i++){
            if(userList[i]==null) continue;
            System.out.println(userList[i]);
        }
    }

    public void changePrize(){
        System.out.println("enter user id who give money:");
        int id1 = new Scanner(System.in).nextInt();
        User userGivingMoney =findById(id1);

        System.out.println("enter user id who receive money:");
        int id2 = new Scanner(System.in).nextInt();
        User userReceivingMoney = findById(id2);

        System.out.println("enter money quantity:");
        double money = new Scanner(System.in).nextDouble();

        userGivingMoney.setPrize(userGivingMoney.getPrize()-money);
        userReceivingMoney.setPrize(userReceivingMoney.getPrize()+money);
    }

    public static void writeIntoFile(Store s) {
        try {
            File file = new File("files/user.txt");
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(s);
            o.close();
            f.close();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static Store readFromFileIO() throws Exception {
        try {
            File file = new File("Files/user.txt");
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Store pr1 = (Store) oi.readObject();
            oi.close();
            fi.close();
            return pr1;
        } catch (Exception ex){
            Store s = new Store();
            s.setList(new User[new Scanner(System.in).nextInt()]);
            return s;
        }
    }
}
