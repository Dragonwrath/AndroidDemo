package com.example.db;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MicroLinkNewGenerator {
    private final static String rootPackageName = "D:\\microlink-app\\microlink\\app\\src\\main\\java";
    private final static String dbPackageName = "com.junwtech.microlink.db"; //指定目录的包名
    private final static String daoPackageName = "com.junwtech.microlink.db.dao"; //
    private final static String entityPackageName = "com.junwtech.microlink.db.entity"; //entity目录的包名
    private final static int VERSION_ONE = 1;
    private final static int VERSION_TWO = 2;


    public static void main(String[] args) throws Exception {
        Schema schema = generataVersionOne();
        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, rootPackageName);

    }

    private static Schema generataVersionOne() {
        Schema schema = new Schema(VERSION_ONE, dbPackageName);
        schema.setDefaultJavaPackageDao(dbPackageName);


        Entity contactRecordEntity = schema.addEntity("ContactRecordEntity");
        contactRecordEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        contactRecordEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        contactRecordEntity.addLongProperty("id").primaryKey();
        contactRecordEntity.addIntProperty("type");
        contactRecordEntity.addStringProperty("ap_id");
        contactRecordEntity.addStringProperty("sender_uuid");
        contactRecordEntity.addStringProperty("recv_ap_id");
        contactRecordEntity.addStringProperty("receiver_uuid");
        contactRecordEntity.addStringProperty("avatar");
        contactRecordEntity.addStringProperty("name");
        contactRecordEntity.addStringProperty("last_content");
        contactRecordEntity.addIntProperty("content_type");
        contactRecordEntity.addStringProperty("last_time");
        contactRecordEntity.addIntProperty("unread_num");

        Entity groupEntity = schema.addEntity("GroupEntity");
        groupEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        groupEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        groupEntity.addLongProperty("id").primaryKey();
        groupEntity.addStringProperty("ap_id");
        groupEntity.addStringProperty("group_id");
        groupEntity.addStringProperty("group_name");
        groupEntity.addStringProperty("creator_uuid");
        groupEntity.addStringProperty("creator_avatar");
        groupEntity.addStringProperty("create_time");
        groupEntity.addIntProperty("flag");

        Entity groupUserRelationEntity = schema.addEntity("GroupUserRelationEntity");
        groupUserRelationEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        groupUserRelationEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        groupUserRelationEntity.addLongProperty("id").primaryKey();
        groupUserRelationEntity.addStringProperty("group_ap_id");
        groupUserRelationEntity.addStringProperty("group_id");
        groupUserRelationEntity.addStringProperty("user_ap_id");
        groupUserRelationEntity.addStringProperty("user_uuid");

        Entity imageEntity = schema.addEntity("ImageEntity");
        imageEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        imageEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        imageEntity.addLongProperty("id").primaryKey();
        imageEntity.addStringProperty("name");
        imageEntity.addStringProperty("type");
        imageEntity.addIntProperty("num");
        imageEntity.addStringProperty("picName");

        Entity messageEntity = schema.addEntity("MessageEntity");
        messageEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        messageEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        messageEntity.addLongProperty("id").primaryKey();
        messageEntity.addIntProperty("msg_type");
        messageEntity.addStringProperty("content");
        messageEntity.addStringProperty("time");
        messageEntity.addStringProperty("sender_uuid");
        messageEntity.addStringProperty("sender_ap_id");
        messageEntity.addLongProperty("_index");
        messageEntity.addIntProperty("receiver_type");
        messageEntity.addStringProperty("receiver_ap_id");
        messageEntity.addStringProperty("receiver_uuid");

        Entity userEntity = schema.addEntity("UserEntity");
        userEntity.setJavaPackageDao(daoPackageName); //设置对应的实体类的DAO文件到指定目录
        userEntity.setJavaPackage(entityPackageName);//设置对应的实体类文件到指定目录

        userEntity.addLongProperty("id").primaryKey();
        userEntity.addStringProperty("uuid");
        userEntity.addStringProperty("ap_id");
        userEntity.addIntProperty("role");
        userEntity.addStringProperty("nickname");
        userEntity.addStringProperty("avatar");
        userEntity.addStringProperty("last_in_time");
        userEntity.addIntProperty("is_register_out");
        userEntity.addIntProperty("is_prohibit");
        userEntity.addFloatProperty("lon");
        userEntity.addFloatProperty("lat");

        return schema;
    }
}
