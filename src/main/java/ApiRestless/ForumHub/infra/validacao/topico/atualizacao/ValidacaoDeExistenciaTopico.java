package ApiRestless.ForumHub.infra.validacao.topico.atualizacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoAtualizacao;
import ApiRestless.ForumHub.infra.exception.ValidacaoException;
import ApiRestless.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeExistenciaTopico implements ValidacaoAtualizacaoTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Long id, DadosTopicoAtualizacao dados) {
        var topico = topicoRepository.findById(id);

        if(topico.isEmpty()) {
            throw new ValidacaoException("Informe um ID do topico valido.");
        }
    }
}
