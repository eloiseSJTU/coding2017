package com.github.congcongcong250.coding2017.main;

import com.github.congcongcong250.coding2017.api.Connection;
import com.github.congcongcong250.coding2017.api.ConnectionException;
import com.github.congcongcong250.coding2017.api.ConnectionManager;
import com.github.congcongcong250.coding2017.api.DownloadListener;

public class FileDownloader {

	String url;

	DownloadListener listener;

	ConnectionManager cm;
	int nb_thd = 4;
	
	public FileDownloader(String _url) {
		this.url = _url;

	}

	public void execute() {
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos,
		// endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		// 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接，
		// 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		Connection conn = null;
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();
			
			if(length < nb_thd){
				nb_thd = 1;
			}
			int remainder = length%nb_thd;
			int partialLen = (length + remainder)/nb_thd;

			final DownloadThread thds[] = new DownloadThread[nb_thd];
			final Connection conns[] = new Connection[nb_thd];
			int startPos = 0 ;
			int endPos = partialLen - 1;
			
			
			for(int i = 0 ; i < nb_thd  ; i++){
				conns[i] = cm.open(url);
				thds[i] = new DownloadThread(conn, startPos, endPos);
				thds[i].start();
				
				startPos +=  partialLen;
				endPos += partialLen;
				if( nb_thd == i+2){
					endPos -= remainder;
				}
			}
			
			new Thread(new Runnable()
	        {
	            public void run() {
	            	try{
		            	for(int i = 0 ; i < nb_thd ; i++){
		    				thds[i].join();
		    			}
		    			if( listener != null){
		    				listener.notifyFinished();
		    			}
	            	}catch ( InterruptedException e){
	        			e.printStackTrace();
	        		}finally{
	        			for(int i = 0 ; i < nb_thd ; i++){
		    				conns[i].close();
		    			}
	        		}
	            }
	        }).start();
			
		} catch (ConnectionException  e) {
			e.printStackTrace();
		} finally {
			
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

}
