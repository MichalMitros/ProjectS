package client;

import botlogic.Bot;

/**
 * Created by kubehe on 30-04-2017.
 */
public class TestInOutAction implements Action {

    Bot bot;

	@Override
	public void constructor(Bot bot)
	{
		this.bot = bot;
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
		while ((line = bot.receiveMessage()) != null)
		{
			if (line.contains("ping"))
			{
				bot.sendMessage(" pong od " + bot.getLogin() + "\r\n");

				System.out.println(line);
			}
			else {
				System.out.println(line);
			}
		}
	}



}
