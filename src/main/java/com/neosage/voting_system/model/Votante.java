package com.neosage.voting_system.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Votante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String cedula;

    @Column(length = 150)
    private String primerNombre;

    @Column(length = 150, nullable = true)
    private String segundoNombre;

    @Column(length = 150)
    private String primerApellido;

    @Column(length = 150, nullable = true)
    private String segundoApellido;

    @Column(name = "ha_votado", nullable = false)
    private boolean haVotado;

    @Column(name = "fecha_voto")
    private Instant fechaVoto;
}
