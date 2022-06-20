package br.com.saude.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import br.com.saude.sistema.model.Paciente;
import br.com.saude.sistema.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<Paciente> getPacienteList(){
		Iterable<Paciente> pacienteIterable = this.pacienteRepository.findAll();
		return Streamable.of(pacienteIterable).toList();
		
	}
}
