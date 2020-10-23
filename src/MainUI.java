import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MainUI extends JFrame {
    public JPanel master = new JPanel(new GridBagLayout());
    public JPanel clock = new JPanel(new GridLayout(10,11));
    public JPanel north = new JPanel();
    public JPanel south = new JPanel();
    public JPanel east = new JPanel(new BorderLayout());
    public JPanel west = new JPanel(new BorderLayout());
    public JPanel obenLinks = new JPanel();
    public JPanel obenRechts = new JPanel();
    public JPanel untenRechts = new JPanel();
    public JPanel untenLinks = new JPanel();
    public MyButton btExit = new MyButton("Exit", "btExit");
    public MyButton btAlarm = new MyButton("Alarm", "btAlarm");
    public ArrayList<GridSubPanel> subPanelList = new ArrayList<>();
    public Steuerung steuerung;


    public MainUI(Steuerung pSteuerung){
        super();
        steuerung = pSteuerung;
        setUndecorated(true);
        setResizable(true);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        int xPos = (d.width + this.getSize().width) / 2;
        int xPos = (d.width/2 - 290);
//        int yPos = (d.height - this.getSize().height) / 2;
        int yPos = (d.height /2)-250 ;
            setLocation(xPos, yPos);
            setVisible(true);
            createGrid();
            setContent();
            pack();
        }

    public void createGrid() {
        this.setContentPane(master);
        master.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();

        //North:
        north.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        master.add(north, gbc2);

        //Clock:
        clock.setBackground(Color.BLACK);
        clock.setPreferredSize(new Dimension(400, 400));
        gbc.gridx = 1;
        gbc.gridy = 1;
        master.add(clock, gbc);

        //South:
        south.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        master.add(south, gbc2);

        //East:
        east.setPreferredSize(new Dimension(90, 400));
        gbc2.fill = GridBagConstraints.VERTICAL;
        gbc2.gridx = 2;
        gbc2.gridy = 1;
        master.add(east, gbc2);

        //West:
        west.setPreferredSize(new Dimension(90, 400));
        gbc2.fill = GridBagConstraints.VERTICAL;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        master.add(west, gbc2);

        //Minute1:
        obenLinks.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        master.add(obenLinks, gbc2);

        //Minute2:
        obenRechts.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 2;
        gbc2.gridy = 0;
        master.add(obenRechts, gbc2);

        //Minute3:
        untenRechts.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 2;
        gbc2.gridy = 2;
        master.add(untenRechts, gbc2);

        //Minute4:
        untenLinks.setBackground(Color.BLACK);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        master.add(untenLinks, gbc2);
    }


    public void setContent(){
        //Buchstaben importieren:
         try {
             BufferedReader in = new BufferedReader(new FileReader("res/Data.txt"));
             String line = "";
             while ((line = in.readLine()) != null) {
                 String[] token = line.split(";");
                 ArrayList<Grid> templist = new ArrayList<>();
                 for (int i = 0; i < token.length; i++) {
                     Grid temp = new Grid(token[i]);
                     templist.add(temp);
                 }

                 GridSubPanel tempSubpanel = new GridSubPanel(templist);
                 subPanelList.add(tempSubpanel);
                 token = line.split(";");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         //Namen der Subpanels importieren:
          try {
              BufferedReader in = new BufferedReader(new FileReader("res/Data2.txt"));
              String line = "";
              int i = 0;
              while ((line = in.readLine()) != null) {
                  subPanelList.get(i).setName(line);
                  i++;
              }
          }catch(Exception e){
                  e.printStackTrace();
              }
          //Minutenflag setzen:
        for (int i = 0;i<4;i++ ) {
            subPanelList.get(i).getGrids().get(0).setIsMinute(true);
        }

             //Minutenfelder zum Panel hinzufügen:
             obenLinks.add(subPanelList.get(0).getGrids().get(0));
             obenRechts.add(subPanelList.get(1).getGrids().get(0));
             untenRechts.add(subPanelList.get(2).getGrids().get(0));
             untenLinks.add(subPanelList.get(3).getGrids().get(0));

             //Buchstabenfelder zum Panel hinzufügen:
             for (int i =4; i< subPanelList.size();i++){
                 for (int j =0; j < subPanelList.get(i).getGrids().size(); j++){
                     clock.add(subPanelList.get(i).getGrids().get(j));
                  }
             }


        btAlarm.addActionListener(steuerung.getListener());
        west.add(btAlarm);

        east.add(btExit);
        btExit.addActionListener(steuerung.getListener());

        repaint();
    }

    public void resetAll(){
        for (int i = 0; i<subPanelList.size();i++){
            subPanelList.get(i).reset();
        }
    }
}
