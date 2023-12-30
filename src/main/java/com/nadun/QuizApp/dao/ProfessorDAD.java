package com.nadun.QuizApp.dao;

import com.nadun.QuizApp.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDAD extends JpaRepository<Professor,Long> {

    boolean existsByEmail(String email);
}
