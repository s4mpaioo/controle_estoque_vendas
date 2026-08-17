package br.com.stockup.service;

import br.com.stockup.dto.CadastroEstoqueAtacadoDTO;
import br.com.stockup.dto.CadastroEstoqueTamanhoDTO;
import br.com.stockup.dto.CadastroEstoqueVarejoDTO;
import br.com.stockup.enums.TipoEstoque;
import br.com.stockup.model.EstoqueProduto;
import br.com.stockup.model.Produto;
import br.com.stockup.repository.EstoqueProdutoRepository;
import br.com.stockup.repository.EstoqueTamanhoRepository;
import br.com.stockup.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueProdutoService {

    private final EstoqueProdutoRepository estoqueProdutoRepository;
    private final EstoqueTamanhoRepository estoqueTamanhoRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueProdutoService(EstoqueProdutoRepository estoqueProdutoRepository,
                                 EstoqueTamanhoRepository estoqueTamanhoRepository,
                                 ProdutoRepository produtoRepository) {
        this.estoqueProdutoRepository = estoqueProdutoRepository;
        this.estoqueTamanhoRepository = estoqueTamanhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarAtacado(CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDto) {
        validarCadastroAtacado(cadastroEstoqueAtacadoDto);

        Produto produto = produtoRepository.findById(cadastroEstoqueAtacadoDto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueAtacadoDto.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueAtacadoDto.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.FICHA);
        estoqueProduto.setFicha(cadastroEstoqueAtacadoDto.getFicha());
        estoqueProduto.setQuantidadeFichas(cadastroEstoqueAtacadoDto.getQuantidadeFichas());

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroAtacado(CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDto) {
        if(!produtoRepository.existsById(cadastroEstoqueAtacadoDto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if(cadastroEstoqueAtacadoDto.getPrecoVenda().compareTo(cadastroEstoqueAtacadoDto.getPrecoCusto()) <= 0) {
            throw new RuntimeException("Preço de venda não pode ser menor ou igual ao preço de custo.");
        }
    }

    public void cadastrarVarejo(CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDto) {
        validarCadastroVarejo(cadastroEstoqueVarejoDto);

        Produto produto = produtoRepository.findById(cadastroEstoqueVarejoDto.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueVarejoDto.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueVarejoDto.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.PAR);

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroVarejo(CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDto) {
        if (!produtoRepository.existsById(cadastroEstoqueVarejoDto.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if (cadastroEstoqueVarejoDto.getPrecoVenda().compareTo(cadastroEstoqueVarejoDto.getPrecoCusto()) <= 0) {
            throw new RuntimeException("O preço de venda não pode ser menor ou igual ao preço de custo.");
        }

        validarQuantidadeTamanho(cadastroEstoqueVarejoDto.getQuantidadePorTamanho());
    }

    private void validarQuantidadeTamanho(List<CadastroEstoqueTamanhoDTO> tamanhosDto) {
        boolean possuiQuantidade = false;

        for (int i = 0; i < tamanhosDto.size(); i++) {
            CadastroEstoqueTamanhoDTO tamanho = tamanhosDto.get(i);
            if(tamanho.getQuantidade() > 0) {
                possuiQuantidade = true;
            }
        }
        if(!possuiQuantidade){
            throw new RuntimeException("Informe pelo menos um tamanho.");
        }
    }
}

