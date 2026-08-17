package br.com.stockup.controller;

import br.com.stockup.dto.CadastroEstoqueAtacadoDTO;

import br.com.stockup.dto.CadastroEstoqueVarejoDTO;

import br.com.stockup.service.EstoqueProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estoque")
public class EstoqueProdutoController {

    private final EstoqueProdutoService estoqueProdutoService;

    public EstoqueProdutoController(EstoqueProdutoService estoqueProdutoService) {
        this.estoqueProdutoService = estoqueProdutoService;
    }

    @PostMapping("/atacado")
    public ResponseEntity<Void> cadastrarAtacado(@RequestBody CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDTO) {
        estoqueProdutoService.cadastrarAtacado(cadastroEstoqueAtacadoDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/varejo")
    public ResponseEntity<Void> cadastrarVarejo(@RequestBody CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDTO) {
        estoqueProdutoService.cadastrarVarejo(cadastroEstoqueVarejoDTO);
        return ResponseEntity.ok().build();
    }
}
