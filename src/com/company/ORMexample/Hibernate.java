package com.company.ORMexample;

import com.company.ORMexample.annotation.Column;
import com.company.ORMexample.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hibernate<T> {

    private Connection con;
    private AtomicLong id = new AtomicLong(0L);
    private static final String DATABASE_URL = "jdbc:h2:"+System.getProperty("user.home")+"/Documentos/proyectos/untitled/database/practice1";
    private static final String DATABASE_USER = "sa";
    private static final String DATABASE_PASS = "";

    private Hibernate() throws SQLException {
        this.con = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASS);
    }

    public static <T> Hibernate<T> getConnection() throws SQLException {
        return new Hibernate<T>();
    }

    public void write(T t) throws IllegalAccessException, SQLException {
        Class<?> clss = t.getClass();
        Field[] fields = clss.getDeclaredFields();
        Field pkey = null;
        List<Field> columns;
        StringJoiner joiner = new StringJoiner(",");

        pkey = Stream.of(fields)
                .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                .findFirst().orElseThrow(() -> new RuntimeException("PKEY NO PUEDE SER NULL"));
        columns = Stream.of(fields)
                .filter(f -> f.isAnnotationPresent(Column.class))
                .peek(f -> joiner.add(f.getName()))
                .collect(Collectors.toList());

        int number = fields.length;
        String qMarks = IntStream.range(0,number)
                .mapToObj(i -> "?")
                .collect(Collectors.joining(","));

        String sql = "INSERT INTO " + "TransactionHistory" + "(" + pkey.getName() + "," + joiner.toString()+")"+"VALUES ("+qMarks+")";

        PreparedStatement stmt = makePreparedStatement(sql, pkey,fields,t);
        stmt.executeUpdate();
    }

    private PreparedStatement makePreparedStatement(String sql, Field pkey, Field[] columns, T element) throws SQLException, IllegalAccessException {
        PreparedStatement ps = con.prepareStatement(sql);
        if(pkey.getType() == long.class){
            ps.setLong(1,this.id.incrementAndGet());
        }
        IntStream.range(1, columns.length).forEach(i -> setValueInStatement(ps, columns[i++],i++,element) );
        return ps;
    }

    private void setValueInStatement(PreparedStatement ps, Field field, int index, T element){
        field.setAccessible(true);
        try{
            if(field.getType() == int.class){
                ps.setInt(index, (int) field.get(element));
            }else
            if(field.getType() == String.class){
                ps.setString(index, (String)field.get(element));
            }
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

    }

    public T read(Class<T> clss, long id) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field pkField = Arrays.stream(clss.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No primary key detected."));

        String sql = "SELECT * from " + clss.getSimpleName() + " WHERE " + pkField.getName() + " = " + id;

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();

        T instance = clss.getConstructor().newInstance();

        long transactionId = resultSet.getInt(pkField.getName());
        pkField.setAccessible(true);
        pkField.set(instance,transactionId);
        Arrays.stream(clss.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> setField(field, instance, resultSet));
        return instance;
    }

    private void setField(Field field, T instance, ResultSet rs){
        field.setAccessible(true);
        try {
            if (field.getType() == int.class) {
                field.set(instance, rs.getInt(field.getName()));
            }
            if (field.getType() == String.class) {
                field.set(instance, rs.getString(field.getName()));
            }
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }
}
