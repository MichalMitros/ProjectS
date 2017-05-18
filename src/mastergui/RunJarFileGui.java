package mastergui;

import javax.swing.*;
import java.awt.event.*;

public class RunJarFileGui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField filepathtextField1;

    private String jarfilepath;

    public String getJarfilepath() {
        return jarfilepath;
    }

    public RunJarFileGui() {
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
        jarfilepath = filepathtextField1.getText();
        dispose();
    }

    private void onCancel() {
        jarfilepath = " ";
        dispose();
    }

    public static void main(String[] args) {
        RunJarFileGui dialog = new RunJarFileGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
