package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.exception.LocacaoNaoEncontradaException;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;

import java.util.List;

@Service
public class BuscarLocacaoService {
    private final LocacaoRepository locacaoRepository;

    public BuscarLocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public List<Locacao> buscarTodasAsLocacoes() {
        return locacaoRepository.findAll();
    }

    public Locacao buscarLocacaoPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new LocacaoNaoEncontradaException(id));
    }


}
