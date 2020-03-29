package demo.student.controller.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StudentDto {
    @NotNull
    @Size(max = 200)
    private String firstName;

    @NotNull
    @Size(max = 200)
    private String lastName;

    @Size(max = 200)
    private String email;

}
