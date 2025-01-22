package ApiRestless.ForumHub.DTOresposta;

import jakarta.validation.constraints.NotBlank;

public record DadosRespostaCadastro(
        @NotBlank
        String mensagem,
        @NotBlank
        String solucao
) {
}
