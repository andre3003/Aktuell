import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GridSubPanel extends JPanel {
    Grid [] grids;
    Steuerung steuerung;
    String name;
    String text;
    public ArrayList<Grid> list = new ArrayList<Grid>();
    boolean alarm = false;

    public GridSubPanel(String pName, String pText, Steuerung pSteuerung){
        super();
        name = pName;
        text = pText;
        steuerung = pSteuerung;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        this.setBackground(Color.BLACK);
        createGrids();
        //System.out.println(pName);
    }

    public void createGrids(){
        for (int i =0; i < text.length();i++){
            list.add(new Grid(String.valueOf(text.charAt(i))));
            this.add(list.get(i));
        }
    }

    public void setShow(){
        for (int i=0; i< list.size();i++){
            list.get(i).setShow(true);
        }
    }

    public void displayAlarm(){
        for (int i = 0; i< list.size();i++){
            list.get(i).setAlarmTime(true);
        }
    }

    public String getText() {
        return text;
    }

    public void reset(){
        for (int i=0; i< list.size();i++){
            list.get(i).setShow(false);
        }
    }

    @Override
    public String getName() {
        return name;
    }

}
