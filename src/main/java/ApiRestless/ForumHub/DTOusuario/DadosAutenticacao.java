package ApiRestless.ForumHub.DTOusuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank  String email,
        @NotBlank String senha) {
}
