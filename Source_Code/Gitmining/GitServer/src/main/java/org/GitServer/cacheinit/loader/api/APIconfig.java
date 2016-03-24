package org.GitServer.cacheinit.loader.api;

import java.nio.charset.Charset;

public final class APIconfig {

	public static final boolean isGithub = false;
	private  static final String[] clientidAndsecret = {
			"?client_id=c0ac13f51484e1dcdad6&client_secret=73c20d744d1fce8be5ce5d2a791774f79b7f648a",
			"?client_id=664f63808c14fd653d40&client_secret=d3f2b14218fbf526beca11f608d2cc1c7027c6d3",
			"?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6"
	}; 
	
	
	private static int index = -1;
	public static synchronized String getClientidandsecret() {
		if(++index>=clientidAndsecret.length){ index = 0;}
		return clientidAndsecret[index];
	}


	public static Charset charset = Charset.forName("UTF-8");
}
