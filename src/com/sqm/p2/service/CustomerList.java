package com.sqm.p2.service;

import com.sqm.p2.bean.Customer;

/**
 * @author: sqm
 * @date: 2020/8/10 18:08
 * @description:
 */
public class CustomerList {
    private Customer[] customers; //用来保存客户对象的数组
    private int total = 0; //记录已保存客户对象的数量

    public CustomerList(int totalCustomer){
        //操作数组前初始化
        customers = new Customer[totalCustomer];
    }

    public boolean addCustomer(Customer customer){
        if (total >= customers.length) {
            return false;
        }

        customers[total] = customer;
        total++;
        return true;
    }


    public boolean replaceCustomer(int index, Customer cust){
        if (index < 0 && index >= total) {
            return false;
        }

        customers[index] = cust;
        return true;

    }

    public boolean deleteCustomer(int index){
        if (index < 0 && index >= total) {
            return false;
        }
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }

        //最后有数据的元素需要置空
//        customers[total - 1] = null;
//        total--;
        customers[--total] = null;
        return true;
    }


    public Customer[] getAllCustomers(){
//        return customers;
        Customer[] custs = new Customer[total];
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i]; //复制地址值
        }
        return custs;
    }

    public Customer getCustomer(int index){
        if (index < 0 && index >= total) {
            return null;
        }

        return customers[index];

    }

    public int getTotal(){
        return total;
    }


}
