package com.sedin.RoCreation;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.IDfLoginInfo;

public class SessionDFC {
	public IDfSession getSession(String repo, String user, String pass) {
		IDfSession mySession = null;
		try {
			IDfClientX clientx = new DfClientX();
			IDfClient client = clientx.getLocalClient();
			IDfSessionManager sMgr = client.newSessionManager();
			IDfLoginInfo loginInfoObj = clientx.getLoginInfo();
			loginInfoObj.setUser(user);
			loginInfoObj.setPassword(pass);
			loginInfoObj.setDomain(null);
			sMgr.setIdentity(repo, loginInfoObj);
			mySession = sMgr.getSession(repo);
			System.err.println("Session created successfully..!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Get Session Code Executed..!");
		}
		return mySession;
	}

	public void releaseSession(IDfSession session)  {
		if (session != null) {
			IDfSessionManager sMgr = session.getSessionManager();
			sMgr.release(session);
			System.err.println("Session Released..!");
		} else {
			System.err.println("Session is unavailable..!");
		}
	}
}
