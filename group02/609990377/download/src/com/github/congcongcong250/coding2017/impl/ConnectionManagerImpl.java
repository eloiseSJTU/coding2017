package com.github.congcongcong250.coding2017.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.github.congcongcong250.coding2017.api.Connection;
import com.github.congcongcong250.coding2017.api.ConnectionException;
import com.github.congcongcong250.coding2017.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		URL urlobj;
		Connection con = null;
		
		try {
			urlobj = new URL(url);
			con = new ConnectionImpl(urlobj.openConnection());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return con;
	}

}
