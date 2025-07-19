package br.cefetmg.vertask.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Setor {
    private Long idSetor;
    private String nome;
    private String descricao;

}
