package br.cefetmg.vertask.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Funcionario {
    private Long idFuncionario;
    private String nome;
    private String email;
    private String senha;
    private Long idSetor;
}
