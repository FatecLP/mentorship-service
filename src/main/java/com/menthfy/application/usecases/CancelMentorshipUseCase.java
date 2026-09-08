package com.menthfy.application.usecases;

import org.springframework.stereotype.Service;

import com.menthfy.domain.models.Mentorship;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;

/** EN: Cancels an existing mentorship request.
 * PT-BR: Cancela uma solicitação de mentoria existente. */
@Service
public class CancelMentorshipUseCase {

    private final JpaMentorshipRepository repository;

    public CancelMentorshipUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

    /**
    * EN: Marks a mentorship as cancelled.
    * PT-BR: Marca uma mentoria como cancelada.
     *
     * @param id mentorship identifier
     * @return updated mentorship
     * @throws RuntimeException when the mentorship does not exist or is already cancelled
     */
    public Mentorship execute(Long id) {
        Mentorship mentorship = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentoria não encontrada"));

        if ("CANCELLED".equals(mentorship.getStatus())) {
            throw new RuntimeException("Esta mentoria já foi cancelada.");
        }

        mentorship.setStatus("CANCELLED");
        return repository.save(mentorship);
    }
}
