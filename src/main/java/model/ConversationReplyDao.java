package model;

import java.util.List;

import util.SessionUtil;

public class ConversationReplyDao implements DaoInterface<ConversationReply, Integer> {

	public void persist(ConversationReply entity) {
		SessionUtil.getSession().persist(entity);
	}

	public void update(ConversationReply entity) {
		SessionUtil.getSession().update(entity);
	}

	public ConversationReply findById(Integer id) {
		ConversationReply conversationReply = (ConversationReply) SessionUtil.getSession().get(ConversationReply.class,
				id);
		return conversationReply;
	}

	public void delete(ConversationReply entity) {
		SessionUtil.getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ConversationReply> findAll() {
		List<ConversationReply> conversationReplyList = (List<ConversationReply>) SessionUtil.openSession()
				.createQuery("from ConversationReply").list();
		return conversationReplyList;
	}

	public void deleteAll() {
		List<ConversationReply> entityList = findAll();
		for (ConversationReply entity : entityList) {
			delete(entity);
		}

	}
}
