package br.cefetmg.vertask.repository;

import java.util.List;
import br.cefetmg.vertask.model.Funcionario;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Repository
@RegisterBeanMapper(Funcionario.class)
public interface FuncionarioRepository {

    //listar todos os funcionarios
    @SqlQuery("SELECT * FROM funcionario;")
    List<Funcionario> findAll();

    //buscar funcionario por id
    @SqlQuery("SELECT * FROM funcionario WHERE id_funcionario = :id;")
    Funcionario findById(@Bind("id") Long id);

    //inserir funcionario
    @SqlUpdate("""
        insert into funcionario (nome, email, senha, id_setor)
        values (:nome, :email, :senha, :idSetor);
    """)
    @GetGeneratedKeys
    Long insert(@BindBean Funcionario funcionario);

    //método para obter uma instância do repositório
    //usando Jdbi, que é uma biblioteca para acesso a banco de dados
    @Bean
    default FuncionarioRepository getInstance(Jdbi jdbi) {
        return jdbi.onDemand(FuncionarioRepository.class);
    }

    //atualizar funcionario
    @SqlUpdate("""
        update funcionario
        set nome = :nome,
            email = :email,
            senha = :senha,
            id_setor = :idSetor
        where id_funcionario = :idFuncionario;
    """)
    int update(@BindBean Funcionario funcionario);
   
    //deletar funcionario
    @SqlUpdate("""
        delete from funcionario where id_funcionario = :id;
    """)
    int delete(@Bind("id") Long id);
}
