package com.goudan.greendao.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.goudan.greendao.db.entity.Student;
import com.goudan.greendao.db.entity.StudentRelation;

import com.goudan.greendao.db.dao.StudentDao;
import com.goudan.greendao.db.dao.StudentRelationDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig studentRelationDaoConfig;

    private final StudentDao studentDao;
    private final StudentRelationDao studentRelationDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        studentRelationDaoConfig = daoConfigMap.get(StudentRelationDao.class).clone();
        studentRelationDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        studentRelationDao = new StudentRelationDao(studentRelationDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(StudentRelation.class, studentRelationDao);
    }
    
    public void clear() {
        studentDaoConfig.getIdentityScope().clear();
        studentRelationDaoConfig.getIdentityScope().clear();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public StudentRelationDao getStudentRelationDao() {
        return studentRelationDao;
    }

}