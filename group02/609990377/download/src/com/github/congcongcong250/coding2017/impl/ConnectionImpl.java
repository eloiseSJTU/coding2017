package com.github.congcongcong250.coding2017.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

import com.github.congcongcong250.coding2017.api.Connection;

public class ConnectionImpl implements Connection{

	private volatile URLConnection urlconn;

	public ConnectionImpl(URLConnection c){
		urlconn = c;
	}
	
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		InputStream is = urlconn.getInputStream();
		int len = endPos - startPos + 1;
		byte content[] = new byte[len];
		is.read(content, startPos, len);
		is.close();
		return content;
	}

	@Override
	public int getContentLength() {
		return urlconn.getContentLength();
	}

	@Override
	public void close() {
		urlconn = null;
		
	}

}
