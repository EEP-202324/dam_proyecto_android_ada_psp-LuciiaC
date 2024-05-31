package com.example.Sorteo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sorteo")
public class SorteoController {

    @Autowired
    private ParticipanteService participanteService;

    // GET para obtener todos los participantes
    @GetMapping
    public List<Participante> getAllParticipantes() {
        return participanteService.findAll();
    }

    // GET para obtener un participante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable int id) {
        Optional<Participante> participante = participanteService.findById(id);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST para crear un nuevo participante
    @PostMapping
    public Participante createParticipante(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    // PUT para actualizar un participante existente
    @PutMapping("/{id}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable int id, @RequestBody Participante participanteDetails) {
        Optional<Participante> participante = participanteService.findById(id);
        if (participante.isPresent()) {
            Participante updatedParticipante = participante.get();
            updatedParticipante.setDni(participanteDetails.getDni());
            updatedParticipante.setNombre(participanteDetails.getNombre());
            updatedParticipante.setNumeroTelefono(participanteDetails.getNumeroTelefono());
            updatedParticipante.setItemComprado(participanteDetails.getItemComprado());
            return ResponseEntity.ok(participanteService.save(updatedParticipante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE para eliminar un participante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable int id) {
        if (participanteService.findById(id).isPresent()) {
            participanteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


