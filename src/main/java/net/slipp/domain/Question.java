package net.slipp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.slipp.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private Integer countOfAnswer = 0;
	
	@OneToMany(mappedBy="question")
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private List<Answer> answers = new ArrayList<Answer>();
	
	private boolean deleted;
	
	public Question() {}
	
	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public void writeBy(User writer) {
		this.writer = writer;
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		System.out.println("writer : " + writer);
		return this.writer.equals(loginUser);
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void addAnswer() {
		this.countOfAnswer += 1;
	}
	
	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}
	
	public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
		if (!isSameWriter(loginUser)) {
			throw new CannotDeleteException("다른 사람의 글은 삭제할 수 없다.");
		}
		
		List<DeleteHistory> histories = new ArrayList<>();
		for (Answer answer : answers) {
			histories.add(answer.delete(loginUser));
		}
		
		this.deleted = true;
		
		histories.add(new DeleteHistory(ContentType.QUESTION, getId(), loginUser, LocalDateTime.now()));
		return histories;
	}
}
