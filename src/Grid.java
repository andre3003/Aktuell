import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    int index;
    String text;
    boolean show = false;
    boolean isMinute = false;
    boolean alarmTime = false;

    public Grid(int pIndex, String pText){
        super();
        index = pIndex;
        text = pText;
    }

    public Grid(String pText){
        super();
        text = pText;
        setPreferredSize(new Dimension(30, 30));
        setBackground(Color.BLACK);
        setBorder(null);
    }

    public void setIsMinute(boolean minute) {
        isMinute = minute;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setAlarmTime(boolean alarmTime) {
        this.alarmTime = alarmTime;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        //g.setColor(Color.white);
        //g.drawString(Integer.toString(index), 10,10);
//        Font f = new Font("Serif", Font.BOLD, (int) (gui.getFrame().getWidth()/25));
        Font f = new Font("Serif", Font.BOLD, (int) 26); // (800/25));
        g.setFont(f);

        if (!this.text.equals("PM") && !this.text.equals("AM")) {
            if (!show) {
                g.setColor(Color.darkGray);
                g.drawString(text, (int) (this.getWidth() / 3.5), (int) (getHeight() / 1.5));
            } else {
                g.setColor(Color.WHITE);
                g.drawString(text, (int) (this.getWidth() / 3.5), (int) (getHeight() / 1.5));
            }
        }

        if (isMinute){
            if (!show){
                g.setColor(Color.darkGray);
                g.fillOval((this.getWidth()/2)-5,(getHeight()/2)-5, 10,10);
            } else{
                g.setColor(Color.white);
                g.fillOval((this.getWidth()/2)-5,(getHeight()/2)-5, 10,10);
            }
        }
    }
}
