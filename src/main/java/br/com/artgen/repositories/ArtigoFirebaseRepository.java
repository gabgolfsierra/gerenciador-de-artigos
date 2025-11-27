package br.com.artgen.repositories;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import br.com.artgen.models.ArtigoModel;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.concurrent.ExecutionException;

@Repository
public class ArtigoFirebaseRepository {

    private static final String COLLECTION = "artigos";

    private Firestore db() {
        return FirestoreClient.getFirestore();
    }

    public ArtigoModel salvar(ArtigoModel artigo) throws Exception {
        if (artigo == null || artigo.getId() == null)
            throw new IllegalArgumentException("Trabalho e ID não podem ser nulos");

        Firestore db = db();
        DocumentReference ref = db.collection(COLLECTION).document(String.valueOf(artigo.getId()));
        ApiFuture<WriteResult> future = ref.set(artigo, SetOptions.merge());
        future.get();
        return artigo;
    }

    public List<ArtigoModel> listarTodos() {
        try {
            Firestore db = db();
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
            return future.get().toObjects(ArtigoModel.class);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}