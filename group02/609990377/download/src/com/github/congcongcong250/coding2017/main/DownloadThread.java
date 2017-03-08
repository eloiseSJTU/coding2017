package com.github.congcongcong250.coding2017.main;

import com.github.congcongcong250.coding2017.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		
	}
}
