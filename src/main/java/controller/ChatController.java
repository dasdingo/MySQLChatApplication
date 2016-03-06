package controller;

import java.util.ArrayList;
import java.util.List;

import model.Conversation;
import model.ConversationReply;
import model.ConversationReplyService;
import model.ConversationService;
import model.DBUser;
import model.DBUserService;

public class ChatController {

	private DBUser dBUserSender;
	private DBUser dBUserReceiver;
	private DBUserService dBUserService;
	private ConversationService conversationService;
    private ConversationReplyService conversationReplyService;
	private static int count = 0;
	public ChatController(String dBUserNameSender)
	{
		dBUserService = new DBUserService();
		conversationService = new ConversationService();
	    conversationReplyService = new ConversationReplyService();
		dBUserSender = dBUserService.findByName(dBUserNameSender);
		if(dBUserSender == null)
		{
			dBUserService.persist(new DBUser(dBUserNameSender));
			dBUserSender = dBUserService.findByName(dBUserNameSender);
		}
	}
	
	public ChatController(String dBUserNameSender, String dBUserNameReceiver)
	{
		dBUserService = new DBUserService();
		conversationService = new ConversationService();
	    conversationReplyService = new ConversationReplyService();
		dBUserSender = dBUserService.findByName(dBUserNameSender);
		if(dBUserSender == null)
		{
			dBUserService.persist(new DBUser(dBUserNameSender));
			dBUserSender = dBUserService.findByName(dBUserNameSender);
		}
		dBUserReceiver = dBUserService.findByName(dBUserNameReceiver);
		if(dBUserReceiver == null)
		{
			System.out.println("Receiving User doesn't exist");
		}
	}
	
	public void sendMessage(String dBUserNameReceiver, String message)
	{
        Conversation conversation;
        ConversationReply conversationReply;
	
		if(dBUserReceiver == null)
		{
			dBUserReceiver = dBUserService.findByName(dBUserNameReceiver);
		if(dBUserReceiver == null)
		{
			System.out.println("Receiving User doesn't exist");
		}
		{
		}
				
				if(conversationService.checkConversationExists(dBUserSender, dBUserReceiver))
				{
					conversation = conversationService.findByUsers(dBUserSender, dBUserReceiver);			
				}
				else
				{
					conversation = new Conversation(dBUserSender, dBUserReceiver);
					conversationService.persist(conversation);
				}

				conversationReply = new ConversationReply(message,dBUserSender, conversation);
				conversationReplyService.persist(conversationReply);
				
	}
	}
	
	public void sendMessage(String message)
	{
        Conversation conversation;
        ConversationReply conversationReply;
		
				if(conversationService.checkConversationExists(dBUserSender, dBUserReceiver))
				{
					conversation = conversationService.findByUsers(dBUserSender, dBUserReceiver);			
				}
				else
				{
					conversation = new Conversation(dBUserSender, dBUserReceiver);
					conversationService.persist(conversation);
				}

				conversationReply = new ConversationReply(message,dBUserSender, conversation);
				conversationReplyService.persist(conversationReply);
				
	}
	
	
	public List<ConversationReply> getAllMessages(String dBUserReceiverString)
	{
		int localCount = 0;
		List<ConversationReply> conversationReplyList = new ArrayList<ConversationReply>();
		dBUserReceiver = dBUserService.findByName(dBUserReceiverString);
		if(dBUserReceiver == null)
		{
			System.out.println("Receiving User doesn't exist");
		}
		else
		{
		if(conversationService.findByUsers(dBUserSender, dBUserReceiver) != null)
		{
		conversationReplyList = conversationReplyService.findByConversationID(conversationService.findByUsers(dBUserSender, dBUserReceiver).get_id());
		if(conversationReplyList != null)
		{
		for(ConversationReply conversationRep : conversationReplyList)
		{
			localCount++;
			System.out.println(dBUserService.findById(conversationRep.get_user_id_fk().getId()).getUserName() +": " +conversationRep.get_reply());
		}
		
		}
		}
		else
		{
			Conversation conversation = new Conversation(dBUserSender, dBUserReceiver);
			conversationService.persist(conversation);
		}
		}
		count = localCount;
		return conversationReplyList;
	}
	
	public List<ConversationReply> getNewMessages(String dBUserReceiverString)
	{
		int localCount = 0;
		List<ConversationReply> conversationReplyList = new ArrayList<ConversationReply>();
		List<ConversationReply> conversationReplyListDummy = new ArrayList<ConversationReply>();
		dBUserReceiver = dBUserService.findByName(dBUserReceiverString);
		if(dBUserReceiver == null)
		{
			System.out.println("Receiving User doesn't exist");
		}
		else
		{
		if(conversationService.findByUsers(dBUserSender, dBUserReceiver) != null)
		{
		conversationReplyList = conversationReplyService.findByConversationID(conversationService.findByUsers(dBUserSender, dBUserReceiver).get_id());
		if(conversationReplyList != null)
		{
		for(ConversationReply conversationRep : conversationReplyList)
		{
			localCount++;
			System.out.println("Count: " + count + "  Localcount:" + localCount);
			if(localCount > count)
			{
				System.out.println("somethin added asdasd");
				conversationReplyListDummy.add(conversationRep);
				count = localCount;
			}
			
		}
		
		}
		}
		else
		{
			Conversation conversation = new Conversation(dBUserSender, dBUserReceiver);
			conversationService.persist(conversation);
		}
		}
		
		return conversationReplyListDummy;
	}
}
