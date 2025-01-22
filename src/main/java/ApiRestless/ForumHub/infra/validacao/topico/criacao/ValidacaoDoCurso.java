package ApiRestless.ForumHub.infra.validacao.topico.criacao;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoCadastro;
import ApiRestless.ForumHub.infra.exception.ValidacaoException;
import ApiRestless.ForumHub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDoCurso implements ValidacaoCriacaodeTopico {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(DadosTopicoCadastro dados) {
        var curso = cursoRepository.findByNomeIgnoreCase(dados.nomeCurso());
        if (curso == null) {
            throw new ValidacaoException("Curso não encontrado: " + dados.nomeCurso());
        }

    }
}
