package net.slipp.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.slipp.CannotDeleteException;

@Entity
public class Answer extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	private boolean deleted = false;
	
	public Answer() {
	}
	
	public Answer(User writer, Question question, String contents) {
		this(0L, writer, question, contents);
	}
	
	public Answer(Long id, User writer, Question question, String contents) {
		super(id);
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.deleted = false;
	}
	
	public boolean isSameWriter(User loginUser) {
		return loginUser.equals(this.writer);
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void delete(User loginUser) throws CannotDeleteException {
		if (!writer.equals(loginUser)) {
			throw new CannotDeleteException("다른 사용자가 작성한 답변을 삭제할 수 없습니다.");
		}
		
		this.deleted = true;
	}

	@Override
	public String toString() {
		return "Answer [" + super.toString() + ", writer=" + writer + ", contents=" + contents + "]";
	}
}
