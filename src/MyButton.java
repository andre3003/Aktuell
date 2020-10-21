import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class MyButton extends JButton {

    public MyButton(String pText){
        super();
        setFont(new Font("Serif", BOLD, (int) 23));
        setText(pText);
        setBackground(Color.black);
        setBorder(null);
        setBorderPainted(false);
        setModel(new FixedStateButtonModel());

    }

    public class FixedStateButtonModel extends DefaultButtonModel    { //Verhindert, dass der Klick den Hintergrund des Buttons neu zeichnet Quelle https://stackoverflow.com/questions/22543633/stopping-jbutton-highlighting-on-press

        @Override
        public boolean isPressed() {
            return false;
        }

        @Override
        public boolean isRollover() {
            return false;
        }

        @Override
        public void setRollover(boolean b) {
            //NOOP
        }

    }


}
