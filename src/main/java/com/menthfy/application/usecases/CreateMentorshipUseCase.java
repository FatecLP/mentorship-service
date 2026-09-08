package com.menthfy.application.usecases;


import com.menthfy.domain.models.Mentorship;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;
import org.springframework.stereotype.Service;

@Service
/** EN: Creates a new pending mentorship request.
 * PT-BR: Cria uma nova solicitação de mentoria pendente. */
public class CreateMentorshipUseCase {
    private final JpaMentorshipRepository repository;

    public CreateMentorshipUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

    /**
    * EN: Creates a request unless the student already has a pending request for the teacher.
    * PT-BR: Cria uma solicitação, exceto quando já existe uma solicitação pendente equivalente.
     *
     * @param studentId student identifier
     * @param teacherId teacher identifier
     * @return persisted mentorship
     * @throws RuntimeException when an equivalent pending request already exists
     */
    public Mentorship execute(Long studentId, Long teacherId) {

        Mentorship mentorship = Mentorship.builder()
                .studentId(studentId)
                .teacherId(teacherId)
                .status("PENDING")
                .build();

        if(repository.existsPending(studentId, teacherId)){
            throw new RuntimeException("Você já solicitou este professor.");
        }

        return repository.save(mentorship);
    }
}
