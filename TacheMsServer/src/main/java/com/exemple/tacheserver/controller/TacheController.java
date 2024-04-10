package com.exemple.tacheserver.controller;

import com.exemple.tacheserver.model.Tache;
import com.exemple.tacheserver.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;



    @GetMapping("/{id}")
    public ResponseEntity<Tache> gettacheById(@PathVariable Long id) {
        Optional<Tache> taches = tacheService.getTacheById(id);
        return taches.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tache> createProjet(@RequestBody Tache tache) {
        Tache createdTache= tacheService.saveOrUpdateTache(tache);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTache);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        tache.setId(id);
        Tache updatedTache = tacheService.saveOrUpdateTache(tache);
        return ResponseEntity.ok(updatedTache);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
}
