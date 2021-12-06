package com.company.ORMexample;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        TransactionHistory sangeeta = new TransactionHistory(15331, "Sangeeta","Credit",10000);
        TransactionHistory neha = new TransactionHistory(14531, "Neha","Credit",7000);
        TransactionHistory mohit = new TransactionHistory(43211, "Mohit","Debit",2000);
        TransactionHistory josh = new TransactionHistory(65432, "Josh","Debit",9000);

        Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();

        hibernate.write(sangeeta);
        hibernate.write(neha);
        hibernate.write(mohit);
        hibernate.write(josh);

        TransactionHistory t1 = hibernate.read(TransactionHistory.class, 1L);
        System.out.println(t1);
    }
}
