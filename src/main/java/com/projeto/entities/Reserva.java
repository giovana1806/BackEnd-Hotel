package com.projeto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reserva")
public class Reserva {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;
	
	@Column(name = "checkin", nullable = false, length = 255)
	private String checkin;
	
	@Column(name = "checkout", nullable = false, length = 255)
	private String checkout;
	
	@ManyToOne 
	@JoinColumn(name = "id_quarto", nullable = false)
	private Quarto quarto;
	
	@ManyToOne
	@JoinColumn(name = "id_hospede", nullable = false)
	private Hospede hospede;
}
