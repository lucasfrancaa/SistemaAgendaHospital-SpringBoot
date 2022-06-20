package br.com.saude.sistema.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.saude.sistema.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
