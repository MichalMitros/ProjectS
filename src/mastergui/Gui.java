package mastergui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zychp_w10 on 08.05.2017.
 */
public class Gui {
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

    public Gui() {
        logtextArea1.setText("TEST");
        sendbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandField1.setText("Wciśnięto Przycisk1");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gui");
        frame.setSize(600, 400);
        frame.setLocationByPlatform(true);
        frame.setTitle("BotNetIRC - Master");
        frame.setContentPane(new Gui().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
