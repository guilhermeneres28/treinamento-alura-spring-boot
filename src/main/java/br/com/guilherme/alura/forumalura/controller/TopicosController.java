package br.com.guilherme.alura.forumalura.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.guilherme.alura.forumalura.dto.AtualizacaoTopicoFormDTO;
import br.com.guilherme.alura.forumalura.dto.DetalhesDoTopicoDTO;
import br.com.guilherme.alura.forumalura.dto.TopicoDTO;
import br.com.guilherme.alura.forumalura.dto.TopicoFormDTO;
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
	@Cacheable(value = "listaDeTopicos")
    public Page<TopicoDTO> listar(@RequestParam(required = false) String nomeCurso, Pageable paginacao) {
    	if(nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDTO.converter(topicos);
    	} else {
    		Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
    		return TopicoDTO.converter(topicos);
    	}
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoFormDTO form, UriComponentsBuilder builderURI) {
		Topico topico = form.converter(cursoRepository);
    	topicoRepository.save(topico);
    	
    	URI uri = builderURI.path("/topicos/{Id}").buildAndExpand(topico.getId()).toUri();
    	return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable("id") Long id) {
    	Optional<Topico> topico = topicoRepository.findById(id);
    	if(topico.isPresent()) {
    		return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable("id") Long id,
    										   @RequestBody @Valid AtualizacaoTopicoFormDTO form) {
    	
    	Optional<Topico> optional = topicoRepository.findById(id);
    	if(optional.isPresent()) {
        	Topico topico = form.atualizar(id, topicoRepository);
        	return ResponseEntity.ok(new TopicoDTO(topico));
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
    	
    	Optional<Topico> optional = topicoRepository.findById(id);
    	if(optional.isPresent()) {
    		topicoRepository.deleteById(id);
        	return ResponseEntity.ok().build();
    	}
    	return ResponseEntity.notFound().build();
    }
}
	