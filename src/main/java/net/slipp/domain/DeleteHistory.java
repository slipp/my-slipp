package net.slipp.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DeleteHistory {
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ContentType contentType;
	
	private Long contentId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_deletehistory_to_user"))
	private User deletedBy;
	
	private LocalDateTime createDate = LocalDateTime.now();

	public DeleteHistory(ContentType contentType, Long contentId, User deletedBy, LocalDateTime createDate) {
		this.contentType = contentType;
		this.contentId = contentId;
		this.deletedBy = deletedBy;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
				+ deletedBy + ", createDate=" + createDate + "]";
	}
}
