package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GeneratorTest {
    public static final String dbPackageName="com.yang.rxjava.db";  //指定目录的包名
    public static final String DaoPackageName="com.yang.rxjava.db.dao";//指定dao层模板的包
    public static final String EntityPackageName="com.yang.rxjava.db.entity";//指定实体类的包

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(2, dbPackageName);
        schema.setDefaultJavaPackageDao(dbPackageName);
        addTest(schema);
        addBook(schema);
        new DaoGenerator().generateAll(schema,"D:\\Samples\\Rxjava\\app\\src\\main\\java\\");
    }

    private static void addTest(Schema schema) {
        Entity testData = schema.addEntity("TestData");//名称
        testData.setJavaPackageDao(DaoPackageName); //设置对应的实体类的DAO文件到指定目录
        testData.setJavaPackage(EntityPackageName);//设置对应的实体类文件到指定目录
        testData.addIdProperty().primaryKey().autoincrement();//主键
        testData.addStringProperty("testString");
        testData.addLongProperty("testLong");
        testData.addDateProperty("testDate");
        testData.addIntProperty("testInt");
        testData.addBooleanProperty("testBoolean");
    }

    private static void addBook(Schema schema) {
        Entity testData = schema.addEntity("Book");//名称
        testData.setJavaPackageDao(DaoPackageName); //设置对应的实体类的DAO文件到指定目录
        testData.setJavaPackage(EntityPackageName);//设置对应的实体类文件到指定目录
        testData.addIdProperty().autoincrement();
        testData.addLongProperty("time");
        testData.addStringProperty("publishTime");
    }


}
