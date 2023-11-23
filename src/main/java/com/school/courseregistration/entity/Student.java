package com.school.courseregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @NonNull
    @NotBlank(message = "First name cannot be blank")
    @Column(name = "firstName", updatable = false, nullable = false)
    private String firstName;

    @NonNull
    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "lastName", updatable = false, nullable = false)
    private String lastName;

    @NonNull
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "userName", updatable = false, nullable = false)
    private String userName;

    @NonNull
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)  // One student to many grades
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany  // many students to many courses
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    private Set<Course> courses;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "uodated_at")
    private Date updatedAt;

    @Setter(AccessLevel.NONE)
    @Column(name = "deleted_at")
    private Date deletedAt;


}
