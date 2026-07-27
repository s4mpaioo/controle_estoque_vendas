package br.com.stockup.controller;

import br.com.stockup.service.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Estoque")
public class EstoqueProdutoController {
    private ProdutoService produtoService;

    public EstoqueProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
}
