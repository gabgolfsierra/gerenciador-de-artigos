package br.com.artgen.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import br.com.artgen.models.ArtigoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class ArtigoFirebaseRepository {

    private static final String COLLECTION = "artigos";

    private Firestore db() {
        return FirestoreClient.getFirestore();
    }

    /** Cria/atualiza usando docId = artigo.id (obrigatório existir). */
    public ArtigoModel salvar(ArtigoModel artigo) throws Exception {
        if (artigo == null || artigo.getId() == null) {
            throw new IllegalArgumentException("Artigo e Artigo.id não podem ser nulos");
        }
        Firestore db = db();
        DocumentReference docRef = db.collection(COLLECTION).document(String.valueOf(artigo.getId()));
        // merge para não sobrescrever campos ausentes por null sem querer
        ApiFuture<WriteResult> result = docRef.set(artigo, SetOptions.merge());
        WriteResult wr = result.get();
        System.out.println("[Firestore] Upsert artigo id=" + artigo.getId() + " at " + wr.getUpdateTime());
        return artigo;
    }

    /** Lista todos os artigos; se vazio, verifique projeto/coleção e tipos dos campos. */
    public List<ArtigoModel> listarTodos() {
        Firestore db = db();
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
            QuerySnapshot snapshot = future.get();
            List<ArtigoModel> lista = snapshot.toObjects(ArtigoModel.class);
            System.out.println("[Firestore] listarTodos -> " + lista.size() + " documentos");
            return lista;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca por ID. Primeiro tenta docId == id; se não existir,
     * faz fallback por campo "id" (útil se documentos antigos foram salvos com docId aleatório).
     */
    public Optional<ArtigoModel> buscarPorId(Long id) {
        Firestore db = db();
        try {
            // 1) tentativa por documentId
            DocumentReference docRef = db.collection(COLLECTION).document(String.valueOf(id));
            DocumentSnapshot doc = docRef.get().get();
            if (doc.exists()) {
                return Optional.ofNullable(doc.toObject(ArtigoModel.class));
            }

            // 2) fallback por campo "id"
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION)
                    .whereEqualTo("id", id)
                    .limit(1)
                    .get();
            List<ArtigoModel> achados = future.get().toObjects(ArtigoModel.class);
            return achados.stream().findFirst();

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return Optional.empty();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /** Atualiza garantindo que o id do path é o mesmo do payload. Usa merge() para não apagar campos. */
    public Optional<ArtigoModel> atualizar(Long id, ArtigoModel artigo) {
        if (artigo == null) return Optional.empty();
        if (artigo.getId() == null) artigo.setId(id);
        if (!id.equals(artigo.getId())) {
            throw new IllegalArgumentException("ID do path (" + id + ") difere do payload (" + artigo.getId() + ")");
        }
        try {
            Firestore db = db();
            DocumentReference docRef = db.collection(COLLECTION).document(String.valueOf(id));
            WriteResult wr = docRef.set(artigo, SetOptions.merge()).get();
            System.out.println("[Firestore] atualizar id=" + id + " at " + wr.getUpdateTime());
            return Optional.of(artigo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean deletar(Long id) {
        Firestore db = db();
        try {
            DocumentReference docRef = db.collection(COLLECTION).document(String.valueOf(id));
            docRef.delete().get();
            System.out.println("[Firestore] deletado id=" + id);
            return true;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
