package com.ukdev.quizapp.dao;

import com.ukdev.quizapp.modal.Question;
import com.ukdev.quizapp.modal.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
