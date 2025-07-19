package br.cefetmg.vertask.config;

import br.cefetmg.vertask.repository.FuncionarioRepository;
import br.cefetmg.vertask.repository.SetorRepository;
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
}
