package br.cefetmg.vertask.controller;

import br.cefetmg.vertask.model.Funcionario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import br.cefetmg.vertask.repository.FuncionarioRepository;

@RestController
@RequestMapping("/api/v1/funcionario") //http://localhost:8080/api/v1/funcionario
public class FuncionarioController {

    // private List<Funcionario> funcionarios;
    // private long nextId = 1L;

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // public FuncionarioController() {
    //     funcionarios = new ArrayList<>();

    //     Funcionario funcionario1 = new Funcionario();
    //     funcionario1.setId(nextId++);
    //     funcionario1.setNome("Funcionario 1");
    //     funcionario1.setEmail("funcionario1@gmail.com");
    //     funcionario1.setSenha("senha123");
    //     funcionario1.setSetorId(1L); // Assuming setorId is set to 1 for this example
    //     funcionarios.add(funcionario1);

    //     Funcionario funcionario2 = new Funcionario();
    //     funcionario2.setId(nextId++);
    //     funcionario2.setNome("Funcionario 2");
    //     funcionario2.setEmail("funcionario2@gmail.com");
    //     funcionario2.setSenha("senha456");
    //     funcionario2.setSetorId(2L); // Assuming setorId is set to 2 for this example
    //     funcionarios.add(funcionario2);

    //     Funcionario funcionario3 = new Funcionario();
    //     funcionario3.setId(nextId++);
    //     funcionario3.setNome("Funcionario 3");
    //     funcionario3.setEmail("funcionario3@gmail.com");
    //     funcionario3.setSenha("senha789");
    //     funcionario3.setSetorId(3L); // Assuming setorId is set to 3 for this example
    //     funcionarios.add(funcionario3);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id);
        if (funcionario != null) {
            return ResponseEntity.ok().body(funcionario);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<Funcionario>> getAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok().body(funcionarios);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        Long id = funcionarioRepository.insert(funcionario);
        funcionario.setIdFuncionario(id);
        return ResponseEntity.ok().body(funcionario);
    }

    //@PutMapping({"/{id}", "/"})
    @PutMapping({"","/"})
    public ResponseEntity<Funcionario> update(@RequestBody Funcionario funcionario) {
        if (funcionario.getIdFuncionario() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario not found");
        }

        int qtd = funcionarioRepository.update(funcionario);
        
        if(qtd == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum funcionario alterado");
        }
        if(qtd > 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mais de um funcionario alterado");
        }

        return ResponseEntity.ok().body(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do Funcionario nao encontrado");
        }

        Funcionario funcionario = funcionarioRepository.findById(id);
        if (funcionario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario not found");
        }

        int qtd = funcionarioRepository.delete(id);

        if(qtd == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum funcionario excluido");
        }
        if(qtd > 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mais de um funcionario excluido");
        }

        return ResponseEntity.ok().body(funcionario);
    }
    
    // private Funcionario findById(Long id) {
    //     for (Funcionario funcionario : funcionarios) {
    //         if (funcionario.getId() == id) {
    //             return funcionario;
    //         }
    //     }
    //     return null;
    // }
}
