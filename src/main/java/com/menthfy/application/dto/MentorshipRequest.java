package com.menthfy.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * EN: Payload used to request a mentorship between a student and a teacher.
 * PT-BR: Dados usados para solicitar uma mentoria entre aluno e professor.
 */
@Getter
@Setter
public class MentorshipRequest {
    /** Student identifier. Identificador do aluno. */
    private Long studentId;

    /** Teacher identifier. Identificador do professor. */
    private Long teacherId;
}
