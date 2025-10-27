package br.com.artgen.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import br.com.artgen.models.PessoaModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PessoaFirebaseRepository {

    public PessoaModel salvar(PessoaModel pessoa) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("pessoas").document(String.valueOf(pessoa.getId()));
        ApiFuture<WriteResult> result = docRef.set(pessoa);
        System.out.println("Update time : " + result.get().getUpdateTime());
        return pessoa;
    }

    public List<PessoaModel> listarTodos() {
        Firestore db = FirestoreClient.getFirestore();
        List<PessoaModel> pessoas = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("pessoas").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                pessoas.add(doc.toObject(PessoaModel.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public Optional<PessoaModel> buscarPorId(Long id) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = db.collection("pessoas").document(String.valueOf(id));
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return Optional.of(document.toObject(PessoaModel.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<PessoaModel> atualizar(Long id, PessoaModel pessoa) {
        try {
            salvar(pessoa);
            return Optional.of(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean deletar(Long id) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = db.collection("pessoas").document(String.valueOf(id));
            ApiFuture<WriteResult> writeResult = docRef.delete();
            writeResult.get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
