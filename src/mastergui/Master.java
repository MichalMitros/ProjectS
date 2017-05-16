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

    public String receiveMessageForMastergui(boolean cleanMsg) throws Exception
    {
        String line;
        if (super.getReader().ready()) {
            line = super.getReader().readLine();
            if (cleanMsg) {
                int argBeginning = line.lastIndexOf(super.getChannel() + " :");
                int nameEnding = line.indexOf('!');
                String name = line.substring(1,nameEnding);
                String message = line.substring((argBeginning + super.getChannel().length() + 2) ,line.length());
                StringBuilder cleanline = new StringBuilder();
                cleanline.append(name + ": " + message);
                return cleanline.toString();
            }
            else {
                return line;
            }
        }
        else {
            return null;
        }
    }
}
