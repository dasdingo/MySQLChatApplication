package model;

import java.util.List;

import org.hibernate.Session;

import util.SessionUtil;

public class DBUserDao implements DaoInterface<DBUser, Integer> {

	public void persist(DBUser entity) {
		SessionUtil.getSession().persist(entity);
	}

	public void update(DBUser entity) {
		SessionUtil.getSession().update(entity);
	}

	public DBUser findById(Integer id) {
		DBUser dBUser = (DBUser) SessionUtil.getSession().get(DBUser.class, id);
		return dBUser;
	}

	public void delete(DBUser entity) {
		SessionUtil.getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<DBUser> findAll() {
		List<DBUser> dBUsers = (List<DBUser>) SessionUtil.openSession().createQuery("from DBUser").list();
		return dBUsers;
	}

	public void deleteAll() {
		List<DBUser> entityList = findAll();
		for (DBUser entity : entityList) {
			delete(entity);
		}

	}

}
