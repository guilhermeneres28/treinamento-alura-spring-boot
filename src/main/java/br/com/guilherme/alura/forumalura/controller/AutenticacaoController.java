package br.com.guilherme.alura.forumalura.controller;

import br.com.guilherme.alura.forumalura.dto.LoginFormDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginFormDTO form) {
        System.out.println(form.getEmail());
        System.out.println(form.getSenha());

        return ResponseEntity.ok().build();
    }
}
