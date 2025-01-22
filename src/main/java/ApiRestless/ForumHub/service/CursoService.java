package ApiRestless.ForumHub.service;

import ApiRestless.ForumHub.model.entities.Curso;
import ApiRestless.ForumHub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso buscarPorNome(String nome) {
        return cursoRepository.findByNomeIgnoreCase(nome);
    }
}
