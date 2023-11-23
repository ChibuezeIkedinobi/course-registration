package com.school.courseregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    @NotBlank(message = "Subject cannot be blank")
    @Column(name = "subject", updatable = false, nullable = false)
    private String Subject;

    @NonNull
    @NotBlank(message = "Course code cannot be blank")
    @Column(name = "code", nullable = false)
    private String code;

    @NonNull
    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)  // one course to many grades
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany    // many courses to many students
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private Set<Student> students;



}
