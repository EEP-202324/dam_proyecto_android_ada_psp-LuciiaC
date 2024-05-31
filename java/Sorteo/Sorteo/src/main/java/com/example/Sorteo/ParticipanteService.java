package com.example.Sorteo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private SorteoRepository sorteoRepository;

    // Método para obtener todos los participantes
    public List<Participante> findAll() {
        return sorteoRepository.findAll();
    }

    // Método para obtener un participante por ID
    public Optional<Participante> findById(int id) {
        return sorteoRepository.findById(id);
    }

    // Método para guardar un nuevo participante o actualizar uno existente
    public Participante save(Participante participante) {
        return sorteoRepository.save(participante);
    }

    // Método para eliminar un participante por ID
    public void deleteById(int id) {
        sorteoRepository.deleteById(id);
    }
}
