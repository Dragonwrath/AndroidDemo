package com.yang.rxjava.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private volatile static DBManager mInstance;
    private final static String DB_NAME = "test";


    private Context mContext;

    private DBManager(Context context) {
        this.mContext = context;
    }

    public static DBManager getInstance(Context context) {
        synchronized (DBManager.class) {
            if (mInstance == null) {
                mInstance = new DBManager(context);
            }
        }
        return mInstance;

    }


    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private SQLiteDatabase db;

    public void init() {
        if (mDaoMaster != null)
            return;

        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
        db = mDevOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
   }

    public void destroy() {
        try {
            if (mDaoMaster != null) {
                mDaoMaster.getDatabase().close();
                mDaoMaster = null;
            }

            if (mDevOpenHelper != null) {
                mDevOpenHelper.close();
                mDevOpenHelper = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DaoSession newSession() {
        DaoSession session = null;

        if (mDaoMaster != null) {
            session = mDaoMaster.newSession();
        }
        return session;
    }
}
