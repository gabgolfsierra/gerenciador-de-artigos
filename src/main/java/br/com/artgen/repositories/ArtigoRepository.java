package br.com.artgen.repositories;

import br.com.artgen.models.ArtigoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<ArtigoModel, Long>{
}
