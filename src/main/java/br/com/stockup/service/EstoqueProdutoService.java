package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueProduto;

public class EstoqueProdutoService {

    private void validarEstoque(CadastroEstoqueProduto dto) {
        if (dto.getEstoqueMinimo() == null) {
            throw new RuntimeException("O estoque mínimo é obrigatório.");
        }

        if (dto.getPrecoCompra() == null) {
            throw new RuntimeException("O preço de compra é obrigatório.");
        }

        if (dto.getPrecoVenda() == null) {
            throw new RuntimeException("O preço de venda é obrigatório.");
        }

        if (dto.getTipoEstoque() == null) {
            throw new RuntimeException("O tipo de estoque é obrigatório.");
        }

        if (dto.getGrade() == null) {
            throw new RuntimeException("A grade é obrigatória.");
        }
    }
}
