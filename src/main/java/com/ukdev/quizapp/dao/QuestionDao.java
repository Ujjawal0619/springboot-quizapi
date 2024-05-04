package com.ukdev.quizapp.dao;

import com.ukdev.quizapp.modal.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    // Extending JapRepository<ROM Class name/Table, PrimaryKey Type> help us to reduce few stuffs
    List<Question> findByCategory(String category);
    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
