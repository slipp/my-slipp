package net.slipp.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	private Question question;
	private User writer = newUser(1L);
	
	@Before
	public void setup() {
		question = new Question();
		question.writeBy(writer);
	}

	@Test
	public void delete_다른_사람이_쓴_질문() throws Exception {
	}
	
	@Test
	public void delete_답변이_없는_자신이_쓴_질문() throws Exception {
	}
	
	@Test
	public void delete_자신이_쓴_답변이_있는_자신이_쓴_질문() throws Exception {
	}
	
	@Test
	public void delete_다른_사람이_쓴_답변이_있는_자신이_쓴_질문() throws Exception {
	}

	private User newUser(Long id) {
		return new User(id, "userId", "pass", "name", "javajigi@slipp.net");
	}
	
	private Answer newAnswer(Long id, User writer, Question question) {
		return new Answer(id, writer, question, "contents");
	}
}
