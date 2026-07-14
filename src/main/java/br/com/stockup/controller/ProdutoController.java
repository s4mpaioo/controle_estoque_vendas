package br.com.stockup.controller;

import br.com.stockup.dto.CadastroProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.service.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody CadastroProduto dto) {
        produtoService.cadastrarProduto(dto);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable Long id, @RequestBody CadastroProduto dto) {
        produtoService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }

    @GetMapping("/buscar")
    public List<Produto> BuscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }
}

