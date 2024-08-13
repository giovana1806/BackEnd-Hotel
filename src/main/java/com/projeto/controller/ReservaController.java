package com.projeto.controller; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entities.Reserva;
import com.projeto.service.ReservaService;

import jakarta.validation.Valid; 

@RestController 
@RequestMapping("/reservas") 
public class ReservaController { 
  
	private final ReservaService reservaService; 
  
	@Autowired 
	public ReservaController(ReservaService reservaService) { 
		this.reservaService = reservaService; 
	} 
	 
	@GetMapping("/{id}") 
	public ResponseEntity<Reserva> buscaReservaControlId(@PathVariable Long id){ 
		Reserva reserva  = reservaService.buscaReservaId(id); 
		if(reserva != null) { 
			return ResponseEntity.ok(reserva); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
  
	} 
	@GetMapping 
	public ResponseEntity<List<Reserva>> buscaTodosReservasControl(){ 
		List<Reserva> reservas = reservaService.buscaTodosReservas(); 
		return ResponseEntity.ok(reservas); 
	} 
  
	@PostMapping 
	public ResponseEntity<Reserva> salvaReservasControl(@RequestBody @Valid Reserva reserva){ 
		Reserva salvaReserva = reservaService.salvaReserva(reserva); 
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaReserva); 
	} 
	 
	@PutMapping("/{id}") 
	public ResponseEntity<Reserva> alteraReservaControl(@PathVariable Long id, @RequestBody @Valid Reserva reserva){ 
		Reserva alteraReserva = reservaService.alterarReserva(id, reserva); 
		if(alteraReserva != null) { 
			return ResponseEntity.ok(reserva); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	 
	@DeleteMapping("/{id}") 
	public ResponseEntity<String> apagaReservaControl(@PathVariable Long id){ 
		boolean apagar = reservaService.apagarReserva(id); 
		if (apagar) { 
			return ResponseEntity.ok().body("A Reserva foi excluido com sucesso"); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
} 