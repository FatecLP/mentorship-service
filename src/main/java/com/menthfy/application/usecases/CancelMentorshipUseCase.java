package com.menthfy.application.usecases;

import org.springframework.stereotype.Service;

import com.menthfy.domain.models.Mentorship;
import com.menthfy.infrastructure.persistence.JpaMentorshipRepository;

@Service
public class CancelMentorshipUseCase {

    private final JpaMentorshipRepository repository;

    public CancelMentorshipUseCase(JpaMentorshipRepository repository) {
        this.repository = repository;
    }

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