package com.ukdev.quizapp.service;

import com.ukdev.quizapp.dao.QuestionDao;
import com.ukdev.quizapp.dao.QuizDao;
import com.ukdev.quizapp.modal.Question;
import com.ukdev.quizapp.modal.QuestionWrapper;
import com.ukdev.quizapp.modal.Quiz;
import com.ukdev.quizapp.modal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );

            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        Map<Integer, String> qMap = new HashMap<Integer, String>();

        for(Question q: questions) {
            qMap.put(q.getId(), q.getRightAnswer());
        }

        int right = 0;
        for(Response response: responses) {
            if(response.getResponse().equals(qMap.get(response.getId())))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
