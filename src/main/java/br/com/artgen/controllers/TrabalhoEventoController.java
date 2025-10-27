package br.com.artgen.controllers;
import br.com.artgen.models.TrabalhoEventoModel;
import br.com.artgen.services.TrabalhoEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trabalhos-eventos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TrabalhoEventoController {

    private final TrabalhoEventoService service;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TrabalhoEventoModel trabalho) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(trabalho));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar trabalho: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}
