package controller;

import java.util.List;

import javax.swing.SwingWorker;

import model.DBUser;
import model.DBUserService;

/**
 * @author dave
 *
 */
public class SearchForUserListWorker extends SwingWorker<List<DBUser>, DBUserService>{
private List<DBUser> _dbUserList;
private DBUserService _dbUserService;
	
	
	  public SearchForUserListWorker(List<DBUser> dbUserList, DBUserService dbUserService) {
		    this._dbUserList = dbUserList;
		    this._dbUserService = dbUserService;
		    
		   
		  }
	@Override
	protected List<DBUser> doInBackground() throws Exception {
		this._dbUserList = _dbUserService.findAll();
		System.out.println(_dbUserList);
		return _dbUserList;
		
	}

}
