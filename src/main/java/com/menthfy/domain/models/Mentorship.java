package com.menthfy.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** EN: JPA aggregate representing a mentorship request and its lifecycle status.
 * PT-BR: Agregado JPA que representa uma solicitação de mentoria e seu status. */
@Entity
@Table(name = "mentorships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mentorship {
    /** Database-generated mentorship identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Student identifier owned by the platform's student service. */
    private Long studentId;
    /** Teacher identifier owned by the platform's teacher service. */
    private Long teacherId;
    /** Current lifecycle status, such as PENDING, ACCEPTED, or CANCELLED. */
    private String status;
}
