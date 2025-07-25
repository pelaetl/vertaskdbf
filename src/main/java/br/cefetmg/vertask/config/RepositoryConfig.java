package br.cefetmg.vertask.config;

import br.cefetmg.vertask.repository.FuncionarioRepository;
import br.cefetmg.vertask.repository.SetorRepository;
import br.cefetmg.vertask.repository.TarefaRepository;
import br.cefetmg.vertask.repository.UsuarioRepository;
import br.cefetmg.vertask.repository.AdministradorRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public SetorRepository setorRepository(Jdbi jdbi) {
        return jdbi.onDemand(SetorRepository.class);
    }

    @Bean
    public FuncionarioRepository funcionarioRepository(Jdbi jdbi) {
        return jdbi.onDemand(FuncionarioRepository.class);
    }

    @Bean
    public TarefaRepository tarefaRepository(Jdbi jdbi) {
        return jdbi.onDemand(TarefaRepository.class);
    }

    @Bean
    public UsuarioRepository usuarioRepository(Jdbi jdbi) {
        return jdbi.onDemand(UsuarioRepository.class);
    }


    @Bean
    public AdministradorRepository administradorRepository(Jdbi jdbi) {
        return jdbi.onDemand(AdministradorRepository.class);
    }

}
