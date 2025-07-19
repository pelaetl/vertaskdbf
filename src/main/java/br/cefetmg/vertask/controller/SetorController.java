package br.cefetmg.vertask.controller;

import br.cefetmg.vertask.model.Setor;
import br.cefetmg.vertask.repository.SetorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/setor") //http://localhost:8080/api/v1/setor
public class SetorController {

    // private List<Setor> setores;
    // private long nextId = 1L;

    private final SetorRepository setorRepository;

    public SetorController(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }
    
    // public SetorController() {
    //     setores = new ArrayList<>();

    //     Setor setor1 = new Setor();
    //     setor1.setId(nextId++);
    //     setor1.setNome("Setor 1");
    //     setor1.setDescricao("Descrição do Setor 1");
    //     setor1.setNumeroFuncinarios(10);
    //     setores.add(setor1);

    //     Setor setor2 = new Setor();
    //     setor2.setId(nextId++);
    //     setor2.setNome("Setor 2");
    //     setor2.setDescricao("Descrição do Setor 2");
    //     setor2.setNumeroFuncinarios(5);
    //     setores.add(setor2);

    //     Setor setor3 = new Setor();
    //     setor3.setId(nextId++);
    //     setor3.setNome("Setor 3");
    //     setor3.setDescricao("Descrição do Setor 3");
    //     setor3.setNumeroFuncinarios(8);
    //     setores.add(setor3);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> getById(@PathVariable Long id) {
        Setor setor = setorRepository.findById(id);
        if (setor != null) {
            return ResponseEntity.ok().body(setor);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<Setor>> getAll() {
        List<Setor> setores = setorRepository.findAll();
        return ResponseEntity.ok().body(setores);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Setor> create(@RequestBody Setor setor) {
        Long id = setorRepository.insert(setor);
        setor.setIdSetor(id);
        return ResponseEntity.ok().body(setor);
    }

    //@PutMapping({"/{id}", "/"})
    @PutMapping({"", "/"})
    public ResponseEntity<Setor> update(@RequestBody Setor setor) {
        //id setor tem que estar preenchido no corpo da requisição pois o sistema pega ele por aqui 
        //e não pelo path variable já que é um RequestBody
        //no corpo da requisição tem que ter o setor com o id preenchido  
        
        if (setor.getIdSetor() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor not found");
        }

        int qtd = setorRepository.update(setor);
        
        if(qtd == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum setor alterado");
        }
        if(qtd > 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mais de um setor alterado");
        }

        return ResponseEntity.ok().body(setor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Setor> delete(@PathVariable Long id) {
        //id não tem que estar preenchido no corpo da requisição pois o sistema pega ele pelo path variable
        //e não pelo RequestBody já que é um PathVariable
        //no corpo da requisição não precisa ter o setor
    
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do Setor nao encontrado");
        }

        Setor setor = setorRepository.findById(id);
        if (setor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor not found");
        }

        int qtd = setorRepository.delete(id);

        if(qtd == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum setor excluido");
        }
        if(qtd > 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mais de um setor excluido");
        }

        return ResponseEntity.ok().body(setor);
    }

    // private Setor findById(Long id) {
    //     for (Setor setor : setores) {
    //         if (setor.getId() == id) {
    //             return setor;
    //         }
    //     }
    //     return null;
    // }
}
