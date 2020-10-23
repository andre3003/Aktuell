import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GridSubPanel {
    Steuerung steuerung;
    String name;
    String text;
    public ArrayList<Grid> list = new ArrayList<Grid>();
    boolean alarm = false;

    public GridSubPanel(ArrayList pGrids){
        super();
        list = pGrids;
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

    public ArrayList<Grid> getGrids(){
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
