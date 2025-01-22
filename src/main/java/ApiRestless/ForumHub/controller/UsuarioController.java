package ApiRestless.ForumHub.controller;

import ApiRestless.ForumHub.DTOtopico.DadosTopicoResponse;
import ApiRestless.ForumHub.DTOusuario.DadosCadastroUsuario;
import ApiRestless.ForumHub.DTOusuario.DadosNome;
import ApiRestless.ForumHub.DTOusuario.DadosUsuarioAtualizacao;
import ApiRestless.ForumHub.DTOusuario.DadosUsuarioResponse;
import ApiRestless.ForumHub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Cadastrar Usuário")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario cadastro, UriComponentsBuilder uriBuilder) {

        var usuarioCadastrado = usuarioService.cadastrarUsuario(cadastro);
        var uri = uriBuilder.path("/usuarios").build().toUri();

        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualizar Usuário")
    public ResponseEntity<DadosUsuarioResponse> atualizarUsuario(@RequestBody @Valid DadosUsuarioAtualizacao dados) {

        DadosUsuarioResponse usuarioAtualizado = usuarioService.atualizarUsuario(dados);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Deletar Usuário")
    public ResponseEntity<?> removerUsuario() {
        usuarioService.deletar();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Listar Usuários")
    public ResponseEntity<Page<DadosNome>> buscarUsuario(
            @PageableDefault(size = 10) Pageable paginacao) {

        var page = usuarioService.buscarUsuario(paginacao).map(DadosNome::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/topicos")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Listar Tópicos do Usuário")
    public ResponseEntity<Page<DadosTopicoResponse>> listarTopicos(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao
    ) {
        var page = usuarioService.buscarTopicos(paginacao).map(DadosTopicoResponse::new);
        return ResponseEntity.ok(page);
    }

}
