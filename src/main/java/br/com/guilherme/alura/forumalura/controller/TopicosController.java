package br.com.guilherme.alura.forumalura.controller;

import br.com.guilherme.alura.forumalura.model.Curso;
import br.com.guilherme.alura.forumalura.model.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    public List<Topico> lista() {
        Topico topico = new Topico("Dúvida Spring boot",
                "Tenho uma duvida sobre spring boot",
                new Curso("SpringBoot 1", "Programação"));

        return Arrays.asList(topico, topico, topico);
    }

}
