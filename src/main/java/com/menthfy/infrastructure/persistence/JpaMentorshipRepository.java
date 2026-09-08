package com.menthfy.infrastructure.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.menthfy.domain.models.Mentorship;


/** EN: Persistence gateway for mentorship aggregates and enriched read projections.
 * PT-BR: Gateway de persistência das mentorias e das projeções enriquecidas. */
public interface  JpaMentorshipRepository  extends JpaRepository<Mentorship, Long>{

    /** Finds mentorships created by a student. */
    List<Mentorship> findByStudentId(Long studentId);

    /** Finds mentorships assigned to a teacher. */
    List<Mentorship> findByTeacherId(Long teacherId);

    /** Checks whether a pending request already exists for a student-teacher pair. */
    @Query("""
    SELECT COUNT(m) > 0 FROM Mentorship m
    WHERE m.studentId = :studentId
    AND m.teacherId = :teacherId
    AND m.status = 'PENDING'
    """)
    boolean existsPending(Long studentId, Long teacherId);

    /** Loads teacher-facing mentorship data with the student's name. */
    @Query(value = """
    SELECT m.id,
        m.student_id,
        a.nome,
        m.teacher_id,
        m.status
    FROM mentorships m
    JOIN alunos a ON a.id = m.student_id
    WHERE m.teacher_id = :teacherId
    """, nativeQuery = true)
    List<Object[]> findByTeacherWithStudentName(Long teacherId);

    /** Loads student-facing mentorship data with the teacher's profile fields. */
    @Query(value = """
    SELECT 
        m.id,
        m.teacher_id,
        p.nome,
        p.foto_url,
        p.disciplina_principal,
        m.status
    FROM mentorships m
    JOIN professores p ON p.id = m.teacher_id
    WHERE m.student_id = :studentId
    """, nativeQuery = true)
List<Object[]> findByStudentWithTeacherData(Long studentId);
}
