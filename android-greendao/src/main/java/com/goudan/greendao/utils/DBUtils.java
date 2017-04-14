package com.goudan.greendao.utils;


import com.goudan.greendao.BaseApplication;
import com.goudan.greendao.db.entity.Student;
import com.goudan.greendao.db.entity.StudentRelation;

import java.util.List;

public final class DBUtils {
    private DBUtils() {
        throw new AssertionError("this class couldn't be initial");
    }

    public static void addStudent(Student student) {
        BaseApplication.getStudentDao().insertOrReplace(student);
    }

    public static List<Student> getStudents(){
        return BaseApplication.getStudentDao().loadAll();
    }

    public static void addRelation(StudentRelation relation){
        BaseApplication.getStudentRelationDao().insertOrReplace(relation);
    }

}
