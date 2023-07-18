package med.voll.api.medico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public Medico save(@RequestBody @Valid DadosCadastroMedico dados) {
        Medico medico = new Medico(dados);
        repository.save(medico);
        return medico;
    }

    @PutMapping
    @Transactional
    public Medico update(@RequestBody @Valid DadosAlteracaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizar(dados);
        return medico;
    }

    @GetMapping
    public Page<DadosListagemMedico> list(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
