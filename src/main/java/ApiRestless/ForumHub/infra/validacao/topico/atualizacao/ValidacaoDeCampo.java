package ApiRestless.ForumHub.infra.validacao.topico.atualizacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoAtualizacao;
import ApiRestless.ForumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeCampo implements ValidacaoAtualizacaoTopico{
    @Override
    public void validar(Long id, DadosTopicoAtualizacao dados) {
        if( dados.titulo() == null && dados.mensagem() == null){
            throw new ValidacaoException("É necessário informar ao menos um campo para atualização");
        }
    }
}
