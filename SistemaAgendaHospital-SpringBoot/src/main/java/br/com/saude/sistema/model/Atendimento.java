package br.com.saude.sistema.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "atendimento")
@Entity(name = "atendimento")
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dataatendimento")
	private LocalDate dataatendimento;
	
	@Column(name = "hora")
	private LocalTime hora;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "observacao")
	private String observacao;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(LocalDate dataatendimento) {
		this.dataatendimento = dataatendimento;
	}

	public LocalTime getHoraatendimento() {
		return hora;
	}

	public void setHoraatendimento(LocalTime hora) {
		this.hora = hora;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}