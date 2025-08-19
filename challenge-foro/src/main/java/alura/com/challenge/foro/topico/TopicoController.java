package alura.com.challenge.foro.topico;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    // Registrar
    @PostMapping
    @Transactional
    public DatosRespuestaTopico registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        Topico topico = new Topico(datos);
        repository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    // Listar
    @GetMapping
    public List<DatosListaTopico> listar() {
        return repository.findAll().stream().map(DatosListaTopico::new).toList();
    }

    // Actualizar
    @PutMapping
    @Transactional
    public DatosRespuestaTopico actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = repository.getReferenceById(datos.id());
        topico.actualizar(datos);
        return new DatosRespuestaTopico(topico);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
