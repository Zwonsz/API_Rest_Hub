package ApiRestless.ForumHub.infra.validacao.topico.criacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoCadastro;

public interface ValidacaoCriacaodeTopico {

        void validar(DadosTopicoCadastro dados);
}
