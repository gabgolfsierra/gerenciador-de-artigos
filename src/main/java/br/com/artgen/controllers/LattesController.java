package br.com.artgen.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lattes")
@CrossOrigin(origins = "http://localhost:4200")
public class LattesController {

    @GetMapping("/artigo/{id}")
    public Map<String, Object> teste(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("titulo", "Artigo de teste com ID " + id);
        map.put("anoPublicacao", 2024);
        return map;
    }
}
