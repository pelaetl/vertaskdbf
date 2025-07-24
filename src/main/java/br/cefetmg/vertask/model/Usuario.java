package br.cefetmg.vertask.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Usuario {
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
}
