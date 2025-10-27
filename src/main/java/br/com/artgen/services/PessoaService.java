package br.com.artgen.services;

import br.com.artgen.models.PessoaModel;
import br.com.artgen.repositories.PessoaFirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaFirebaseRepository pessoaRepository;

    public PessoaModel salvarPessoa(PessoaModel pessoa) throws Exception {
        return pessoaRepository.salvar(pessoa);
    }

    public List<PessoaModel> listarPessoas() {
        return pessoaRepository.listarTodos();
    }

    public Optional<PessoaModel> buscarPessoaPorId(Long id) {
        return pessoaRepository.buscarPorId(id);
    }

    public Optional<PessoaModel> atualizarPessoa(Long id, PessoaModel pessoaAtualizada) {
        return pessoaRepository.atualizar(id, pessoaAtualizada);
    }

    public boolean deletarPessoa(Long id) {
        return pessoaRepository.deletar(id);
    }
}
