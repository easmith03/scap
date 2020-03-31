package demo.student.service;

import demo.student.controller.dto.StudentDto;
import demo.student.domain.Student;
import demo.student.exception.NotFoundException;
import demo.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> allStudents() {
        Sort sort = Sort.by(List.of(new Sort.Order(Sort.Direction.ASC,"lastName"), new Sort.Order(Sort.Direction.ASC,"firstName")));
        return studentRepository.findAll(sort);
    }

    public Student studentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student Not Found: " + id));
    }

    public Student saveStudent(StudentDto studentDto) {
        return updateAndSaveStudent(studentDto, new Student());
    }

    private Student updateAndSaveStudent(StudentDto studentDto, Student student) {
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, StudentDto studentDto) {
        return updateAndSaveStudent(studentDto, studentById(id));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


}
