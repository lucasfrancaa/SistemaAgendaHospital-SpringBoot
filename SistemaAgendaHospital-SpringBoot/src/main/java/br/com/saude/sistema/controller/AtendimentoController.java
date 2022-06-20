package br.com.saude.sistema.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.saude.sistema.model.Atendimento;
import br.com.saude.sistema.model.Paciente;
import br.com.saude.sistema.repository.AtendimentoRepository;
import br.com.saude.sistema.service.AtendimentoService;
import br.com.saude.sistema.service.PacienteService;

@Controller
public class AtendimentoController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	public AtendimentoController (AtendimentoRepository atendimentosRepository) {
		this.atendimentoRepository = atendimentosRepository;
	}
	
	@GetMapping("sistema/atendimento")
	public String atendimento(Model model) {
		model.addAttribute("listaAtendimento",atendimentoRepository.findAll());
		return "/sistema/atendimento/telaatendimento";
	} 
	
	/*@GetMapping("/sistema/atendimento/novo")
	public String novoAtendimento(@ModelAttribute("atendimento") Atendimento atendimento) {
	
	List<Cliente> clienteList = this.clienteService.getClienteList();
		return "/sistema/atendimento/formatendimento";
	}*/
	
	@GetMapping("/sistema/atendimento/novo")
	public ModelAndView novoAtendimento(Atendimento atendimento) {
		ModelAndView mv = new ModelAndView("/sistema/atendimento/FormAtendimento");
		List<Paciente> pacienteList = this.pacienteService.getPacienteList();
		mv.addObject(pacienteList);
		return mv;
	}
	
	@PostMapping("/sistema/atendimento/salvar")
	public String salvarAtendimento(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes redirect) {
		atendimentoRepository.save(atendimento);
		if(result.hasErrors()) {
			redirect.addFlashAttribute("Verifique os campos obrigatórios");
			return "redirect:/sistema/atendimento/FormAtendimento";
		}
		this.atendimentoService.save(atendimento);
		
		return "redirect:/sistema/atendimento";
	}
	
	/*@GetMapping("/sistema/atendimento/{idatendimento}")
	public String alterarAtendimento(@PathVariable("idatendimento") Long idatendimento, Model model) {
		Optional<Atendimento> atendimentoOpt = atendimentosRepository.findById(idatendimento);
		if (!atendimentoOpt.isPresent()) {
			throw new IllegalArgumentException("Agendamento inválido.");
		}
		
		model.addAttribute("atendimento", atendimentoOpt.get());
		
		return "/sistema/atendimento/formatendimento";
	}*/
	
	@GetMapping("/sistema/atendimento/{id}")
	public ModelAndView editarAtendimento(@PathVariable ("id") Long id){
			
		
			ModelAndView mv = new ModelAndView("/sistema/atendimento/FormAtendimento");
			List<Paciente> pacienteList = this.pacienteService.getPacienteList();
			mv.addObject(pacienteList);
		
			Atendimento atendimento = this.atendimentoService.findById(id);
			mv.addObject(atendimento);
			return mv;
	}
	
	
	/* APAGARRRRRRRRRRR
	 * 	@GetMapping("/sistema/atendimento/novo")
	public ModelAndView novoAtendimento(Atendimento atendimento) {
		ModelAndView mv = new ModelAndView("/sistema/atendimento/formatendimento");
		List<Cliente> clienteList = this.clienteService.getClienteList();
		mv.addObject(clienteList);
		return mv;
	}
	*/
	
	
	
	@GetMapping("/sistema/atendimento/excluir/{id}")
	public String excluirAtendimento(@PathVariable("id") Long id) {
		Optional<Atendimento> atendimentoOpt = atendimentoRepository.findById(id);
		if (atendimentoOpt.isEmpty()) {
			throw new IllegalArgumentException("Atendimento inválido.");
		}
		atendimentoRepository.delete(atendimentoOpt.get());
		return "redirect:/sistema/atendimento";
		
	}
}
