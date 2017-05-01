package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Created by kubehe on 30-04-2017.
 */
public class TestInOutAction implements Action {
	BufferedReader reader;
	BufferedWriter writer;

	String channel;
	String login;

	@Override
	public void constructor(BufferedReader reader, BufferedWriter writer, String channel, String login)
	{
		this.reader = reader;
		this.writer = writer;
		this.channel = channel;
		this.login = login;
	}

	@Override
	public void executeAction()
	{
		try
		{
			testInOut();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void testInOut() throws Exception
	{
		String line;
		while ((line = this.reader.readLine()) != null)
		{
			if (line.contains("ping"))
			{
				this.writer.write("PONG" + "\r\n");
				this.writer.write("PRIVMSG " + channel + " pong od " + this.login + "\r\n");
				this.writer.flush();
				System.out.println(line);
			}
			else {
				System.out.println(line);
			}
		}
	}



}
