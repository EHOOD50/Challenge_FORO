package alura.com.challenge.foro.usuario;

import alura.com.challenge.foro.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    // Registrar usuario y devolver token
    @PostMapping("/registrar")
    public TokenResponse registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        // Encriptar la contraseña
        String passwordEncriptada = passwordEncoder.encode(datos.password());

        // Crear usuario
        Usuario usuario = new Usuario(datos.nombre(), datos.email(), passwordEncriptada);
        usuarioRepository.save(usuario);

        // Generar token usando email
        String token = tokenService.generarToken(usuario.getEmail());

        return new TokenResponse(token);
    }

    // Record para la respuesta del token
    public record TokenResponse(String token) {}
}
