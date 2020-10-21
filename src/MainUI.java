import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MainUI extends JFrame {
    public JPanel master = new JPanel(new GridBagLayout());
    public JPanel clock = new JPanel();
    public JPanel north = new JPanel();
    public JPanel south = new JPanel();
    public JPanel east = new JPanel(new BorderLayout());
    public JPanel west = new JPanel(new BorderLayout());
    public JPanel obenLinks = new JPanel();
    public JPanel obenRechts = new JPanel();
    public JPanel untenRechts = new JPanel();
    public JPanel untenLinks = new JPanel();
    public MyButton btExit = new MyButton("Exit");
    public MyButton btAlarm = new MyButton("Alarm");
    public Grid minute1 = new Grid(0,"");
    public ArrayList<GridSubPanel> panelList = new ArrayList<GridSubPanel>();


    public MainUI(){
        super();
        //this.getContentPane().add(main);
        setUndecorated(true);

        //cp = this.getContentPane();
        setLayout(null);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setSize(new Dimension(630, 500));
         Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (d.width - this.getSize().width) / 2;
        int yPos = (d.height - this.getSize().height) / 2;
            setLocation(xPos, yPos);
            setVisible(true);
            createGrid();
            //pack();
        }

     public void createGrid(){


        //master.setBackground(Color.black);
        master.setBounds(0,0, 630, 480);
         this.setContentPane(master);
        master.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        //cp.add(master);

         GridBagConstraints gbc = new GridBagConstraints();
         GridBagConstraints gbc2 = new GridBagConstraints();

         //north.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 1;
         gbc2.gridy = 0;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;
        master.setBackground(Color.BLACK);
         master.add(north, gbc2);


        // clock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        clock.setPreferredSize(new Dimension(400,400));
         gbc.gridx = 1;
         gbc.gridy = 1;
         //gbc.gridheight = 40;
         //gbc.gridwidth = 40;
         //gbc.weightx = 1;
        clock.setBackground(Color.BLACK);
         master.add(clock, gbc);

         //south.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 1;
         gbc2.gridy = 2;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;
        south.setBackground(Color.BLACK);
         master.add(south, gbc2);

        // east.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.VERTICAL;
         gbc2.gridx = 2;
         gbc2.gridy = 1;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;
        north.setBackground(Color.BLACK);
         master.add(east, gbc2);

         //west.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.VERTICAL;
         gbc2.gridx = 0;
         gbc2.gridy = 1;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;

         master.add(west, gbc2);

         //obenLinks.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 0;
         gbc2.gridy = 0;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;

         master.add(obenLinks, gbc2);

         //obenRechts.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 2;
         gbc2.gridy = 0;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;

         master.add(obenRechts, gbc2);

         //untenLinks.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 0;
         gbc2.gridy = 2;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;

         master.add(untenLinks, gbc2);

         //untenRechts.setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
         //north.setPreferredSize(Dimension());
         gbc2.fill = GridBagConstraints.HORIZONTAL;
         gbc2.gridx = 2;
         gbc2.gridy = 2;
         //gbc2.gridheight = 20;
         //gbc2.gridheight = 20;
         //gbc2.weightx = 1;

         master.add(untenRechts, gbc2);
         west.add(btAlarm); //, FlowLayout.CENTER);
         east.add(btExit); //, FlowLayout.CENTER);

         minute1.setIsMinute(true);
         obenLinks.add(minute1);


         try {
             BufferedReader in = new BufferedReader(new FileReader("res/text.txt"));
             String[] token1 = in.readLine().split(";");
             String[] token2 = in.readLine().split(";");

             for (int i =0; i < token1.length;i++){
                 panelList.add(new GridSubPanel(token1[i],token2[i]));
//                 if (i == 0){
//                     westSub.add(panelList.get(i), BorderLayout.NORTH);
//                     panelList.get(i).list.get(i).setIsMinute(true);
//                 }
//
//                 if (i == 1){
//                     eastSub.add(panelList.get(i), BorderLayout.NORTH);
//                     panelList.get(i).list.get(0).setIsMinute(true);
//                 }
//
//                 if (i == 2){
//                     eastSub.add(panelList.get(i), BorderLayout.SOUTH);
//                     panelList.get(i).list.get(0).setIsMinute(true);
//
//                 }
//
//                 if (i == 3){
//                     westSub.add(panelList.get(i), BorderLayout.SOUTH);
//                     panelList.get(i).list.get(0).setIsMinute(true);
//                 }

                 if (i >3 ){
                     clock.add(panelList.get(i));
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }





         repaint();
         revalidate();




    }

    public static void main(String[] args) {
        new MainUI();
    }

}
