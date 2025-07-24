package br.cefetmg.vertask.model;

import java.sql.Date;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Tarefa {
    private Long idTarefa;
    private String titulo;
    private String descricao;
    private Long idUsuario; // ID do Usuario responsável
    private Long idAdministrador; // ID do Administrador que criou a tarefa
    private String status; // Ex: "Pendente", "Em Progresso", "Concluída"
    private Date dataInicio;
    private Date dataEntrega;

    
}
