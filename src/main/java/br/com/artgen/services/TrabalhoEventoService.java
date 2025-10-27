package br.com.artgen.services;

import br.com.artgen.models.TrabalhoEventoModel;
import br.com.artgen.repositories.TrabalhoEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabalhoEventoService {

    private final TrabalhoEventoRepository repository;

    public TrabalhoEventoModel salvar(TrabalhoEventoModel trabalho) throws Exception {
        if (trabalho.getId() == null) {
            trabalho.setId(System.currentTimeMillis());
        }
        return repository.salvar(trabalho);
    }

    public List<TrabalhoEventoModel> listarTodos() {
        return repository.listarTodos();
    }
}