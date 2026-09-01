package com.menthfy.presentation.controller;

import com.menthfy.application.dto.MentorshipRequest;
import com.menthfy.application.dto.MentorshipResponse;
import com.menthfy.application.dto.StudentMentorshipResponse;
import com.menthfy.application.usecases.*;
import com.menthfy.domain.models.Mentorship;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentorships")
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

    @PostMapping
    public Mentorship create(@RequestBody MentorshipRequest request) {
        return createUseCase.execute(
                request.getStudentId(),
                request.getTeacherId()
        );
    }

    @PutMapping("/{id}/accept")
    public Mentorship accept(@PathVariable Long id) {
        return acceptUseCase.execute(id);
    }
    
    @PutMapping("/{id}/cancel")
    public Mentorship cancel(@PathVariable Long id) {
        return cancelUseCase.execute(id);
    }
    
    @GetMapping("/student/{id}")
    public List<StudentMentorshipResponse> getByStudent(@PathVariable Long id) {
        return studentUseCase.execute(id);
    }

    @GetMapping("/teacher/{id}")
    public List<MentorshipResponse> getByTeacher(@PathVariable Long id) {
        return teacherUseCase.execute(id);
    }

}
