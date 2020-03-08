package com.fatec.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.javaweb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.email = :email")
	Optional<Usuario> login(@Param("email")String email);
}
