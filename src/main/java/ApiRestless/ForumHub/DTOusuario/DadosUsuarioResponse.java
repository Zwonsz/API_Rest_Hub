package ApiRestless.ForumHub.DTOusuario;

import ApiRestless.ForumHub.model.entities.Usuario;

public record DadosUsuarioResponse(
        Long id,
        String nome,
        String email
) {
    public DadosUsuarioResponse(Usuario novoUsuario) {
        this(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }
}
