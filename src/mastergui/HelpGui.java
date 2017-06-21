package mastergui;

import javax.swing.*;
import java.awt.event.*;

public class HelpGui extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel helpLabel;

    public HelpGui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);
        helpLabel.setText("<html>Program umożliwia różne akcje z wykorzystaniem botów podłączonych do czatu IRC.<br>" +
                "Dolna część okna, czyli linia Command wraz z przyciskiem \"Send\" i polem Log zachowują się jak zwykły klient IRC.<br>"+
                "Opcja Cleanchat powoduje wyświetlanie samej wiadomości, bez częsci technicznej.<br>"+
                "Część górna, czyli przyciski Action pomagają utworzyć polecenie, które ostatecznie należy wysłać naciskając \"Send\". <br>" +
                "Możliwe akcje:<br>"+
                "1. Atak DDoS: W celu wykonania wybierz \"DDOS Start\", wpisz adres URL, który chcesz zaatakować i podaj ilość wątków ataku. <br>" +
                "Zatwierdź \"OK\" i wyślij polecenie \"Send\". W celu zakończenia ataku wybierz \"DDOS Stop\", wyślij \"Send\".<br>" +
                "2. Pobieranie plików na komputery botów: Wybierz \"Download File from URL\", podaj adres URL pliku który chcesz pobrać.<br>" +
                "Następnie podając scieżkę należy nadać nazwę i rozszerzenie pliku \"scieżka/nazwa.roz\" np. \"./plik.jpg\" Zatwierdź \"OK\", wyślij \"Send\". <br>" +
                "3. Uruchamianie plików JAR. Wybierz \"Run Jar File\". Następnie podaj ścieżkę pliku. Zatwierdź \"OK\", wyślij \"Send\".<br> " +
                "4. Test połączenia z botami. Wybierz \"Connection test\", wyślij Send. Aktywne boty odpowiedzą \"pong\" na czacie. <br>" +
                "5. Zabicie botów. Wybierz \"Kill all bots\", wyślij \"Send\". Wszytkie boty natychmiast wyłączą się.</html>");

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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        HelpGui dialog = new HelpGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
