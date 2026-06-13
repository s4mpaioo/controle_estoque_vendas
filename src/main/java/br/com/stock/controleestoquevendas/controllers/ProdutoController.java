package br.com.stock.controleestoquevendas.controllers;

import br.com.stock.controleestoquevendas.domains.entity.Produto;
import br.com.stock.controleestoquevendas.services.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //terá rotas de API
@RequestMapping("/produtos")
public class ProdutoController {
   private final ProdutoService produtoService;

   public ProdutoController(ProdutoService produtoService){
       this.produtoService = produtoService;
   }

   @PostMapping
    public Produto criar(@RequestBody Produto produto){
       return  produtoService.salvar(produto);
   }
}
