package br.com.guilherme.alura.forumalura.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.guilherme.alura.forumalura.controller.form.TopicoForm;
import br.com.guilherme.alura.forumalura.dto.TopicoDTO;
import br.com.guilherme.alura.forumalura.model.Topico;
import br.com.guilherme.alura.forumalura.repository.CursoRepository;
import br.com.guilherme.alura.forumalura.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
    
	@GetMapping
    public List<TopicoDTO> lista(String nomeCurso) {
    	if(nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
    	} else {
    		List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
    		return TopicoDTO.converter(topicos);
    	}
    }
    
    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder builderURI) {
		Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	URI uri = builderURI.path("/topicos/{Id}").buildAndExpand(topico.getId()).toUri();
    	return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
}
