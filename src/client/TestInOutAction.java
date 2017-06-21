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
	public String getInfo()
	{
		if (pingReceived)
			return " pong ";
		else
			return " no ping received!?!?";
	}

	private void testInOut() throws Exception
	{
        pingReceived = true;
	}



}
