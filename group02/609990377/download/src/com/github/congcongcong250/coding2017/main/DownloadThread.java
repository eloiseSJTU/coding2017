package com.github.congcongcong250.coding2017.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
		int len = endPos - startPos + 1;
		byte res[] = new byte[len];
		try {
			res = conn.read(startPos, endPos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "./res.jpeg";
		RandomAccessFile f;
		
		try {
			f = new RandomAccessFile(new File(path), "rw");
			f.seek(startPos);
			f.write(res);
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
}
