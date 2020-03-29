package demo.student.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Column(name = "email", nullable = true, length = 200)
    private String email;

    @Column(name = "last_modified", nullable = true)
    private Timestamp lastModified;
}
