package client;

import botlogic.Bot;

/**
 * Created by kubehe on 13.04.17.
 */

public class SlaveClient {

    public static void main(String[] args) throws Exception {
        String server = "irc.kubehe.me";
        String nick = "bot";
        String login = "bot";

        String channel = "#channel";

        Bot bot1 = new Bot(server, nick, login, channel);
            bot1.connectToIRC();
            bot1.testInOut();

    }
}
