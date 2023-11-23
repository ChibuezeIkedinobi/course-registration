package com.school.courseregistration.entity;

import com.school.courseregistration.validation.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@Table(name = "grade", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Score
    @Column(name = "score", nullable = false)
    private String score;

    private Student student;

    @ManyToOne(optional = false)   // Many grades to one course
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
