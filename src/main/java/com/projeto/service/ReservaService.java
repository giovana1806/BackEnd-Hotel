package com.projeto.service; 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.entities.Reserva;
import com.projeto.repository.ReservaRepository; 
  
@Service 
public class ReservaService { 
  
	private final ReservaRepository reservaRepository; 
  
	@Autowired 
	public ReservaService(ReservaRepository reservaRepository) { 
		this.reservaRepository = reservaRepository; 
	} 
	public List<Reserva> buscaTodosReservas(){ 
		return reservaRepository.findAll(); 
	} 
	public Reserva buscaReservaId(Long id) { 
		Optional <Reserva> existeReserva = reservaRepository.findById(id); 
		return existeReserva.orElse(null); 
	} 
	public Reserva salvaReserva(Reserva reserva) { 
		return reservaRepository.save(reserva);		 
	} 
	public Reserva alterarReserva(Long id, Reserva alterarReserva) { 
		Optional <Reserva> existeReserva = reservaRepository.findById(id); 
		if (existeReserva.isPresent()) { 
			alterarReserva.setId(id);; 
			return reservaRepository.save(alterarReserva); 
		} 
		return null; 
	} 
	public boolean apagarReserva(Long id) { 
		Optional <Reserva> existeReserva = reservaRepository.findById(id); 
		if (existeReserva.isPresent()) { 
			reservaRepository.deleteById(id); 
			return true; 
		} 
		return false; 
	} 
} 