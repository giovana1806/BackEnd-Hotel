package com.projeto.repository; 



import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.entities.Reserva; 



public interface ReservaRepository extends JpaRepository<Reserva, Long> { 


}