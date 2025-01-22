package ApiRestless.ForumHub.infra.validacao.topico.criacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoCadastro;
import ApiRestless.ForumHub.infra.exception.ValidacaoException;
import ApiRestless.ForumHub.model.entities.Topico;
import ApiRestless.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeTopico implements ValidacaoCriacaodeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosTopicoCadastro dados) {

        Topico topico = topicoRepository.findByTituloAndMensagemIgnoreCase(dados.titulo(), dados.mensagem());

        if (topico != null) {
            throw new ValidacaoException("Tópico já existente: " + dados.titulo());
        }

    }
}
