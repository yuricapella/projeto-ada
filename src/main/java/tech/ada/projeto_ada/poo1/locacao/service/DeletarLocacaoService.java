package tech.ada.projeto_ada.poo1.locacao.service;

import org.springframework.stereotype.Service;
import tech.ada.projeto_ada.poo1.locacao.model.Locacao;
import tech.ada.projeto_ada.poo1.locacao.repository.LocacaoRepository;

@Service
public class DeletarLocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final BuscarLocacaoService buscarLocacaoService;

    public DeletarLocacaoService(LocacaoRepository locacaoRepository, BuscarLocacaoService buscarLocacaoService) {
        this.locacaoRepository = locacaoRepository;
        this.buscarLocacaoService = buscarLocacaoService;
    }

    public void deletar(Long id) {
        Locacao locacaoExistente = buscarLocacaoService.buscarLocacaoPorId(id);
        locacaoRepository.delete(locacaoExistente);
    }
}
