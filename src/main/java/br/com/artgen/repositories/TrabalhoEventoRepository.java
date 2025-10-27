package br.com.artgen.repositories;

import br.com.artgen.models.TrabalhoEventoModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class TrabalhoEventoRepository {
    private static final String COLLECTION = "trabalhos_eventos";

    private Firestore db() {
        return FirestoreClient.getFirestore();
    }

    public TrabalhoEventoModel salvar(TrabalhoEventoModel trabalho) throws Exception {
        if (trabalho == null || trabalho.getId() == null)
            throw new IllegalArgumentException("Trabalho e ID não podem ser nulos");

        Firestore db = db();
        DocumentReference ref = db.collection(COLLECTION).document(String.valueOf(trabalho.getId()));
        ApiFuture<WriteResult> future = ref.set(trabalho, SetOptions.merge());
        future.get();
        return trabalho;
    }

    public List<TrabalhoEventoModel> listarTodos() {
        try {
            Firestore db = db();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
            return future.get().toObjects(TrabalhoEventoModel.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
