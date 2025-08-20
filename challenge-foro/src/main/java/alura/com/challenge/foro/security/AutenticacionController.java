package alura.com.challenge.foro.security;

import alura.com.challenge.foro.usuario.DatosAutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public TokenResponse login(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        try {
            // Crear token de autenticación de Spring
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(datos.email(), datos.password())
            );

            // Si pasa la autenticación, generamos el JWT
            String token = tokenService.generarToken(datos.email());

            return new TokenResponse(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Email o password incorrectos");
        }
    }

    public record TokenResponse(String token) {}
}
