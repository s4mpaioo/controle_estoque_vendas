package br.com.stockup.controller;

import br.com.stockup.dto.CadastroEstoqueAtacado;

import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.service.EstoqueProdutoService;
import br.com.stockup.service.ProdutoService;
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
    public ResponseEntity<Void> cadastrarAtacado(@RequestBody CadastroEstoqueAtacado dto) {
        estoqueProdutoService.cadastrarAtacado(dto);
        return ResponseEntity.ok().build();
    }

}
