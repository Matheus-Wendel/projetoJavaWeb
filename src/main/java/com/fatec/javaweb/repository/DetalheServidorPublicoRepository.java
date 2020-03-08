package com.fatec.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.javaweb.model.DetalheServidorPublico;

public interface DetalheServidorPublicoRepository extends JpaRepository<DetalheServidorPublico, Long> {

	@Query("SELECT d from DetalheServidorPublico d WHERE d.nome LIKE %:nome%")
	List<DetalheServidorPublico> findByNome(@Param("nome") String nome);
	
}
