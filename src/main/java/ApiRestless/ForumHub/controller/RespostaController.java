package ApiRestless.ForumHub.controller;

import ApiRestless.ForumHub.DTOresposta.DadosRespostaAtualizacao;
import ApiRestless.ForumHub.DTOresposta.DadosRespostaCadastro;
import ApiRestless.ForumHub.DTOresposta.DadosRespostaResponse;
import ApiRestless.ForumHub.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping("{id}")
    @Operation(summary = "Postar Resposta")
    public ResponseEntity<?> criar(@RequestBody @Valid DadosRespostaCadastro resposta,
                                                       @PathVariable Long id,
                                                       UriComponentsBuilder uriBuilder) {
        DadosRespostaResponse respostaCriada = respostaService.criar(resposta, id);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(respostaCriada.id()).toUri();

        return ResponseEntity.created(uri).body(respostaCriada);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar Resposta")
    public ResponseEntity<DadosRespostaResponse> atualizar(@RequestBody @Valid DadosRespostaAtualizacao resposta,
                                                          @PathVariable Long id) {
        DadosRespostaResponse respostaAtualizada = respostaService.atualizar(resposta, id);

        return ResponseEntity.ok(respostaAtualizada);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar Resposta")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        respostaService.deletar(id);
        return ResponseEntity.ok().build();
    }


}
