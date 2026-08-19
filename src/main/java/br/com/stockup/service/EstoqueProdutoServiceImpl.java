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
public class EstoqueProdutoServiceImpl implements EstoqueProdutoService {

    private final EstoqueProdutoRepository estoqueProdutoRepository;
    private final EstoqueTamanhoRepository estoqueTamanhoRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueProdutoServiceImpl(EstoqueProdutoRepository estoqueProdutoRepository,
                                     EstoqueTamanhoRepository estoqueTamanhoRepository,
                                     ProdutoRepository produtoRepository) {
        this.estoqueProdutoRepository = estoqueProdutoRepository;
        this.estoqueTamanhoRepository = estoqueTamanhoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void cadastrarAtacado(CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDTO) {
        validarCadastroAtacado(cadastroEstoqueAtacadoDTO);

        Produto produto = produtoRepository.findById(cadastroEstoqueAtacadoDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueAtacadoDTO.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueAtacadoDTO.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.FICHA);
        estoqueProduto.setFicha(cadastroEstoqueAtacadoDTO.getFicha());
        estoqueProduto.setQuantidadeFichas(cadastroEstoqueAtacadoDTO.getQuantidadeFichas());

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroAtacado(CadastroEstoqueAtacadoDTO cadastroEstoqueAtacadoDTO) {
        if(!produtoRepository.existsById(cadastroEstoqueAtacadoDTO.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if(cadastroEstoqueAtacadoDTO.getPrecoVenda().compareTo(cadastroEstoqueAtacadoDTO.getPrecoCusto()) <= 0) {
            throw new RuntimeException("Preço de venda não pode ser menor ou igual ao preço de custo.");
        }
    }

    @Override
    public void cadastrarVarejo(CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDTO) {
        validarCadastroVarejo(cadastroEstoqueVarejoDTO);

        Produto produto = produtoRepository.findById(cadastroEstoqueVarejoDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        EstoqueProduto estoqueProduto = new EstoqueProduto();

        estoqueProduto.setProduto(produto);
        estoqueProduto.setPrecoVenda(cadastroEstoqueVarejoDTO.getPrecoVenda());
        estoqueProduto.setPrecoCusto(cadastroEstoqueVarejoDTO.getPrecoCusto());
        estoqueProduto.setTipoEstoque(TipoEstoque.PAR);

        estoqueProdutoRepository.save(estoqueProduto);
    }

    private void validarCadastroVarejo(CadastroEstoqueVarejoDTO cadastroEstoqueVarejoDTO) {
        if (!produtoRepository.existsById(cadastroEstoqueVarejoDTO.getProdutoId())) {
            throw new RuntimeException("Produto não encontrado.");
        }
        if (cadastroEstoqueVarejoDTO.getPrecoVenda().compareTo(cadastroEstoqueVarejoDTO.getPrecoCusto()) <= 0) {
            throw new RuntimeException("O preço de venda não pode ser menor ou igual ao preço de custo.");
        }

        validarQuantidadeTamanho(cadastroEstoqueVarejoDTO.getQuantidadePorTamanho());
    }

    private void validarQuantidadeTamanho(List<CadastroEstoqueTamanhoDTO> tamanhosDTO) {
        boolean possuiQuantidade = false;

        for (int i = 0; i < tamanhosDTO.size(); i++) {
            CadastroEstoqueTamanhoDTO tamanho = tamanhosDTO.get(i);
            if(tamanho.getQuantidade() > 0) {
                possuiQuantidade = true;
            }
        }
        if(!possuiQuantidade){
            throw new RuntimeException("Informe pelo menos um tamanho.");
        }
    }
}

