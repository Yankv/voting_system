package com.neosage.voting_system.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracion_votacion")
@Getter
@Setter
@NoArgsConstructor
public class ConfiguracionVotacion {
    public static final short ID_UNICO = 1;

    @Id
    private Short id = ID_UNICO;

    @Column(nullable = false)
    private boolean abierta;

    @Column(name = "fecha_apertura", insertable = false, updatable = false)
    private Instant fechaApertura;

    @Column(name = "fecha_cierre", insertable = false, updatable = false)
    private Instant fechaCierre;
}
