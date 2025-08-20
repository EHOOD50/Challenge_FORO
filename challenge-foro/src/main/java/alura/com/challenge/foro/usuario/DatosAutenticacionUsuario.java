package alura.com.challenge.foro.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @Email String email,
        @NotBlank String password
) {}
