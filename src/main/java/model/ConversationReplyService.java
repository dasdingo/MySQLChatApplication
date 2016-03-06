package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import util.SessionUtil;

public class ConversationReplyService {

	private static ConversationReplyDao conversationReplyDao;

	public ConversationReplyService() {
		conversationReplyDao = new ConversationReplyDao();
	}

	public void persist(ConversationReply entity) {
		SessionUtil.getSessionWithTransaction();
		conversationReplyDao.persist(entity);
		SessionUtil.closeSessionWithTransaction();
	}

	public ConversationReply findById(Integer id) {
		SessionUtil.openSession();
		ConversationReply conversationReply = conversationReplyDao.findById(id);
		SessionUtil.closeSession();
		return conversationReply;
	}
	
	public List<ConversationReply> findByConversationID(Integer id) {
		int count = 0;
		List<ConversationReply> conversationReplyList = new ArrayList<ConversationReply>();
    	Session session = SessionUtil.openSession();
        @SuppressWarnings("unchecked")
        List<ConversationReply> list = (List<ConversationReply>) session.createQuery("from ConversationReply").list();

  
        for (ConversationReply m : list) {
        	
            if(m.get_c_id_fk().get_id() == id)
            {
            	System.out.println("ID is: " +m.get_c_id_fk().get_id());
            	count++;
            	conversationReplyList.add(m);
            	
            }
        }
        
        SessionUtil.closeSession();
      
		return conversationReplyList;
	}

	public void delete(Integer id) {
		SessionUtil.getSessionWithTransaction();
		ConversationReply conversation = conversationReplyDao.findById(id);
		conversationReplyDao.delete(conversation);
		SessionUtil.closeSessionWithTransaction();
	}

	public List<ConversationReply> findAll() {
		SessionUtil.openSession();
		List<ConversationReply> conversationReply = conversationReplyDao.findAll();
		SessionUtil.closeSession();
		return conversationReply;
	}

	public void deleteAll() {
		SessionUtil.getSessionWithTransaction();
		conversationReplyDao.deleteAll();
		SessionUtil.closeSessionWithTransaction();
	}
	
	public boolean checkConversationExists(DBUser user1, DBUser user2) {
        boolean exists = false;
    	Session session = SessionUtil.openSession();
        @SuppressWarnings("unchecked")
        List<Conversation> list = (List<Conversation>) session.createQuery("from ConversationReply").list();

  
        for (Conversation m : list) {
            if((m.get_userOne().getId() == user1.getId() & m.get_userTwo().getId() == user2.getId()) | (m.get_userOne().getId() == user2.getId() & m.get_userTwo().getId() == user1.getId()))
            {
            	 exists = true;
            	 break;
            }
        }
        SessionUtil.closeSession();
        return exists;
    }

	public ConversationReplyDao ConversationReplyDao() {
		return conversationReplyDao;
	
		    }
}
