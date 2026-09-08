package com.menthfy.presentation.controller;

import com.menthfy.application.dto.MentorshipRequest;
import com.menthfy.application.dto.MentorshipResponse;
import com.menthfy.application.dto.StudentMentorshipResponse;
import com.menthfy.application.usecases.*;
import com.menthfy.domain.models.Mentorship;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** EN: REST controller for the mentorship lifecycle and read operations.
 * PT-BR: Controller REST do ciclo de vida e das consultas de mentorias. */
@RestController
@RequestMapping("/api/mentorships")
@Tag(name = "Mentorships", description = "Operations for creating and managing mentorship requests")
public class MentorshipController {
    private final CreateMentorshipUseCase createUseCase;
    private final AcceptMentorshipUseCase acceptUseCase;
    private final GetByStudentUseCase studentUseCase;
    private final GetByTeacherUseCase teacherUseCase;
    private final CancelMentorshipUseCase cancelUseCase;

    public MentorshipController(
            CreateMentorshipUseCase createUseCase,
            AcceptMentorshipUseCase acceptUseCase,
            GetByStudentUseCase studentUseCase,
            GetByTeacherUseCase teacherUseCase,
            CancelMentorshipUseCase cancelUseCase
    ) {
        this.createUseCase = createUseCase;
        this.acceptUseCase = acceptUseCase;
        this.studentUseCase = studentUseCase;
        this.teacherUseCase = teacherUseCase;
        this.cancelUseCase = cancelUseCase;
    }

    /**
     * Creates a mentorship request.
        * PT-BR: Cria uma solicitação de mentoria.
     *
     * @param request student and teacher identifiers
     * @return persisted mentorship with PENDING status
     */
    @PostMapping
    @Operation(summary = "Create a mentorship request", description = "EN: Creates a pending mentorship request between a student and a teacher. PT-BR: Cria uma solicitação pendente entre aluno e professor.")
    public Mentorship create(@RequestBody MentorshipRequest request) {
        return createUseCase.execute(
                request.getStudentId(),
                request.getTeacherId()
        );
    }

    /**
     * Accepts a mentorship request.
        * PT-BR: Aceita uma solicitação de mentoria.
     *
     * @param id mentorship identifier
     * @return updated mentorship
     */
    @PutMapping("/{id}/accept")
    @Operation(summary = "Accept a mentorship", description = "EN: Changes a mentorship request status to ACCEPTED. PT-BR: Altera o status da mentoria para ACCEPTED.")
    public Mentorship accept(@PathVariable Long id) {
        return acceptUseCase.execute(id);
    }
    
    /**
     * Cancels a mentorship request.
        * PT-BR: Cancela uma solicitação de mentoria.
     *
     * @param id mentorship identifier
     * @return updated mentorship
     */
    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel a mentorship", description = "EN: Changes a mentorship request status to CANCELLED. PT-BR: Altera o status da mentoria para CANCELLED.")
    public Mentorship cancel(@PathVariable Long id) {
        return cancelUseCase.execute(id);
    }
    
    /**
     * Lists mentorships associated with a student.
        * PT-BR: Lista as mentorias associadas a um aluno.
     *
     * @param id student identifier
     * @return teacher-enriched mentorship responses
     */
    @GetMapping("/student/{id}")
    @Operation(summary = "List mentorships for a student", description = "EN: Returns mentorships enriched with teacher profile data. PT-BR: Retorna mentorias enriquecidas com dados do professor.")
    public List<StudentMentorshipResponse> getByStudent(@PathVariable Long id) {
        return studentUseCase.execute(id);
    }

    /**
     * Lists mentorships associated with a teacher.
        * PT-BR: Lista as mentorias associadas a um professor.
     *
     * @param id teacher identifier
     * @return student-enriched mentorship responses
     */
    @GetMapping("/teacher/{id}")
    @Operation(summary = "List mentorships for a teacher", description = "EN: Returns mentorships enriched with student name data. PT-BR: Retorna mentorias enriquecidas com o nome do aluno.")
    public List<MentorshipResponse> getByTeacher(@PathVariable Long id) {
        return teacherUseCase.execute(id);
    }

}
