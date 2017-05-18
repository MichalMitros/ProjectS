package mastergui;

import javax.swing.*;
import java.awt.event.*;

public class DownloadFileFromUrlGui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField filepathtextField1;
    private JTextField urltextField2;

    private String url;
    private String filepath;

    public String getUrl() {

        return url;
    }

    public String getFilepath() {
        return filepath;
    }

    public DownloadFileFromUrlGui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        filepath = filepathtextField1.getText();
        dispose();
    }

    private void onCancel() {
        url = " ";
        filepath = " ";
        dispose();
    }


    public static void main(String[] args) {
        DownloadFileFromUrlGui dialog = new DownloadFileFromUrlGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
