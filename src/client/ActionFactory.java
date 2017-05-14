package client;

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
        else if(commandWithArgs[0].equalsIgnoreCase("DIE"))
        {
            System.exit(0);
        }
        return null;
    }
}
