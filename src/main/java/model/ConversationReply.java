package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.SessionUtil;
@Entity
@Table( name = "conversation_reply")
public class ConversationReply {

	@Id @GeneratedValue
	@Column(name = "cr_id")
	private int _id;
	
	@Column(name ="reply")
	private String _reply;
	
	@Column(name = "time")
	private double time;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id_fk")
	private DBUser _user_id_fk;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name ="c_id_fk")
	private Conversation _c_id_fk;
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_reply() {
		return _reply;
	}

	public void set_reply(String _reply) {
		this._reply = _reply;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public DBUser get_user_id_fk() {
		return _user_id_fk;
	}

	public void set_user_id_fk(DBUser _user_id_fk) {
		this._user_id_fk = _user_id_fk;
	}

	public Conversation get_c_id_fk() {
		return _c_id_fk;
	}

	public void set_c_id_fk(Conversation _c_id_fk) {
		this._c_id_fk = _c_id_fk;
	}
	
	public ConversationReply()
	{
		
	}
	
	
	public ConversationReply(String _reply, DBUser _user_id_fk, Conversation _c_id_fk) {
		this._reply = _reply;
		this._user_id_fk = _user_id_fk;
		this._c_id_fk = _c_id_fk;
		this.setTime(new Date().getTime());
	}

	
}
