package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueAtacadoDTO;
import br.com.stockup.dto.CadastroEstoqueVarejoDTO;

public interface EstoqueProdutoService {

    void cadastrarAtacado(CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDTO);

    void cadastrarVarejo(CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDTO);
}
