package br.ufmt.ic.fata.PranchaComunicacao.controller.web.palavra;

import br.ufmt.ic.fata.PranchaComunicacao.model.Diverso;
import br.ufmt.ic.fata.PranchaComunicacao.service.palavra.DiversoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static br.ufmt.ic.fata.PranchaComunicacao.controller.web.palavra.PalavraController.PALAVRA_MODEL;

@Controller
@SessionAttributes(PALAVRA_MODEL) // Garante o mesmo Model até completar a sessão (setComplete())
@RequestMapping("/pastaDiverso") // URL raiz para todos os Requests deste controller
public class DiversoController extends PalavraController<Diverso> {
    
    /* Nome das páginas HTML (Views) */
    private static final String PAGINA_INICIAL = "pastaDiverso";
    private static final String FRAGMENTO_CADASTRO = "pastaDiverso :: form-cadastro";
    
    @Autowired
    public DiversoController(DiversoService service) {
        super(service);
    }
    
    @Override
    String getPaginaInicial() {
        return PAGINA_INICIAL;
    }
    
    @Override
    String getFragmentoCadastro() {
        return FRAGMENTO_CADASTRO;
    }
    
    @Override
    Diverso novaInstanciaPalavra() {
        return new Diverso();
    }
    
}
