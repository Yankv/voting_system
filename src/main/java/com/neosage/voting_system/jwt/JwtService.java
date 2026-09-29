package com.neosage.voting_system.jwt;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.neosage.voting_system.exception.TokenInvalidoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String CLAIM_TIPO = "tipo";
    private static final String TIPO_VOTANTE = "VOTANTE";
    private static final String TIPO_ADMIN = "ADMIN";

    private final SecretKey clave;
    private final JwtProperties propiedades;

    public JwtService(JwtProperties propiedades) {
        this.propiedades = propiedades;
        this.clave = Keys.hmacShaKeyFor(Decoders.BASE64.decode(propiedades.secret()));
    }

    public String generarTokenVotante(String cedula) {
        return generarToken(cedula, TIPO_VOTANTE, propiedades.votanteExpiracionMinutos());
    }

    public String extraerCedulaVotante(String token) {
        return extraerSubject(token, TIPO_VOTANTE);
    }

    public String generarTokenAdmin(String username) {
        return generarToken(username, TIPO_ADMIN, propiedades.adminExpiracionMinutos());
    }

    public String extraerUsernameAdmin(String token) {
        return extraerSubject(token, TIPO_ADMIN);
    }

    private String generarToken(String subject, String tipo, long minutosExpiracion) {
        Instant ahora = Instant.now();
        return Jwts.builder()
                .setSubject(subject)
                .claim(CLAIM_TIPO, tipo)
                .setIssuedAt(Date.from(ahora))
                .setExpiration(Date.from(ahora.plusSeconds(minutosExpiracion * 60)))
                .signWith(clave)
                .compact();
    }

    private String extraerSubject(String token, String tipoEsperado) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(clave)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (!tipoEsperado.equals(claims.get(CLAIM_TIPO, String.class))) {
                throw new TokenInvalidoException();
            }
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            throw new TokenInvalidoException();
        }
    }
}
