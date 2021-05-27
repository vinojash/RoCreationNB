package com.testCases;

import com.documentum.fc.client.IDfSession;
import com.sedin.RoCreation.SessionDFC;

public class SessionTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SessionDFC sessionDFC = new SessionDFC();
		IDfSession mySession = sessionDFC.getSession("NABARDUAT", "dmadmin", "P@ssword01");
		sessionDFC.releaseSession(mySession);
	}
}
