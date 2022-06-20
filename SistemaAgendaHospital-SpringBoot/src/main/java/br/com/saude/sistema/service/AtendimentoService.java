package br.com.saude.sistema.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saude.sistema.model.Atendimento;
import br.com.saude.sistema.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentos;
	
	public void salvar(Atendimento atendimento) {
		this.atendimentos.save(atendimento);
	}

	public void save(@Valid Atendimento atendimento) {
		// TODO Auto-generated method stub
	}

	public Atendimento findById(Long id) {
		// TODO Auto-generated method stub
		return this.atendimentos.findById(id).get();
	}
}
