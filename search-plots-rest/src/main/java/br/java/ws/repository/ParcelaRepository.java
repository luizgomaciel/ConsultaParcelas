package br.java.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.java.ws.repository.entity.Parcela;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

}
