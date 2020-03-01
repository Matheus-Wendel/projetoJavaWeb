package com.fatec.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.javaweb.model.ServidorInfoMax;

public interface ServidorInfoMaxRepository extends JpaRepository<ServidorInfoMax, Long> {

	@Query("SELECT f from funcionario f WHERE f.nome LIKE %:nome%")
	List<ServidorInfoMax> findByNome(@Param("nome") String nome);
	
}
