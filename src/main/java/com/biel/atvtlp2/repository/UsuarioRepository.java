package com.biel.atvtlp2.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.biel.atvtlp2.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);// mudar para roupa
	
}
