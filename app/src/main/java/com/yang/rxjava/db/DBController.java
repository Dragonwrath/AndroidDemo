package com.yang.rxjava.db;

import android.content.Context;

import com.yang.rxjava.db.dao.BookDao;
import com.yang.rxjava.db.dao.TestDataDao;
import com.yang.rxjava.db.entity.Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by JunWei on 2017/1/4.
 */

public class DBController {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private static DBController mInstance;
    private DaoSession mDaoSession;

    private BookDao bookDao;
    private TestDataDao testDao;

    private Context mContext;

    private SimpleDateFormat ymdHmsFormat;

    private DBController(Context context) {
        mContext = context;
        if (mDaoSession == null) mDaoSession = DBManager.getInstance(context).newSession();
        bookDao = mDaoSession.getBookDao();
        testDao = mDaoSession.getTestDataDao();

        ymdHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    public static DBController getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DBController(context);
        return mInstance;
    }

    public long addBook(Book book){
        if (bookDao != null ) {
             return bookDao.insertOrReplace(book);
        }
        return  -1;
    }

    public List<Book> queryBook() {
        if (bookDao == null) {
            return new ArrayList<>();
        }
        return bookDao.queryBuilder().build().list();
    }
}
