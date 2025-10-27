package br.com.artgen.services;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.repositories.ArtigoFirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {


    @Autowired
    private ArtigoFirebaseRepository artigoFirebaseRepository;

    public ArtigoModel salvarArtigo(ArtigoModel artigo) throws Exception {
        return artigoFirebaseRepository.salvar(artigo);
    }

    public List<ArtigoModel> listarArtigos() {
        return artigoFirebaseRepository.listarTodos();
    }

    public Optional<ArtigoModel> buscarArtigoPorId(Long id) {
        return artigoFirebaseRepository.buscarPorId(id);
    }

    public Optional<ArtigoModel> atualizarArtigo(Long id, ArtigoModel artigo) {
        return artigoFirebaseRepository.atualizar(id, artigo);
    }

    public boolean deletarArtigo(Long id) {
        return artigoFirebaseRepository.deletar(id);
    }
}
