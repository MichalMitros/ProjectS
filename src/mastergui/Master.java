package mastergui;

import botlogic.Bot;

/**
 * Created by zychp_w10 on 16.05.2017.
 */
public class Master extends Bot {

    public Master(String s, String n, String l, String ch)
    {
       super(s,n,l,ch);
    }

    public String receiveMessageForMastergui() throws Exception
    {
        String line;
        if (super.getReader().ready()) {
            line = super.getReader().readLine();
            return line;
        }
        else {
            return null;
        }
    }
}
