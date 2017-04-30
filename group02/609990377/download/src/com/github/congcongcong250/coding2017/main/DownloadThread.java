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
		
		try {
			byte res[]  = conn.read(startPos, endPos);
		
			String path = "./src/res.txt";
			
			RandomAccessFile f;

			if( res != null){
				f = new RandomAccessFile(new File(path), "rw");
				f.seek(startPos);
				f.write(res,0,res.length);
				f.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
}
