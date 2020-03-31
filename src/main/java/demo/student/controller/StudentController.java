package demo.student.controller;

import demo.student.controller.dto.StudentDto;
import demo.student.domain.Student;
import demo.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.allStudents();
    }

    @GetMapping("/{id}")
    public Student getStudents(@PathVariable("id") Long id) {
        return studentService.studentById(id);
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @PatchMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @Valid @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

}
