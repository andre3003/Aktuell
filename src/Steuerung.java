import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;

public class Steuerung implements ActionListener{
 MainGUI mainGUI;
 Timer timer = new Timer(1000, null);
 int stunde, minute = 0;
 String stunde_;
 String minute_;
 String aM_pM;
 Alarm alarm = null;

    public Steuerung(){
        mainGUI = new MainGUI(this);
        timer.setInitialDelay(0);
        timer.setDelay(1000);
        timer.setRepeats(true);
        timer.start();
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer_action(e);
            }

        });
     }

    public void timer_action(ActionEvent evt){
        stunde = LocalTime.now().getHour();
        minute = LocalTime.now().getMinute();

        if (minute >= 25){
            stunde = stunde+1;
        }

        if (stunde > 12) stunde = stunde -12;

        //Das ist ein Test

        stunde_ = Integer.toString(stunde);
        minute_ = Integer.toString(minute);

       if (stunde == 0){
            stunde_ = "12";
        }

        uhrStellen();
    }

    public void uhrStellen() {
        mainGUI.resetAll();
        int zaehlerMinutenAnzeige = minute;

        mainGUI.panelList.get(4).setShow(); //ES
        mainGUI.panelList.get(6).setShow(); //IST


        for (int i = 0; i < mainGUI.panelList.size(); i++) {
            if (mainGUI.panelList.get(i).getName().equals(stunde_)) {
                mainGUI.panelList.get(i).setShow();
            }
        }

        if (minute >= 5 && minute < 10) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("fuenfM") || mainGUI.panelList.get(i).getName().equals("nach")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 5;
        }

        if (minute >= 10 && minute < 15) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("zehnM") || mainGUI.panelList.get(i).getName().equals("nach")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 10;
        }

        if (minute >= 15 && minute < 20) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("viertel") || mainGUI.panelList.get(i).getName().equals("nach")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 15;
        }

        if (minute >= 20 && minute < 25) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("zwanzig") || mainGUI.panelList.get(i).getName().equals("nach")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 20;
        }

        if (minute >= 25 && minute < 30) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("fuenfM") || mainGUI.panelList.get(i).getName().equals("vor") || mainGUI.panelList.get(i).getName().equals("halb")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 25;
        }

        if (minute >= 30 && minute < 35) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("halb")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 30;
        }


        if (minute >= 35 && minute < 40) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("fuenfM") || mainGUI.panelList.get(i).getName().equals("nach") || mainGUI.panelList.get(i).getName().equals("halb")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 35;
        }


        if (minute >= 40 && minute < 45) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("zwanzig") || mainGUI.panelList.get(i).getName().equals("vor")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 40;
        }

        if (minute >= 45 && minute < 50) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("viertel") || mainGUI.panelList.get(i).getName().equals("vor")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 45;
        }

        if (minute >= 50 && minute < 55) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("zehnM") || mainGUI.panelList.get(i).getName().equals("vor")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 50;
        }

        if (minute >= 55 && minute < 60) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("fuenfM") || mainGUI.panelList.get(i).getName().equals("vor")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
            zaehlerMinutenAnzeige = minute - 55;
        }

        if (zaehlerMinutenAnzeige > 0) {
            for (int i = 1; i <= zaehlerMinutenAnzeige; i++) {
                mainGUI.panelList.get(i - 1).setShow();
            }
        }

        if (minute >= 0 && minute < 5) {
            for (int i = 0; i < mainGUI.panelList.size(); i++) {
                if (mainGUI.panelList.get(i).getName().equals("uhr")) {
                    mainGUI.panelList.get(i).setShow();
                }
            }
        }
        mainGUI.revalidate();
        mainGUI.repaint();
    }

    public ActionListener getListener(){
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource();
        switch (temp.getName()){
            case "btExit":
                System.exit(0);
            case "btAlarm":
                btAlarm_action(e);
        }
    }

    private void btAlarm_action(ActionEvent e) {
        SetAlarm d = new SetAlarm();
        if (d.ok && alarm == null){
            alarm = new Alarm(this);
            alarm.setAlarmTime(d.getStunde(), d.getMinute());
            mainGUI.btAlarm.setForeground(Color.WHITE);
            mainGUI.btAlarm.setText(d.stunde + ":" + d.minute);

        } else if (!d.ok && alarm != null){
            alarm.stopAlarm();
            alarm = null;
            mainGUI.btAlarm.setForeground(Color.DARK_GRAY);
            mainGUI.btAlarm.setText("Alarm");

        }
    }


    public static void main(String[] args) {
        new Steuerung();
    }


}
