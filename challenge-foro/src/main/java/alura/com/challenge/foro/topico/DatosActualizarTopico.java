package alura.com.challenge.foro.topico;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso
) { }
