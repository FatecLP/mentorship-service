package com.menthfy.application.dto;

/**
 * EN: Teacher-facing representation of a mentorship, including the student's name.
 * PT-BR: Representação da mentoria para o professor, incluindo o nome do aluno.
 */
public class MentorshipResponse {
    private final Long id;
    private final Long studentId;
    private final String studentName;
    private final Long teacherId;
    private final String status;

    /**
     * Creates a teacher-facing mentorship response.
     *
     * @param id mentorship identifier
     * @param studentId student identifier
     * @param studentName student display name
     * @param teacherId teacher identifier
     * @param status current mentorship status
     */
    public MentorshipResponse(Long id, Long studentId, String studentName, Long teacherId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.teacherId = teacherId;
        this.status = status;
    }

    /** @return mentorship identifier */
    public Long getId() { return id; }
    /** @return student identifier */
    public Long getStudentId() { return studentId; }
    /** @return student display name */
    public String getStudentName() { return studentName; }
    /** @return teacher identifier */
    public Long getTeacherId() { return teacherId; }
    /** @return current mentorship status */
    public String getStatus() { return status; }
}
