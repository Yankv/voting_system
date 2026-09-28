package com.neosage.voting_system.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @Column(name = "fecha_hora", nullable = false, updatable = false)
    private Instant fechaHora;

    @PrePersist
    void alCrear() {
        if (fechaHora == null) {
            fechaHora = Instant.now();
        }
    }
}
