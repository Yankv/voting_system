package com.neosage.voting_system.model;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Immutable
@Table(name = "vw_resultados")
@Getter
@NoArgsConstructor
public class ResultadoCandidato {

    @Id
    @Column(name = "candidato_id")
    private Long candidatoId;

    @Column(name = "numero_tarjeton")
    private Integer numeroTarjeton;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Column(name = "total_votos")
    private Long totalVotos;

    private BigDecimal porcentaje;

    @Column(name = "total_general")
    private Long totalGeneral;
}
