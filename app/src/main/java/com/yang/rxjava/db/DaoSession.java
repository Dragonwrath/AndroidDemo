package com.yang.rxjava.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.yang.rxjava.db.entity.TestData;
import com.yang.rxjava.db.entity.Book;

import com.yang.rxjava.db.dao.TestDataDao;
import com.yang.rxjava.db.dao.BookDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig testDataDaoConfig;
    private final DaoConfig bookDaoConfig;

    private final TestDataDao testDataDao;
    private final BookDao bookDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        testDataDaoConfig = daoConfigMap.get(TestDataDao.class).clone();
        testDataDaoConfig.initIdentityScope(type);

        bookDaoConfig = daoConfigMap.get(BookDao.class).clone();
        bookDaoConfig.initIdentityScope(type);

        testDataDao = new TestDataDao(testDataDaoConfig, this);
        bookDao = new BookDao(bookDaoConfig, this);

        registerDao(TestData.class, testDataDao);
        registerDao(Book.class, bookDao);
    }
    
    public void clear() {
        testDataDaoConfig.getIdentityScope().clear();
        bookDaoConfig.getIdentityScope().clear();
    }

    public TestDataDao getTestDataDao() {
        return testDataDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

}
