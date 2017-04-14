package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;


public class Generator {
    private static final String dbPackageName="com.goudan.greendao.db";  //指定目录的包名
    private static final String daoPackageName="com.goudan.greendao.db.dao";//指定dao层模板的包
    private static final String entityPackageName="com.goudan.greendao.db.entity";//指定实体类的包

    private final static int VERSION_ONE = 1;
    private final static int VERSION_TWO = 2;

    public static void main(String[] args) throws Exception {
//        Schema schema = generateFirst();
        Schema schema = generateSecondary();
        new DaoGenerator().generateAll(schema,"D:\\Samples\\Rxjava\\android-greendao\\src\\main\\java");
    }

    //生成第一代版本数据库
    private static Schema generateFirst(){
        Schema schema = new Schema(VERSION_ONE, dbPackageName);
        schema.setDefaultJavaPackageDao(dbPackageName);
        Entity student = schema.addEntity("Student");//名称
        student.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        student.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录
        student.addLongProperty("studentId").primaryKey();//主键
        student.addStringProperty("name");
        student.addStringProperty("studentClass");

        return schema;
    }

    //生成第二代版本数据库
    private static Schema generateSecondary(){
        //创建第一张表
        Schema schema = new Schema(VERSION_TWO, dbPackageName);
        schema.setDefaultJavaPackageDao(dbPackageName);
        Entity student = schema.addEntity("Student");//名称
        student.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        student.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录
        student.implementsSerializable();
        //这里添加一个studentId外键,同时，它也是第一张表的主键
        student.addLongProperty("studentId").primaryKey();//主键
        student.addStringProperty("name");
        student.addStringProperty("studentClass");



        //版本二，创建第二张表
        Entity relation = schema.addEntity("StudentRelation");
        relation.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        relation.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录
        relation.implementsSerializable();
        relation.addIdProperty().primaryKey().autoincrement();
        //指向第一张表的外键
        Property studentId = relation.addLongProperty("studentId").getProperty();
        relation.addStringProperty("name");
        relation.addIntProperty("age");
        relation.addStringProperty("phone");


        //这两个表建立1:n的关系，并设置关联字段。
        relation.addToOne(student, studentId);
        student.addToMany(relation,studentId);

        return schema;
    }

}
