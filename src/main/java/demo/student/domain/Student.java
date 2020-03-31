package demo.student.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "STUDENT")
public class Student extends AuditableEntity {

    @NotNull
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Column(name = "email", nullable = true, length = 200)
    private String email;
    
}
