package model;

import java.util.List;

import util.SessionUtil;

public class ConversationDao implements DaoInterface<Conversation, Integer>{

	public void persist(Conversation entity) {
		SessionUtil.getSession().persist(entity);
	}

	public void update(Conversation entity) {
		SessionUtil.getSession().update(entity);
	}

	public Conversation findById(Integer id) {
		Conversation conversation = (Conversation) SessionUtil.getSession().get(Conversation.class, id);
		return conversation;
	}

	public void delete(Conversation entity) {
		SessionUtil.getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Conversation> findAll() {
		List<Conversation> conversationList = (List<Conversation>) SessionUtil.openSession().createQuery("from Conversation").list();
		return conversationList;
	}

	public void deleteAll() {
		List<Conversation> entityList = findAll();
		for (Conversation entity : entityList) {
			delete(entity);
		}

	}


}
