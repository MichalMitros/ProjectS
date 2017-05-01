package botlogic;

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
    String server;
    String nick;
    String login;

    String channel;

    Socket socket;

    BufferedReader reader;
    BufferedWriter writer;



    public Bot(String s, String n, String l, String ch)
    {
        this.server = s;
        this.nick = n;
        this.login = l;
        this.channel = ch;
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
        String line = null;
        while ((line = this.reader.readLine( )) != null) {
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

    public void waitAndExecuteAction() throws Exception
    {
        String line;
        ActionFactory factory = new ActionFactory();

        while((line = this.reader.readLine()) != null)
        {
                System.out.println(line);
        }
    }

}