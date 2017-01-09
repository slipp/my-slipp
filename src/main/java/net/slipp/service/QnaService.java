package net.slipp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.slipp.CannotDeleteException;
import net.slipp.domain.DeleteHistory;
import net.slipp.domain.DeleteHistoryRepository;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;

@Service
public class QnaService {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private DeleteHistoryRepository deleteHistoryRepository;
	
	@Transactional
    public void deleteQuestion(long questionId, User loginUser) throws CannotDeleteException {
    	Question question = questionRepository.findOne(questionId);
		if (question == null) {
			throw new EmptyResultDataAccessException("존재하지 않는 질문입니다.", 1);
		}
		
		List<DeleteHistory> historeis = question.delete(loginUser);
		for (DeleteHistory deleteHistory : historeis) {
			deleteHistoryRepository.save(deleteHistory);
		}
    }
}
