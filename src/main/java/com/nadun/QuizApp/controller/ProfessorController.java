package com.nadun.QuizApp.controller;

import com.nadun.QuizApp.model.Professor;
import com.nadun.QuizApp.service.ProfesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("Professors")
public class ProfessorController {
    @Autowired
    ProfesserService professerService;

    @GetMapping("/")
    public List<Professor> getAllProfessors() {
        return professerService.getAllProfessors();

    }
   @GetMapping("/{id}")
   public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
      Optional<Professor> professor = professerService.getProfessorById(id);
        return professor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }


    @PatchMapping("/")
    public ResponseEntity<?> createProfessor(@RequestBody Professor professor) {
        // Vérifier si l'adresse e-mail existe déjà dans la base de données
        boolean emailExists = professerService.isEmailExists(professor.getEmail());

        if (emailExists) {
            String errorMessage = "L'adresse e-mail est déjà utilisée.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        Professor savedProfessor = professerService.saveProfessor(professor);
        return ResponseEntity.ok(savedProfessor);
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long professorId) {
        // Appelez la méthode du service pour supprimer le professeur avec l'ID spécifié
        // boolean isDeleted = professerService.deleteProfessor(professorId);
        boolean isDeleted =professerService.deleteProfessor(professorId);

        if (isDeleted) {
            // Retournez une réponse indiquant que la suppression a réussi
            return ResponseEntity.ok("Professor deleted successfully");
        } else {
            // Retournez une réponse indiquant que l'ID du professeur n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor with ID " + professorId + " not found");
        }
    }
}

