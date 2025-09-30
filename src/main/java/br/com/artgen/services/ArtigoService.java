package br.com.artgen.services;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.repositories.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    public ArtigoModel salvarArtigo(ArtigoModel artigo){
        return artigoRepository.save(artigo);
    }
}
