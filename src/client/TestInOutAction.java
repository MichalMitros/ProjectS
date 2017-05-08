package client;

import static botlogic.Bot.receiveMessage;

/**
 * Created by kubehe on 30-04-2017.
 */
public class TestInOutAction implements Action {

    private Boolean pingReceived = false;

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
	public String sendInfo()
	{
		if (pingReceived)
			return " pong od ";
		else
			return " no ping received!?!?";
	}

	private void testInOut() throws Exception
	{
		String line;

		while ((line = receiveMessage()) != null  )
		{
			if (line.contains("ping"))
			{
				pingReceived = true;
				break;
			}
		}
	}



}
