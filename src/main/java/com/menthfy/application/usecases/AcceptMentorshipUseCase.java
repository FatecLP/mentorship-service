package com.menthfy.application.usecases;

import com.menthfy.domain.models.Mentorship;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;
import org.springframework.stereotype.Service;

/** EN: Accepts an existing mentorship request.
 * PT-BR: Aceita uma solicitação de mentoria existente. */
@Service
public class AcceptMentorshipUseCase {
    private final JpaMentorshipRepository repository;

    public AcceptMentorshipUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

    /**
    * EN: Marks a mentorship as accepted.
    * PT-BR: Marca uma mentoria como aceita.
     *
     * @param id mentorship identifier
     * @return updated mentorship
     * @throws RuntimeException when the mentorship does not exist
     */
    public Mentorship execute(Long id) {

        Mentorship mentorship = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentoria não encontrada"));

        mentorship.setStatus("ACCEPTED");

        return repository.save(mentorship);
    }
}
