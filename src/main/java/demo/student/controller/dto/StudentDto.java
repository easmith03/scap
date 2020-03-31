package demo.student.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StudentDto {
    @NotBlank
    @Size(max = 200)
    private String firstName;

    @NotBlank
    @Size(max = 200)
    private String lastName;

    @Size(max = 200)
    private String email;

}
