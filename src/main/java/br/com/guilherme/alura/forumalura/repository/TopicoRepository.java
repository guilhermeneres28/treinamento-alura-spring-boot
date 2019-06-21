package br.com.guilherme.alura.forumalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guilherme.alura.forumalura.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
