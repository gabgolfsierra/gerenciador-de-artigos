package br.com.artgen.services;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.models.TrabalhoEventoModel;
import br.com.artgen.repositories.ArtigoFirebaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtigoService {

    private final ArtigoFirebaseRepository repository;

    public ArtigoModel salvar(ArtigoModel artigo) throws Exception {
        if (artigo.getId() == null) {
            artigo.setId(System.currentTimeMillis());
        }
        return repository.salvar(artigo);
    }

    public List<ArtigoModel> listarTodos() {
        return repository.listarTodos();
    }

}
