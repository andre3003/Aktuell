import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Alarm {

    Clip clip;
    boolean clipIsRunning = false;
    int localTimeStunde;
    int localTimeMinute;
    int alarmTimeStunde = 0;
    int alarmTimeMinute = 0;
    boolean isActive = false;
    Timer alarmTimer = new Timer(1000, null);
    ArrayList<String> clipList;
    String aktClip;
    int numberOfTracks = 0;
    Random r = new Random();
    Steuerung steuerung;

    public Alarm(Steuerung pSteuerung) {
        steuerung = pSteuerung;
            alarmTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmTimer_action(e);

            }
        });
        alarmTimer.setInitialDelay(0);
        alarmTimer.setDelay(1000);
        alarmTimer.setRepeats(true);
        alarmTimer.start();
        createClipList();
    }

    public void createClipList(){
        clipList = new ArrayList();
        try{
            FileReader reader = new FileReader("res/Clips/Titelliste.txt");
            BufferedReader in = new BufferedReader(reader);
            String temp = in.readLine();

            while(temp != null){
                clipList.add(temp);
                numberOfTracks++;
                temp = in.readLine();
            }
        } catch (Exception e){
            System.out.println("Lesefehler");
        }
        aktClip = clipList.get(r.nextInt(numberOfTracks)-1);

    }

    private void alarmTimer_action(ActionEvent e) {
        localTimeStunde = LocalTime.now().getHour();
        localTimeMinute = LocalTime.now().getMinute();
        checkAlarm();
    }

    public void playAlarm() throws Exception {
        clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/Clips/"+ aktClip));
        clip.open(ais);
        clip.start();
        clipIsRunning = true;
    }

    public void stopAlarm(){
            alarmTimer.stop();
        if (clip != null){
            clip.stop();
            clip.close();
            clipIsRunning = false;
            alarmTimer.stop();
        }
    }

    public void setAlarmTime(int pStunde, int pMinute){
        alarmTimeStunde = pStunde;
        alarmTimeMinute = pMinute;
        isActive = true;
        alarmTimer.start();
    }

    public void checkAlarm(){
      if (isActive){
          if (localTimeStunde == alarmTimeStunde && localTimeMinute == alarmTimeMinute && !clipIsRunning){
              try {
                    playAlarm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
