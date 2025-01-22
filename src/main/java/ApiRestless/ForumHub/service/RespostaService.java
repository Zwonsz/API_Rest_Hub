package ApiRestless.ForumHub.service;

import ApiRestless.ForumHub.DTOresposta.DadosRespostaAtualizacao;
import ApiRestless.ForumHub.DTOresposta.DadosRespostaCadastro;
import ApiRestless.ForumHub.DTOresposta.DadosRespostaResponse;
import ApiRestless.ForumHub.infra.exception.ValidacaoException;
import ApiRestless.ForumHub.model.entities.Resposta;
import ApiRestless.ForumHub.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public DadosRespostaResponse criar(DadosRespostaCadastro dados, Long id) {

        usuarioService.verificarSeUsuarioEstaAtivo();

        var topico = topicoService.buscarPorId(id);

        if (topico == null) {
            throw new ValidacaoException("Tópico não encontrado");
        }

        // Mudando o status do tópico
        topicoService.atualizarStatusTopico(topico);

        var usuario = usuarioService.usuarioAtual();

        var resposta = new Resposta(dados, topico, usuario);

        var respostaSalva = respostaRepository.save(resposta);

        return new DadosRespostaResponse(respostaSalva);
    }

    @Transactional
    public DadosRespostaResponse atualizar(DadosRespostaAtualizacao resposta, Long id) {
        usuarioService.verificarSeUsuarioEstaAtivo();

        Resposta respostaSalva = respostaRepository.findById(id).orElseThrow(() -> new ValidacaoException("Resposta não encontrada"));

        verificarUsuario(respostaSalva);

        respostaSalva.atualizar(resposta);

        return new DadosRespostaResponse(respostaSalva);
    }

    private void verificarUsuario(Resposta resposta){
        if(usuarioService.usuarioAtual() != resposta.getAutor()){
            throw new ValidacaoException("Você não tem permição para fazer essa operação");
        }
    }

    @Transactional
    public void deletar(Long id) {
        usuarioService.verificarSeUsuarioEstaAtivo();

        Resposta resposta = respostaRepository.findById(id).orElseThrow(() -> new ValidacaoException("Resposta não encontrada"));

        verificarUsuario(resposta);

        respostaRepository.delete(resposta);

        topicoService.decrementarStatus(resposta.getTopico());
    }
}
