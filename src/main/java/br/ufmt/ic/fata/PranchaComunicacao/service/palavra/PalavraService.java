package br.ufmt.ic.fata.PranchaComunicacao.service.palavra;

import br.ufmt.ic.fata.PranchaComunicacao.model.Palavra;
import br.ufmt.ic.fata.PranchaComunicacao.repository.PalavraRepository;
import br.ufmt.ic.fata.PranchaComunicacao.service.armazenamento.ArmazenamentoService;
import br.ufmt.ic.fata.PranchaComunicacao.service.base.BaseCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

/**
 * <a href=https://stackoverflow.com/q/48125594/9180376>Por que não é possível fazer um único
 * PalavraService.</a>
 *
 * @param <T> Subclasse de palavra
 */
@Slf4j
public abstract class PalavraService<T extends Palavra> extends BaseCrudService<T> {
    //TODO: Testar https://github.com/google/guava/wiki/ReflectionExplained
    
    private final ArmazenamentoService armazenamentoService;
    
    protected abstract PalavraRepository<T> getRepository();
    
    PalavraService(ArmazenamentoService as) {
        this.armazenamentoService = as;
    }
    
    @Override
    public void salvar(T palavra) {
        logPalavraSalva(palavra);
        super.salvar(palavra);
    }
    
    public String salvarImagem(MultipartFile imagem, Errors erros) {
        return armazenamentoService.salvarImagem(imagem, erros);
    }
    
    /*  Métodos Auxiliares */
    
    private void logPalavraSalva(T palavra) {
        log.debug("\n--- Palavra Salva ---\n" +
                "Palavra: {}\n" +
                "Novo: {}\n" +
                "ID: {}\n" +
                "Imagem: {}\n" +
                "--- ---\n", palavra.getPalavra(), palavra.isNew(), palavra.getId(), palavra.getUrl());
    }
    
}
