package com.biel.atvtlp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biel.atvtlp2.modelo.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	Papel findByPapel(String papel);
}
