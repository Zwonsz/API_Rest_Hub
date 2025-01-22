package ApiRestless.ForumHub.DTOusuario;

import ApiRestless.ForumHub.model.entities.Usuario;

public record DadosNome(String nome) {

    public DadosNome(Usuario usuario) {
        this(usuario.getNome());
    }
}
