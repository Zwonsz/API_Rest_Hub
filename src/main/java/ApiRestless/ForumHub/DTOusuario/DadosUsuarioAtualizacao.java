package ApiRestless.ForumHub.DTOusuario;

public record DadosUsuarioAtualizacao(
        String nome,
        String senha
) {
    public DadosUsuarioAtualizacao atualizarSenha(String s) {
        return new DadosUsuarioAtualizacao(this.nome, s);
    }
}
