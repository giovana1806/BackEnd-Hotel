package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.entities.Hospede;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

}