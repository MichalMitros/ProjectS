package mastergui;

import javax.swing.*;
import java.awt.event.*;

public class DdosGui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField urltextField2;
    private JTextField numofthreadstextField1;
    private JLabel numberok;

    private String url;
    private String numOfThreads;

    public DdosGui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        numberok.setText("");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        url = urltextField2.getText();
        numOfThreads = numofthreadstextField1.getText();
        try {
            if (Integer.parseInt(numOfThreads) > 0) {
                dispose();
            } else {
                numberok.setText("Must be bigger than 0");
            }

        } catch (NumberFormatException e) {
            numberok.setText("Must be a number!");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getNumOfThreads() {
        return numOfThreads;
    }

    private void onCancel() {
        url = " ";
        numOfThreads = " ";
        dispose();
    }

    public static void main(String[] args) {
        DdosGui dialog = new DdosGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
