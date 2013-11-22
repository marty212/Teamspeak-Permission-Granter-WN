package process;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Scanner;
import org.json.*;

import de.stefan1200.jts3serverquery.JTS3ServerQuery;


/**
 * Assign those ranks.
 * @author Martin Borstad
 *
 */
public class Process {
	
	protected String md5;
	private JTS3ServerQuery query;
	protected String username;
	protected Roster r;
	
	private final static String ADMIN_LOGIN = "admin ";
	private final static String APIKEY = "";
	final String IP = "ts.sgtcanadian.com";
	final int QUERYPORT = 10011;
	
	public Process(Roster nR)
	{
		query = new JTS3ServerQuery();
		r = nR;
	}

	/**
	 * Makes a md5
	 * @param password the password they pass in
	 */
	public void md5(String password)
	{
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		}
		catch (Exception e)
		{
			md5 = "";
			System.err.println("How do I not have md5?");
			return;
		}
		md.update(password.getBytes());
		byte[] digest = md.digest();
		BigInteger b = new BigInteger(1, digest);
		md5 = b.toString(16);
	}
	
	/**
	 * Verify user status.
	 * @return are they a wn member or not?
	 */
	private boolean verifyUser()
	{
		URL url = null;
		try {
			url = new URL("http://warriornation.net/api/?api_user=WNxJango%20Fett&api_key=" + APIKEY + "&action=login&auth_user=" + username.replace(" ", "%20") + "&auth_pass=" + md5);
			String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
			JSONObject a = new JSONObject(out);
			return a.has("login_valid") ? a.getBoolean("login_valid") : false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * First verify user, then grant permission based on their rank.
	 * @return boolean did they get permissions or not?
	 */
	public String grantPermission()
	{
		if(!verifyUser())
		{
			md5 = null;
			return null;
		}
		md5 = null;
		query.connectTS3Query(IP, QUERYPORT);
		query.doCommand("login " + ADMIN_LOGIN);
		query.selectVirtualServer(2);
		try{
			Rank rank = r.getRank(username);
			if(rank == null)
			{
				rank = Rank.PRIVATE;
			}
			HashMap<String, String> m = query.doCommand("privilegekeyadd tokentype=0 tokenid1=" + rank.rankId + " tokenid2=0 tokendescription=" + username.replace(" ", "_"));
			query.closeTS3Connection();
			return m.get("response").substring(6);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			query.closeTS3Connection();
			return null;
		}
		
		
	}

}
