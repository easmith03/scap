package demo.student.service;

import demo.student.controller.dto.StudentDto;
import demo.student.domain.Student;
import demo.student.exception.NotFoundException;
import demo.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void allStudentSuccess() {

        Long id = 1l;
        Student student = new Student();
        student.setId(id);

        List<Student> students = List.of(student);

        ArgumentCaptor<Sort> sortArgumentCaptor = ArgumentCaptor.forClass(Sort.class);
        doReturn(students).when(studentRepository).findAll(any(Sort.class));

        studentService.allStudents();

        verify(studentRepository).findAll(sortArgumentCaptor.capture());

        assertAll(() -> assertEquals(Sort.Direction.ASC, sortArgumentCaptor.getValue().getOrderFor("lastName").getDirection()),
                () -> assertEquals(Sort.Direction.ASC, sortArgumentCaptor.getValue().getOrderFor("firstName").getDirection()),
                () -> assertEquals(1, students.size()),
                () -> assertEquals(id, students.get(0).getId())
                );
    }

    @Test
    void studentByIdSuccess() {

        Long id = 1l;
        Student student = new Student();
        student.setId(id);

        doReturn(Optional.of(student)).when(studentRepository).findById(id);

        Student foundStudent = studentService.studentById(id);

        verify(studentRepository).findById(id);

        assertEquals(student, foundStudent);
    }

    @Test
    void studentByIdException() {

        Long id = 1l;
        doReturn(Optional.empty()).when(studentRepository).findById(id);

        assertEquals("Student Not Found: " + id,
                assertThrows(NotFoundException.class, () -> {
                    studentService.studentById(id);
                }).getMessage());

    }

    @Test
    void saveStudentSuccess() {

        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("test@email");
        studentDto.setFirstName("TestFirst");
        studentDto.setFirstName("TestSecond");

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        doReturn(new Student()).when(studentRepository).save(any(Student.class));

        studentService.saveStudent(studentDto);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        assertAll(() -> assertEquals(studentDto.getFirstName(), studentArgumentCaptor.getValue().getFirstName()),
                () -> assertEquals(studentDto.getLastName(), studentArgumentCaptor.getValue().getLastName()),
                () -> assertEquals(studentDto.getEmail(), studentArgumentCaptor.getValue().getEmail())
        );
    }

    @Test
    void updateStudentSuccess() {

        Long id = 1l;
        Student student = new Student();
        student.setId(id);

        StudentDto studentDto = new StudentDto();
        studentDto.setEmail("test@email");
        studentDto.setFirstName("TestFirst");
        studentDto.setFirstName("TestSecond");

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        doReturn(new Student()).when(studentRepository).save(any(Student.class));
        doReturn(Optional.of(student)).when(studentRepository).findById(id);

        studentService.updateStudent(id, studentDto);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        assertAll(() -> assertEquals(studentDto.getFirstName(), studentArgumentCaptor.getValue().getFirstName()),
                () -> assertEquals(studentDto.getLastName(), studentArgumentCaptor.getValue().getLastName()),
                () -> assertEquals(studentDto.getEmail(), studentArgumentCaptor.getValue().getEmail())
        );
   }

    @Test
    void deleteStudentByIdSuccess() {

        Long id = 1l;
        doNothing().when(studentRepository).deleteById(id);

        studentService.deleteStudent(id);

        verify(studentRepository).deleteById(id);
    }

}
