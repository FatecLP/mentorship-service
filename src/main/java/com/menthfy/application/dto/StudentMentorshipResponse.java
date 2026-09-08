package com.menthfy.application.dto;

/**
 * EN: Student-facing representation of a mentorship, including teacher profile data.
 * PT-BR: Representação da mentoria para o aluno, incluindo dados do perfil do professor.
 *
 * @param id mentorship identifier
 * @param teacherId teacher identifier
 * @param teacherName teacher display name
 * @param teacherPhoto teacher profile photo URL
 * @param disciplina teacher's main subject
 * @param status current mentorship status
 */
public record StudentMentorshipResponse(
    Long id,
    Long teacherId,
    String teacherName,
    String teacherPhoto,
    String disciplina,
    String status
) {}