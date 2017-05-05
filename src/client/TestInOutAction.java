package client;

import botlogic.Bot;

/**
 * Created by kubehe on 30-04-2017.
 */
public class TestInOutAction implements Action {

    private Bot bot;
    private Boolean pingReceived = false;

	@Override
	public void getBotObject(Bot bot)
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

	@Override
	public void sendInfo()
	{
		try
		{
			if (pingReceived == true)
				bot.sendMessage(" pong od " + bot.getLogin());
			else if (pingReceived == false)
				bot.sendMessage(" no ping received!?!?");
		}
		catch(Exception e)
		{
			e.getMessage();
		}
	}

	private void testInOut() throws Exception
	{
		String line;

		while ((line = bot.receiveMessage()) != null  )
		{
			if (line.contains("ping"))
			{
				pingReceived = true;
				break;
			}
		}
	}



}
