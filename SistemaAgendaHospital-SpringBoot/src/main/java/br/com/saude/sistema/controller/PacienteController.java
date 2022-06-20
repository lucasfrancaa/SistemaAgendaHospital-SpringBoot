package br.com.saude.sistema.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.saude.sistema.model.Paciente;
import br.com.saude.sistema.repository.PacienteRepository;

@Controller
@RequestMapping
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public PacienteController (PacienteRepository pacienteRepository) {
		this.pacienteRepository = (PacienteRepository) pacienteRepository;
	}
	
	@GetMapping("/sistema/index")
	public String telaInicial() {
		return "/sistema/index";
	}
	
	@GetMapping("sistema/paciente")
	public String paciente(Model model) {
		model.addAttribute("listaPaciente", pacienteRepository.findAll());
		return "/sistema/paciente/telapaciente";
	} 
	
	@GetMapping("/sistema/paciente/novo")
	public String novoPaciente(@ModelAttribute("paciente") Paciente paciente) {
	
		return "/sistema/paciente/formpaciente";
	}
	
	@PostMapping("/sistema/paciente/salvar")
	public String salvarPaciente(@ModelAttribute("paciente") Paciente paciente) {
		pacienteRepository.save(paciente);
		return "redirect:/sistema/paciente";
	}
	
	@GetMapping("/sistema/paciente/{id}")
	public String alterarPaciente(@PathVariable("id") Long id, Model model) {
		Optional<Paciente> pacienteOpt = pacienteRepository.findById(id);
		if (!pacienteOpt.isPresent()) {
			throw new IllegalArgumentException("Paciente inválido.");
		}
		
		model.addAttribute("paciente", pacienteOpt.get());
		
		return "/sistema/paciente/formpaciente";
	}
	
	@GetMapping("/sistema/paciente/excluir/{id}")
	public String excluirPaciente(@PathVariable("id") Long id) {
		Optional<Paciente> pacienteOpt = pacienteRepository.findById(id);
		if (pacienteOpt.isEmpty()) {
			throw new IllegalArgumentException("Paciente inválido.");
		}
		
		pacienteRepository.delete(pacienteOpt.get());
		return "redirect:/sistema/paciente";
		
	}
}