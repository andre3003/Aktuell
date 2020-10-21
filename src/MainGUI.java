import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static java.awt.Font.BOLD;

public class MainGUI extends JFrame {
    private JPanel main;
    private JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
    private JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
    private JPanel northSub = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));

    private JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
    private JPanel southSub = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));

    private JPanel west = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,3));
    private JPanel westSub = new JPanel(new BorderLayout());

    private JPanel east = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,3));
    private JPanel eastSub = new JPanel(new BorderLayout());

    public MyButton btExit = new MyButton("Exit");
    public MyButton btAlarm = new MyButton("Alarm");


    GridSubPanel aM;
    GridSubPanel pM;
    boolean setAlarm = false;
    Steuerung steuerung;
    public int xPos, yPos;


    public ArrayList<GridSubPanel> panelList = new ArrayList<GridSubPanel>();

    public MainGUI(Steuerung pSteuerung) {
        super();
        steuerung = pSteuerung;
        this.getContentPane().add(main);
        setUndecorated(true);
        //setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(630, 480));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        xPos = (d.width - this.getSize().width) / 2;
        yPos = (d.height - this.getSize().height) / 2;
        setLocation(xPos, yPos);
        setVisible(true);
        btAlarm.setName("btAlarm");
        btExit.setName("btExit");
        createGrid();
        displayAlarmTime(0,0);
        //pack();
    }

    public void createGrid() {

        main.setLayout(new BorderLayout());

        center.setPreferredSize(new Dimension (600, 480));
        center.setBackground(Color.BLACK);
        //center.setBorder(BorderFactory.createLineBorder(Color.white, 1));

        north.setPreferredSize(new Dimension(70,40));
        //north.setBorder(BorderFactory.createLineBorder(Color.white));
        north.setBackground(Color.BLACK);

        northSub.setPreferredSize(new Dimension(555,40));
        //northSub.setBorder(BorderFactory.createLineBorder(Color.white));
        northSub.setBackground(Color.BLACK);

        ///south.setBorder(BorderFactory.createLineBorder(Color.white));
        south.setBackground(Color.BLACK);

        southSub.setPreferredSize(new Dimension(555,50));
        //southSub.setBorder(BorderFactory.createLineBorder(Color.white));
        southSub.setBackground(Color.BLACK);

        //north.add(northSub);

        west.setPreferredSize(new Dimension(70,470));
        //west.setBorder(BorderFactory.createLineBorder(Color.white));
        west.setBackground(Color.BLACK);
        westSub.setPreferredSize(new Dimension(75,400));
        //westSub.setBorder(BorderFactory.createLineBorder(Color.white));
        west.add(westSub);

        east.setPreferredSize(new Dimension(70,470));
        eastSub.setPreferredSize(new Dimension(75,400));
        //eastSub.setBorder(BorderFactory.createLineBorder(Color.white));
        //east.setBorder(BorderFactory.createLineBorder(Color.white));
        east.setBackground(Color.BLACK);
        east.add(eastSub);


        main.add(north, BorderLayout.NORTH);
        main.add(west, BorderLayout.WEST);
        main.add(east, BorderLayout.EAST);
        main.add(south, BorderLayout.SOUTH);
        main.add(center, BorderLayout.CENTER);

        try {
            BufferedReader in = new BufferedReader(new FileReader("res/text.txt"));
            String[] token1 = in.readLine().split(";");
            String[] token2 = in.readLine().split(";");

            for (int i =0; i < token1.length;i++){
                panelList.add(new GridSubPanel(token1[i],token2[i], steuerung));
                if (i == 0){
                    westSub.add(panelList.get(i), BorderLayout.NORTH);
                    panelList.get(i).list.get(i).setIsMinute(true);
                }

                if (i == 1){
                    eastSub.add(panelList.get(i), BorderLayout.NORTH);
                    panelList.get(i).list.get(0).setIsMinute(true);
                }

                if (i == 2){
                    eastSub.add(panelList.get(i), BorderLayout.SOUTH);
                    panelList.get(i).list.get(0).setIsMinute(true);

                }

                if (i == 3){
                    westSub.add(panelList.get(i), BorderLayout.SOUTH);
                    panelList.get(i).list.get(0).setIsMinute(true);
                }

                if (i >3 ){
                    center.add(panelList.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        btAlarm.addActionListener(steuerung.getListener());
        westSub.add(btAlarm, BorderLayout.CENTER);

        btExit.addActionListener(steuerung.getListener());
        eastSub.add(btExit); //, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void displayAlarmTime(int pStunde, int pMinute){

        panelList.get(9).displayAlarm();

    }

    public void resetAll(){
        for (int i = 0; i<panelList.size();i++){
            panelList.get(i).reset();
        }
    }
}
