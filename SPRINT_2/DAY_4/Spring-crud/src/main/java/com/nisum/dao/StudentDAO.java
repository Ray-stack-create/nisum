package com.nisum.dao;
import com.nisum.model.Student;
import org.springframework.dao.DataAccessException;
import javax.xml.crypto.Data;
import java.util.List;
public interface StudentDAO {
    void createStudent(Student student) throws DataAccessException;
    Student getStudentById(Integer id) throws DataAccessException;
    List<Student> listStudent() throws DataAccessException;
    void updateStudent(Student student) throws DataAccessException;
    void deleteStudent(Integer id) throws DataAccessException;
}