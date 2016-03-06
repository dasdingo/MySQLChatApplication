package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.SessionUtil;
@Entity
@Table(name = "conversation")
public class Conversation {

	@Id @GeneratedValue
	@Column(name = "c_id")
	private int _id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_one")
	public DBUser _userOne;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_two")
	public DBUser _userTwo;
	
	@Column(name = "time")
	public double time;
	
	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public DBUser get_userOne() {
		return _userOne;
	}


	public void set_userOne(DBUser _userOne) {
		this._userOne = _userOne;
	}


	public DBUser get_userTwo() {
		return _userTwo;
	}

	public void set_userTwo(DBUser _userTwo) {
		this._userTwo = _userTwo;
	}


	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}

		
	
		
	
    public Conversation()
    {
    	
    }


	public Conversation(DBUser _userOne, DBUser _userTwo) {
		
		this._userOne = _userOne;
		this._userTwo = _userTwo;
		this.setTime(new Date().getTime());
	}

	
}
