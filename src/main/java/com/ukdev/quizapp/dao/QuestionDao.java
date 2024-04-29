package com.ukdev.quizapp.dao;

import com.ukdev.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    // Extending JapRepository<ROM Class name/Table, PrimaryKey Type> help us to reduce few stuffs
}
