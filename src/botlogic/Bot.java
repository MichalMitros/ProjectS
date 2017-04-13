package botlogic;

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

    public void testInOut() throws Exception
    {
        String line = null;
        while ((line = this.reader.readLine( )) != null) {
            if (line.contains("pong")) {
                this.writer.write("PONG" + "\r\n");
                this.writer.write("PRIVMSG " + channel + " pong od " + this.login + "\r\n");
                this.writer.flush( );
                System.out.println(line);
            }
            else {
                System.out.println(line);

            }
        }
    }


}