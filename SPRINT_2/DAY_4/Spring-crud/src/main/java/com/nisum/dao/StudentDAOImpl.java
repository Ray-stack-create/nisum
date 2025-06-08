package com.nisum.dao;
import com.nisum.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class StudentDAOImpl implements StudentDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        return student;
    };
    public void createStudent(Student student) throws DataAccessException{
        String sql = "insert into student(id,name,age) values (?,?,?);";
        jdbcTemplate.update(sql,student.getId(),student.getName(),student.getAge());
    }
    public Student getStudentById(Integer id) throws DataAccessException{
        String sql = "select * from student where id = ?;";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
    public List<Student> listStudent() throws DataAccessException{
        String sql = "select * from student;";
        return jdbcTemplate.query(sql,rowMapper);
    }
    public void updateStudent(Student student) throws DataAccessException{
        String sql = "update student set name = ?,age = ? where id = ?";
        jdbcTemplate.update(sql,student.getName(),student.getAge(),student.getId());
    }
    public void deleteStudent(Integer id) throws DataAccessException{
        String sql = "delete from student where id = ?;";
        jdbcTemplate.update(sql,id);
    }
}