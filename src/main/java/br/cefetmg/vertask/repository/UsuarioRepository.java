package br.cefetmg.vertask.repository;

import br.cefetmg.vertask.model.Usuario;

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
@RegisterBeanMapper(Usuario.class)
public interface UsuarioRepository {

}
