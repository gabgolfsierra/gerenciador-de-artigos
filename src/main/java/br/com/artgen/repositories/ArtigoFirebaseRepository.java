package br.com.artgen.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import br.com.artgen.models.ArtigoModel;
import org.springframework.stereotype.Repository;

@Repository
public class ArtigoFirebaseRepository {

    public ArtigoModel salvar(ArtigoModel artigo) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("artigos").document(String.valueOf(artigo.getId()));
        ApiFuture<WriteResult> result = docRef.set(artigo);
        System.out.println("Update time : " + result.get().getUpdateTime());
        return artigo;
    }
}