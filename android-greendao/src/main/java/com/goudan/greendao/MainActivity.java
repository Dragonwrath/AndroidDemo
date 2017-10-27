package com.goudan.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.goudan.greendao.db.entity.Student;
import com.goudan.greendao.db.entity.StudentRelation;
import com.goudan.greendao.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView)findViewById(R.id.list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mList.setAdapter(mAdapter);
        generateSecond();
    }

    //自定生成v1数据
    public void generateFirst(){
        for (Long i = 0L; i < 10L; i++) {
            Student student = new Student();
            student.setName("LiErGou-->"+i);
            student.setStudentId(i);
            student.setStudentClass("GouDan");
            DBUtils.addStudent(student);
        }
    }

    public void searchFirst(View view){
        List<Student> students = DBUtils.getStudents();
        List<String> strings = new ArrayList<>();
        if (students.size() > 0) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                strings.add(student.getName() + "--" + student.getStudentId() + "--" + student.getStudentClass());
            }
            mAdapter.clear();
            mAdapter.addAll(strings);
        }
    }


    //自动生成v2数据
    public void generateSecond(){
        for (Long i = 0L; i < 10L; i++) {
            Student student = new Student();
            student.setName("LiErGou-->"+i);
            student.setStudentId(i);
            student.setStudentClass("GouDan");
            for (int j = 0; j < 2; j++) {
                StudentRelation relation = new StudentRelation();
                relation.setStudentId(i);
                relation.setName("Li");
                relation.setAge(50);
                relation.setPhone(123456+"");
                DBUtils.addRelation(relation);
            }
            DBUtils.addStudent(student);
        }
    }


    public void searchSecondary(View view){
        List<Student> students = DBUtils.getStudents();
        List<String> strings = new ArrayList<>();
        if (students.size() > 0) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                List<StudentRelation> relations = student.getSetStudentRelationList();
                StudentRelation first = relations.get(0);
                StudentRelation second = relations.get(1);
                strings.add(student.getName() + "--" + student.getStudentId() + "--" + student.getStudentClass() + "\n"
                +first.getStudentId() +"--" + first.getName() +"--" + first.getPhone() +"--" + "\n"
                +second.getStudentId() +"--" + second.getName() +"--" + second.getPhone() +"--");
            }
            mAdapter.clear();
            mAdapter.addAll(strings);
        }
    }
}
