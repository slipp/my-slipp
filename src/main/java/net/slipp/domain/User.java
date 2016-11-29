package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends AbstractEntity {
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	
	@JsonIgnore
	private String password;

	private String name;

	private String email;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		
		return newPassword.equals(password);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}
	
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		
		return newId.equals(getId());
	}
	
	@Override
	public String toString() {
		return "User [" + super.toString() + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}
}
