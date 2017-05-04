package client;

/**
 * Created by kubehe on 30-04-2017.
 */
public class ActionFactory {
    public Action getAction (String actionType) throws Exception
    {
        if(actionType==null)
        {
            return null;
        }



        if(actionType.equalsIgnoreCase("TESTINOUT"))
        {
            return new TestInOutAction();
        }
        return null;
    }
}
