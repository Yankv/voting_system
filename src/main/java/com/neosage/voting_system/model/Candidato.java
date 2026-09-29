package com.neosage.voting_system.model;

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
@Table(name = "candidato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(length = 150)
    private String primerNombre;

    @Column(length = 150, nullable = true)
    private String segundoNombre;

    @Column(length = 150)
    private String primerApellido;

    @Column(length = 150, nullable = true)
    private String segundoApellido;
 
    @Column(name = "numero_tarjeton", nullable = false, unique = true)
    private Integer numeroTarjeton;
 
    @Column(name = "foto_url", length = 500)
    private String fotoUrl;
 
    @Column(nullable = false)
    private boolean activo = true;
}
