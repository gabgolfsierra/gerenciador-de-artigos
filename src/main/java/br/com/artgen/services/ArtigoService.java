package br.com.artgen.services;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.repositories.ArtigoFirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoFirebaseRepository artigoFirebaseRepository;

    public ArtigoModel salvarArtigo(ArtigoModel artigo) throws Exception {
        return artigoFirebaseRepository.salvar(artigo);
    }
}
