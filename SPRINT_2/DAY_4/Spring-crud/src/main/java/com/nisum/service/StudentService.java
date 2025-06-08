package com.nisum.service;
import com.nisum.dao.StudentDAOImpl;
import com.nisum.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentService {

    @Autowired
    private StudentDAOImpl studentDAO;

    public void save(Student student){
        studentDAO.createStudent(student);
    }
    public List<Student> listAll(){
        return studentDAO.listStudent();
    }
    public Student getById(int id){
        return studentDAO.getStudentById(id);
    }
    public void update(Student student){
        studentDAO.updateStudent(student);
    }
    public void delete(int id){
        studentDAO.deleteStudent(id);
    }
}