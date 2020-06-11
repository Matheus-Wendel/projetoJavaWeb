package com.fatec.javaweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.javaweb.model.ServidorPublico;

public interface ServidorPublicoRepository extends JpaRepository<ServidorPublico, Long> {

	@Query("SELECT d from ServidorPublico d WHERE d.rendimentos >= :rendimentos ")
	List<ServidorPublico> buscaPorSalarioMaiorQue(@Param("rendimentos") Double rendimentos);

	@Query("SELECT d from ServidorPublico d WHERE d.rendimentos <= :rendimentos ")
	List<ServidorPublico> buscaPorSalarioMenorQue(@Param("rendimentos") Double rendimentos);

	@Query("SELECT d from ServidorPublico d WHERE d.nome  LIKE %:nome% ")
	List<ServidorPublico> findByNome(@Param("nome") String nome);

	@Query("SELECT d from ServidorPublico d WHERE d.rgf  = :rgf ")
	Optional<ServidorPublico> findByRgf(@Param("rgf") String rgf);

}
