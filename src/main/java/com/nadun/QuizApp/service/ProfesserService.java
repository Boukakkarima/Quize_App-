package com.nadun.QuizApp.service;

import com.nadun.QuizApp.dao.ProfessorDAD;
import com.nadun.QuizApp.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesserService {
    @Autowired
     ProfessorDAD professorDAD;

    public List<Professor> getAllProfessors() {
        return professorDAD.findAll();
    }
    public Optional<Professor> getProfessorById(Long professorId) {
        return professorDAD.findById(professorId);
    }

    public Professor saveProfessor(Professor professor) {
        return professorDAD.save(professor);
    }

    public boolean isEmailExists(String email) {
        // Logique de vérification de l'existence de l'adresse e-mail dans la base de données
        return professorDAD.existsByEmail(email);
    }
    public void updateProfessor(Long professorId, Professor professor) {
        Optional<Professor> optionalProfessor = professorDAD.findById(professorId);
        if (optionalProfessor.isPresent()) {
            Professor existingProfessor = optionalProfessor.get();
            existingProfessor.setName(professor.getName());
            existingProfessor.setSpecialty(professor.getSpecialty());
            professorDAD.save(existingProfessor);
        }
    }



    // Logique de suppression du professeur  public boolean deleteProfessor(Long professorId) {
    // Logique de suppression du professeur   professorDAD.deleteById(professorId);
    // Logique de suppression du professeur  return false;
    // Logique de suppression du professeur  }

    public boolean deleteProfessor(Long professorId) {
        // Logique de suppression du professeur
        try {
            // Vérifiez si le professeur existe avant de le supprimer
            if (professorDAD.existsById(professorId)) {
                professorDAD.deleteById(professorId);
                return true; // La suppression a réussi
            } else {
                return false; // Le professeur avec l'ID spécifié n'existe pas
            }
        } catch (Exception e) {
            // Gérez l'exception si nécessaire
            throw new RuntimeException("Error occurred while deleting professor");
        }
    }
}
