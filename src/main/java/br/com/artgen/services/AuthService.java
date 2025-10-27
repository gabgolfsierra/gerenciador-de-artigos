package br.com.artgen.services;

import br.com.artgen.dtos.AuthResponse;
import br.com.artgen.dtos.LoginRequest;
import br.com.artgen.dtos.RegisterRequest;
import br.com.artgen.models.UserModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String COLLECTION = "users";

    private Firestore db() { return FirestoreClient.getFirestore(); }

    public AuthResponse register(RegisterRequest req) throws Exception {
        String email = req.getEmail() == null ? "" : req.getEmail().trim().toLowerCase();
        String senha = req.getSenha() == null ? "" : req.getSenha();
        if (email.isEmpty() || senha.isEmpty()) throw new IllegalArgumentException("email e senha são obrigatórios");

        DocumentReference ref = db().collection(COLLECTION).document(email);
        DocumentSnapshot snap = ref.get().get();
        if (snap.exists()) throw new IllegalStateException("email já cadastrado");

        UserModel user = new UserModel();
        user.setId(email);
        user.setEmail(email);
        user.setSenhaHash(senha);
        user.setCreatedAt(System.currentTimeMillis());

        ApiFuture<WriteResult> write = ref.set(user);
        write.get();

        AuthResponse resp = new AuthResponse();
        resp.setUserId(user.getId());
        resp.setEmail(user.getEmail());
        resp.setToken(UUID.randomUUID().toString());
        return resp;
    }

    public AuthResponse login(LoginRequest req) throws Exception {
        String email = req.getEmail() == null ? "" : req.getEmail().trim().toLowerCase();
        String senha = req.getSenha() == null ? "" : req.getSenha();
        if (email.isEmpty() || senha.isEmpty()) throw new IllegalArgumentException("email e senha são obrigatórios");

        DocumentReference ref = db().collection(COLLECTION).document(email);
        DocumentSnapshot snap = ref.get().get();
        if (!snap.exists()) throw new IllegalArgumentException("credenciais inválidas");

        UserModel user = snap.toObject(UserModel.class);
        if (user == null) throw new IllegalStateException("usuário inválido");
        if (!senha.equals(user.getSenhaHash())) throw new IllegalArgumentException("credenciais inválidas");

        AuthResponse resp = new AuthResponse();
        resp.setUserId(user.getId());
        resp.setEmail(user.getEmail());
        resp.setToken(UUID.randomUUID().toString());
        return resp;
    }
}