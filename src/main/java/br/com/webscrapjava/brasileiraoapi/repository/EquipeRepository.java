package br.com.webscrapjava.brasileiraoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.webscrapjava.brasileiraoapi.entity.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long>  {
	
	public Optional<Equipe> findByNomeEquipe(String nomeEquipe);
	
	public boolean existsByNomeEquipe(String nomeEquipe);

}
