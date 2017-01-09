package net.slipp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.slipp.CannotDeleteException;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;

@Service
public class QnaService {
	@Autowired
	private QuestionRepository questionRepository;
	
    public void deleteQuestion(long questionId, User loginUser) throws CannotDeleteException {
        
    }
}
