package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	public Long getId() {
		return id;
	}
	
	public String getFormattedCreateDate() {
		return getFormattedDate(createDate, "yyyy.MM.dd HH:mm:ss");
	}
	
	public String getFormattedModifiedDate() {
		return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm:ss");
	}
	
	private String getFormattedDate(LocalDateTime dateTime, String format) {
		if (dateTime == null) {
			return "";
		}
		return dateTime.format(DateTimeFormatter.ofPattern(format));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractEntity [id=" + id + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}
}
