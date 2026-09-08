package com.menthfy.application.usecases;

import com.menthfy.application.dto.StudentMentorshipResponse;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/** EN: Retrieves mentorships enriched with teacher data for a student.
 * PT-BR: Recupera mentorias enriquecidas com dados do professor para um aluno. */
@Service
public class GetByStudentUseCase {
    private final JpaMentorshipRepository repository;

    public GetByStudentUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

    /**
    * EN: Finds all mentorships associated with a student.
    * PT-BR: Busca todas as mentorias associadas a um aluno.
     *
     * @param studentId student identifier
     * @return enriched student mentorship responses
     */
    public List<StudentMentorshipResponse> execute(Long studentId) {
        return repository.findByStudentWithTeacherData(studentId)
                .stream()
                .map(r -> new StudentMentorshipResponse(
                        ((Number) r[0]).longValue(),
                        ((Number) r[1]).longValue(),
                        (String) r[2],
                        (String) r[3],
                        (String) r[4],
                        r[5].toString()))
                .toList();
    }
}
