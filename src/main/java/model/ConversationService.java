package model;

import java.util.List;

import org.hibernate.Session;

import util.SessionUtil;

public class ConversationService {

	private static ConversationDao conversationDao;

	public ConversationService() {
		conversationDao = new ConversationDao();
	}

	public void persist(Conversation entity) {
		SessionUtil.getSessionWithTransaction();
		conversationDao.persist(entity);
		SessionUtil.closeSessionWithTransaction();
	}

	public Conversation findById(Integer id) {
		SessionUtil.openSession();
		Conversation conversation = conversationDao.findById(id);
		SessionUtil.closeSession();
		return conversation;
	}

	public Conversation findByUsers(DBUser user1, DBUser user2) {
		Conversation conversation = null;
		Session session = SessionUtil.openSession();
		@SuppressWarnings("unchecked")
		List<Conversation> list = (List<Conversation>) session.createQuery("from Conversation").list();

		for (Conversation m : list) {
			if ((m.get_userOne().getId() == user1.getId() & m.get_userTwo().getId() == user2.getId())
					| (m.get_userOne().getId() == user2.getId() & m.get_userTwo().getId() == user1.getId())) {
				conversation = m;
				break;
			}
		}
		SessionUtil.closeSession();

		return conversation;
	}

	public static Conversation getConversation(int id) {
		Conversation conversation = new Conversation();
		boolean exists = false;
		Session session = SessionUtil.getSession();
		@SuppressWarnings("unchecked")
		List<Conversation> list = (List<Conversation>) session.createQuery("from Conversation").list();

		for (Conversation m : list) {
			if (m.get_id() == id) {
				conversation = m;
				exists = true;
				break;
			}
		}
		session.close();
		return conversation;
	}

	public void delete(Integer id) {
		SessionUtil.getSessionWithTransaction();
		Conversation conversation = conversationDao.findById(id);
		conversationDao.delete(conversation);
		SessionUtil.closeSessionWithTransaction();
	}

	public List<Conversation> findAll() {
		SessionUtil.openSession();
		List<Conversation> conversation = conversationDao.findAll();
		SessionUtil.closeSession();
		return conversation;
	}

	public void deleteAll() {
		SessionUtil.getSessionWithTransaction();
		conversationDao.deleteAll();
		SessionUtil.closeSessionWithTransaction();
	}

	public boolean checkConversationExists(DBUser user1, DBUser user2) {
		boolean exists = false;
		Session session = SessionUtil.openSession();
		@SuppressWarnings("unchecked")
		List<Conversation> list = (List<Conversation>) session.createQuery("from Conversation").list();

		for (Conversation m : list) {
			if((m.get_userOne().getId() == user1.getId() & m.get_userTwo().getId() == user2.getId())
					| (m.get_userOne().getId() == user2.getId() & m.get_userTwo().getId() == user1.getId())) {
				exists = true;
				break;
			}
		}
		SessionUtil.closeSession();
		return exists;
	}

	public ConversationDao ConversationDao() {
		return conversationDao;

	}
}
