package botlogic;

import client.Action;
import client.ActionFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by kubehe on 13.04.17.
 */

public class Bot {
    private String server;
    private String nick;
    private String login;

    private String channel;

    private Socket socket;

    private BufferedReader reader;
    private BufferedWriter writer;

    private Action action;



    public Bot(String s, String n, String l, String ch)
    {
        this.server = s;
        this.nick = n;
        this.login = l;
        this.channel = ch;
    }

    public String getLogin() {
        return login;
    }

    public void connectToIRC() throws Exception
    {
        this.socket = new Socket(this.server, 6667);

        this.reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));


        // logowanie na serwer
        writer.write("NICK " + nick + "\r\n");
        writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
        writer.flush( );

        // zczytywanie linii, az zaloguje sie na serwer
        String line;
        while ((line = receiveMessage()) != null) {
            if (line.indexOf("004") >= 0) {
                // We are now logged in.
                break;
            }
            else if (line.indexOf("433") >= 0) {
                throw new Exception("Nickname is already in use.");
            }
        }

        // dolaczanie do kanalu irc
        writer.write("JOIN " + channel + "\r\n");
        writer.flush( );
    }

    public void runAction()
    {
        action.constructor(this);
        action.executeAction();
    }

    public void waitAndValidateAction() throws Exception
    {
        String line;
        ActionFactory factory = new ActionFactory();

        String command;
        int argBeginning;
        String[] commandWithArgs;


        while((line = receiveMessage()) != null)
        {
            //System.out.println(line);

            argBeginning = line.lastIndexOf(":");
            command = line.substring(argBeginning+1);
            commandWithArgs = command.split(" ");
            System.out.println(command);

            action = factory.getAction(commandWithArgs);
            if (action != null)
                break;
        }
    }


    public void sendMessage(String message) throws Exception
    {
        writer.write("PRIVMSG " + channel + " :" + message);
        writer.flush();
    }

    public String receiveMessage() throws Exception
    {
        String line;
        line = this.reader.readLine();
        return line;
    }

}