package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {

	    private static SessionUtil instance = new SessionUtil();
	    private SessionFactory factory;
	    private static Session currentSession;
	    private static Transaction currentTransaction;
	    private SessionUtil() {
	    	System.out.println("Start SessionUtil");
	    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
	    			.configure() // configures settings from hibernate.cfg.xml
	    			.build();
	    	try {
	    		factory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	    	}
	    	catch (Exception e) {
	    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
	    		// so destroy it manually.
	    		StandardServiceRegistryBuilder.destroy( registry );
	    		e.printStackTrace();
	    	}
	    		System.out.println("End SessionUtil");
	    }

	    public static Session openSession() {
	    	currentSession = getInstance().factory.openSession();
	        return currentSession;
	    }

	    public static Session getSession() {
	    	//currentSession = getInstance().factory.openSession();
	        return currentSession;
	    }
	    
	    private static SessionUtil getInstance() {
	        return instance;
	    }
	    
	    public static void closeSession() {
	        currentSession.close();
	    }
	    
	    public static Session getSessionWithTransaction()
	    {
	    	currentSession = getInstance().factory.openSession();
	    	currentTransaction = currentSession.beginTransaction();
	    	return currentSession;
	    }
	    
	    public static void closeSessionWithTransaction()
	    {
	    	if(currentTransaction != null)
	    	{
	        System.out.println("CurrentTransaction: " + currentTransaction.getStatus().toString());
	        
	    	currentTransaction.commit();
	    	currentTransaction = null;
	    	}
	    	else
	    	{
	    		System.out.println("Transaction is null");
	    	}
	    	currentSession.close();
	    }
	}

