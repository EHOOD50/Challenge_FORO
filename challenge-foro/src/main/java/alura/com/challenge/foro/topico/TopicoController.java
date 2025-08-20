package alura.com.challenge.foro.topico;

import alura.com.challenge.foro.topico.DatosRegistroTopico;
import alura.com.challenge.foro.topico.DatosRespuestaTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public DatosRespuestaTopico registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        Topico topico = new Topico(datos);
        repository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    @GetMapping
    public List<DatosRespuestaTopico> listar() {
        return repository.findAll().stream().map(DatosRespuestaTopico::new).toList();
    }

    @PutMapping
    @Transactional
    public DatosRespuestaTopico actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = repository.getReferenceById(datos.id());
        topico.actualizar(datos);
        return new DatosRespuestaTopico(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
