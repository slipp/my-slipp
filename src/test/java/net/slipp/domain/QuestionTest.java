package net.slipp.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.slipp.CannotDeleteException;

public class QuestionTest {
	private Question question;
	private User writer = newUser(1L);
	
	@Before
	public void setup() {
		question = new Question();
		question.writeBy(writer);
	}

	@Test(expected = CannotDeleteException.class)
	public void delete_다른_사람이_쓴_질문() throws Exception {
		question.delete(newUser(2L));
	}
	
	@Test
	public void delete_답변이_없는_자신이_쓴_질문() throws Exception {
		assertFalse(question.isDeleted());
		question.delete(newUser(1L));
		assertTrue(question.isDeleted());
	}
	
	@Test
	public void delete_자신이_쓴_답변이_있는_자신이_쓴_질문() throws Exception {
		Answer answer = newAnswer(1L, writer, question);
		question.addAnswer(answer);
		question.delete(newUser(1L));
		assertTrue(question.isDeleted());
	}
	
	@Test(expected=CannotDeleteException.class)
	public void delete_다른_사람이_쓴_답변이_있는_자신이_쓴_질문() throws Exception {
		Answer answer = newAnswer(1L, newUser(2L), question);
		question.addAnswer(answer);
		question.delete(newUser(1L));
	}

	private User newUser(Long id) {
		return new User(id, "userId", "pass", "name", "javajigi@slipp.net");
	}
	
	private Answer newAnswer(Long id, User writer, Question question) {
		return new Answer(id, writer, question, "contents");
	}
}
