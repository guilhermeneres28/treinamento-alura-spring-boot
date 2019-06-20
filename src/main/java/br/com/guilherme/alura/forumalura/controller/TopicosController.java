package br.com.guilherme.alura.forumalura.controller;

import br.com.guilherme.alura.forumalura.dto.TopicoDTO;
import br.com.guilherme.alura.forumalura.model.Curso;
import br.com.guilherme.alura.forumalura.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDTO> lista() {
        Topico topico = new Topico("Dúvida Spring boot",
                "Tenho uma duvida sobre spring boot",
                new Curso("SpringBoot 1", "Programação"));

        return TopicoDTO.converter(asList(topico, topico, topico));
    }
}
