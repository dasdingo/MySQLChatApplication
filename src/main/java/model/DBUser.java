package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.SessionUtil;

@Entity
@Table(name = "users")
public class DBUser {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int _id;

	@Column(name = "username", unique = true)
	private String _userName;

	public DBUser() {

	}

	public DBUser(String userName) {
		this._userName = userName;

	}

	public String getUserName() {
		return _userName;
	}

	public void setName(String userName) {
		_userName = userName;
	}

	public int getId() {
		System.out.println(_id);
		return _id;

	}

	public void setId(int id) {
		this._id = id;
	}

	// Override standard toString method to give a useful result
	public String toString() {
		return _userName;
	}
}
