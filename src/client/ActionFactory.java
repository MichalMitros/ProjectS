package client;

/**
 * Created by kubehe on 30-04-2017.
 */
public class ActionFactory {
    public Action getAction (String[] actionType) throws Exception // use next strings (eg. [1],[2], ...) in vector as arguments
    {
        if(actionType==null)
        {
            return null;
        }



        if(actionType[0].equalsIgnoreCase("TESTINOUT"))
        {
            return new TestInOutAction();
        }
        return null;
    }
}
