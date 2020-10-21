import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SetAlarm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private MySpinner spinnerStunde;
    private MySpinner spinnerMinute;
    private JPanel plButtons;
    private JPanel plSpinner;
    public boolean ok = false;

    public int getStunde() {
        return Integer.parseInt(stunde);
    }

    public int getMinute() {
        return Integer.parseInt(minute);
    }

    public String stunde, minute; // = 0;

    public SetAlarm() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width/2) - 160;
        int y = (d.height/2) - 160;
        setLocation(x, y);
        setContentPane(contentPane);
        setModal(true);
        setUndecorated(true);
        getRootPane().setDefaultButton(buttonOK);
        contentPane.setBackground(Color.BLACK);
        plSpinner.setBackground(Color.BLACK);
        stunde = readLastAlarm()[0];
        minute = readLastAlarm()[1];
        String [] hours = {"00", "01","02", "03", "04","05","06","07","08","09","10","11","12","13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        spinnerStunde = new MySpinner(hours);
        spinnerStunde.setValue(stunde);
        String [] minutes = {"00", "05","10", "15", "20","25","30","35","40","45","50","55"};
        spinnerMinute = new MySpinner(minutes);
        spinnerMinute.setValue(minute);

        plSpinner.add(spinnerStunde);
        plSpinner.add(spinnerMinute);

        spinnerStunde.setPreferredSize(new Dimension(150, 300));
        spinnerMinute.setPreferredSize(new Dimension(150, 300));

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

        pack();
        setVisible(true);
        repaint();
        revalidate();
    }

    private void onOK() {
        stunde = (String) spinnerStunde.getValue();
        minute = (String) spinnerMinute.getValue();
        ok = true;
        writeLastAlarm();
        dispose();
    }

    private void onCancel() {
        ok = false;
        dispose();
    }

    public String [] readLastAlarm() {
        String[] token = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("res/lastAlarm.txt"));
            token = in.readLine().split(":");

            } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public void writeLastAlarm(){
        try (BufferedWriter out = new BufferedWriter(new FileWriter("res/lastAlarm.txt"))){
            out.write(stunde+ ":" + minute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SetAlarm dialog = new SetAlarm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
