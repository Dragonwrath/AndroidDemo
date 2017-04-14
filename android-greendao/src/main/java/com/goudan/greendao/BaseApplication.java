package com.goudan.greendao;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.goudan.greendao.db.DaoMaster;
import com.goudan.greendao.db.DaoSession;
import com.goudan.greendao.db.dao.StudentDao;
import com.goudan.greendao.db.dao.StudentRelationDao;

public class BaseApplication extends Application {
    private final static String DB_NAME = "GouDan";
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper devOpenHelper = new DBHelper(this, DB_NAME, null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    public static StudentDao getStudentDao(){
        return mDaoSession.getStudentDao();
    }

    public static StudentRelationDao getStudentRelationDao() {
        return mDaoSession.getStudentRelationDao();
    }



    //OpenHelper对象，帮助数据库升级以及进行相应的创建
    private class DBHelper extends DaoMaster.OpenHelper{

        DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch (oldVersion) {
                case Constants.DB_VERSION_ONE: //升级
                    StudentRelationDao.createTable(db,true);
                    break;
            }
        }
    }
}
