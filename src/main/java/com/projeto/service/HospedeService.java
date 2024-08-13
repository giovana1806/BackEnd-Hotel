package com.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entities.Hospede;
import com.projeto.repository.HospedeRepository;

@Service
public class HospedeService {

	private final HospedeRepository hospedeRepository;

	@Autowired
	public HospedeService(HospedeRepository hospedeRepository) {
		this.hospedeRepository = hospedeRepository;
	}
	public List<Hospede> buscaTodosHospedes(){
		return hospedeRepository.findAll();
	}
	public Hospede buscaHospedeId(Long id) {
		Optional <Hospede> existeHospede = hospedeRepository.findById(id);
		return existeHospede.orElse(null);
	}
	public Hospede salvaHospede(Hospede hospede) {
		return hospedeRepository.save(hospede);		
	}
	public Hospede alterarHospede(Long id, Hospede alterarHospede) {
		Optional <Hospede> existeHospede = hospedeRepository.findById(id);
		if (existeHospede.isPresent()) {
			alterarHospede.setId(id);;
			return hospedeRepository.save(alterarHospede);
		}
		return null;
	}
	public boolean apagarHospede(Long id) {
		Optional <Hospede> existeHospede = hospedeRepository.findById(id);
		if (existeHospede.isPresent()) {
			hospedeRepository.deleteById(id);
			return true;
		}
		return false;
	}
}