package com.menthfy.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.menthfy.application.dto.StudentMentorshipResponse;
import com.menthfy.domain.models.Mentorship;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;

@Service
public class GetByStudentUseCase {
    private final JpaMentorshipRepository repository;

    public GetByStudentUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

    public List<StudentMentorshipResponse> execute(Long studentId) {
        return repository.findByStudentWithTeacherData(studentId)
                .stream()
                .map(r -> new StudentMentorshipResponse(
                    ((Number) r[0]).longValue(),
                    ((Number) r[1]).longValue(),
                    (String) r[2],
                    (String) r[3],
                    (String) r[4],
                    r[5].toString()
                ))
                .toList();    
        }
}
