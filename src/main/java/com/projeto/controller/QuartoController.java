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

import com.projeto.entities.Quarto;
import com.projeto.service.QuartoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/quartos")
public class QuartoController {
	
	private final QuartoService quartoService; 

	@Autowired 
	public QuartoController(QuartoService quartoService) { 
		this.quartoService = quartoService; 
	} 
	 
	@GetMapping("/{id}") 
	public ResponseEntity<Quarto> buscaQuartoControlId(@PathVariable Long id){ 
		Quarto quarto = quartoService.buscaQuartoId(id); 
		if(quarto != null) { 
			return ResponseEntity.ok(quarto); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
 
	} 
	@GetMapping 
	public ResponseEntity<List<Quarto>> buscaTodosQuartosControl(){ 
		List<Quarto> quartos = quartoService.buscaTodosQuartos(); 
		return ResponseEntity.ok(quartos); 
	} 
 
	@PostMapping 
	public ResponseEntity<Quarto> salvaQuartosControl(@RequestBody @Valid Quarto quarto){ 
		Quarto salvaQuarto = quartoService.salvaQuarto(quarto); 
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaQuarto); 
	} 
	 
	@PutMapping("/{id}") 
	public ResponseEntity<Quarto> alteraQuartoControl(@PathVariable Long id, @RequestBody @Valid Quarto quarto){ 
		Quarto alteraQuarto = quartoService.alterarQuarto(id, quarto); 
		if(alteraQuarto != null) { 
			return ResponseEntity.ok(quarto); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	 
	@DeleteMapping("/{id}") 
	public ResponseEntity<String> apagaQuartoControl(@PathVariable Long id){ 
		boolean apagar = quartoService.apagarQuarto(id); 
		if (apagar) { 
			return ResponseEntity.ok().body("O Quarto foi excluido com sucesso"); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	}
 
}