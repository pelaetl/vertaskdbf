package br.cefetmg.vertask.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Funcionario extends Usuario {
    private Long idFuncionario;
    private Long idSetor;
}
