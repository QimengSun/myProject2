package com.sqm.p2.ui;

import com.sqm.p2.bean.Customer;
import com.sqm.p2.service.CustomerList;
import com.sqm.p2.util.CMUtility;

/**
 * @author: sqm
 * @date: 2020/8/10 18:09
 * @description:
 */
public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public void enterMainMenu(){
    boolean isFlag = true;
    while(isFlag) {
        System.out.println("\n-----------------客户信息管理软件-----------------\n");
        System.out.println("                   1 添 加 客 户");
        System.out.println("                   2 修 改 客 户");
        System.out.println("                   3 删 除 客 户");
        System.out.println("                   4 客 户 列 表");
        System.out.println("                   5 退       出\n");
        System.out.print("                   请选择(1-5)：");

        char menu = CMUtility.readMenuSelection();
        switch(menu){
            case '1':
                addNewCustomer();
                break;
            case '2':
                modifyCustomer();
                break;
            case '3':
                deleteCustomer();
                break;
            case '4':
                listAllCustomers();
                break;
            case '5':
                System.out.println("确认是否退出(Y/N):");
                char isExit = CMUtility.readConfirmSelection();
                if (isExit == 'Y') {
                    isFlag = false;
                }
        }

//        isFlag = false;
    }

    }

    private void addNewCustomer(){
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(5);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(15);
        System.out.print("邮箱：");
        String email = CMUtility.readString(20);

        //将上述对象封装
        Customer customer = new Customer(name,gender,age,phone,email);

        //添加
        boolean isSuccess = customerList.addCustomer(customer);
        if (isSuccess) {
            System.out.println("---------------添加成功---------------");
        }else{
            System.out.println("------------已满，添加失败---------------");
        }
    }

    private void modifyCustomer(){
//        System.out.println("mod");
        System.out.println("---------------------修改客户---------------------");
        int index;
        int number;
        Customer cust;
        for(;;){
            System.out.print("请选择待修改客户编号(-1退出)：");
            number = CMUtility.readInt();
            if (number == -1) {
                // break;//不能使用break
                return;
            }
            // 对于用户来讲，我们让index 默认从1开始。
            cust = customerList.getCustomer(number - 1);
            if (cust == null) {//未找到用户、则无法修改数据
                System.out.println("无法找到指定客户！");
            } else {//找到了相应客户
                break;
            }
        }

        // 修改用户的信息
        System.out.print("姓名(" + cust.getName() + "):");
        // 如果用户输入了姓名，则返回用户输入的信息，如果用户没有输入，直接回车，则返回customer.getName()
        String name = CMUtility.readString(5, cust.getName());

        System.out.print("性别(" + cust.getGender() + "):");
        char gender = CMUtility.readChar(cust.getGender());

        System.out.print("年龄(" + cust.getAge() + "):");
        int age = CMUtility.readInt(cust.getAge());

        System.out.print("电话(" + cust.getPhone() + "):");
        String phone = CMUtility.readString(13, cust.getPhone());

        System.out.print("邮箱(" + cust.getEmail() + "):");
        String email = CMUtility.readString(15, cust.getEmail());

        // 获取用户输入的属性以后，得到的最新的Customer对象
        Customer newCust = new Customer(name, gender, age, phone, email);

        customerList.replaceCustomer(number,newCust);

        // 对于用户来讲，我们让index 默认从1开始。

        boolean isReplaced = customerList.replaceCustomer(number -1, newCust);
        if (isReplaced) {
            System.out
                    .println("---------------------修改完成---------------------");
        } else {
            System.out
                    .println("---------------------修改失败---------------------");
        }



    }

    private void deleteCustomer(){
//        System.out.println("del");
        Customer customer;
        int number;
        for (;;) {

            System.out.print("请选择待删除客户编号(-1退出)：");
            // 针对普通用户来讲：此index从 1开始
            number = CMUtility.readInt();
            if (number == -1) {
                return;
            }

            customer = customerList.getCustomer(number-1);
            if (customer == null) {
                System.out.println("无法找到指定客户");
            }else{
                break;
            }
        }

            //删除操作

            System.out.println("是否确认删除(Y/N)");
            char isDelete =CMUtility.readConfirmSelection();
            if (isDelete == 'Y') {
                boolean deleteSuccess = customerList.deleteCustomer(number - 1);
                if (deleteSuccess) {
                    System.out.println("---------------------删除完成---------------------");
                } else {
                    System.out.println("---------------------删除失败---------------------");
                }
            }

    }


    private void listAllCustomers(){
//        System.out.println("list");
        System.out.println("------------------客户列表------------------");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("没有客户记录！");
        }else{
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
            Customer[] custs = customerList.getAllCustomers();
            for (int i = 0; i < total; i++) {
                Customer cust = custs[i];
                // 方式一
                System.out.println((i + 1) + "\t" + cust.getName() + "\t"
                        + cust.getGender() + "\t" + cust.getAge() + "\t"
                        + cust.getPhone() + "\t\t" + cust.getEmail());
                //方式二：
            }


            }


        System.out.println("----------------客户列表完成------------------");

    }

    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }


}
