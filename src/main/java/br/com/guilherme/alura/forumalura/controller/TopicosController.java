package br.com.guilherme.alura.forumalura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilherme.alura.forumalura.dto.TopicoDTO;
import br.com.guilherme.alura.forumalura.model.Topico;
import br.com.guilherme.alura.forumalura.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDTO> lista() {
        List<Topico> topicos = topicoRepository.findAll();

        return TopicoDTO.converter(topicos);
    }
}
