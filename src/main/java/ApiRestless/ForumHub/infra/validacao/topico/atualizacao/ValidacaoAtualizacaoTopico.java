package ApiRestless.ForumHub.infra.validacao.topico.atualizacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoAtualizacao;

public interface ValidacaoAtualizacaoTopico {
    void validar(Long id, DadosTopicoAtualizacao dados);
}
