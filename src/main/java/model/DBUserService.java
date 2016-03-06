package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.SessionUtil;

public class DBUserService {

	private static DBUserDao dBUserDao;

	public DBUserService() {
		dBUserDao = new DBUserDao();
	}

	public void persist(DBUser entity) {
		SessionUtil.getSessionWithTransaction();
		dBUserDao.persist(entity);
		SessionUtil.closeSessionWithTransaction();
	}

	public DBUser findById(Integer id) {
		SessionUtil.openSession();
		DBUser dBUser = dBUserDao.findById(id);
		SessionUtil.closeSession();
		return dBUser;
	}
	
	public DBUser findByName(String name) {
		DBUser dBUser = null;
		Session session = SessionUtil.openSession();
		@SuppressWarnings("unchecked")
		List<DBUser> list = (List<DBUser>) session.createQuery("from DBUser").list();

		  
        for (DBUser m : list) {
            if(m.getUserName().equals(name))
            {
            	 dBUser = m;
            	 break;
            }
            
        }
        SessionUtil.closeSession();
        return dBUser;
	}

	public void delete(Integer id) {
		SessionUtil.getSessionWithTransaction();
		DBUser dBUser = dBUserDao.findById(id);
		dBUserDao.delete(dBUser);
		SessionUtil.closeSessionWithTransaction();
	}

	public List<DBUser> findAll() {
		SessionUtil.openSession();
		List<DBUser> dBUser = dBUserDao.findAll();
		SessionUtil.closeSession();
		return dBUser;
	}

	public void deleteAll() {
		SessionUtil.getSessionWithTransaction();
		dBUserDao.deleteAll();
		SessionUtil.closeSessionWithTransaction();
	}
	
	public boolean checkUserExists(String DBUser) {
        boolean exists = false;
    	Session session = SessionUtil.openSession();
        @SuppressWarnings("unchecked")
        List<DBUser> list = (List<DBUser>) session.createQuery("from DBUser").list();

  
        for (DBUser m : list) {
            if(m.getUserName().equals(DBUser))
            {
            	 exists = true;
            	 break;
            }
        }
        SessionUtil.closeSession();
        return exists;
    }

	public DBUserDao dBUserDao() {
		return dBUserDao;
	
		    }

}
