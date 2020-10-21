import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MySpinner extends JSpinner {
    SpinnerCircularListModel model;

    public MySpinner(Object [] values){
        super();
        model = new SpinnerCircularListModel(values);
        this.setModel(model);
        this.setLayout(new GridLayout(3,1));
        this.setFont(this.getFont().deriveFont(52f));
        setMyLayout();
    }

    public void setMyLayout(){
        getComponent(0).setBackground(Color.black);
        getComponent(1).setBackground(Color.black);
        ListEditor e = (ListEditor) this.getEditor();
        e.setBackground(Color.BLACK);
        JFormattedTextField tf = (JFormattedTextField) e.getComponent(0);
        tf.setBackground(Color.BLACK);
        tf.setForeground(Color.WHITE);
        tf.setHorizontalAlignment(JTextField.CENTER);
        this.setBackground(Color.BLACK);
        repaint();
    }

    class SpinnerCircularListModel extends SpinnerListModel {

        public SpinnerCircularListModel(Object[] items) {
            super(items);
            for (int i =0; i< items.length;i++){
               // System.out.println(items[i]);
            }
        }

        public Object getNextValue() {
            java.util.List list = getList();
            int index = list.indexOf(getValue());

            index = (index >= list.size() - 1) ? 0 : index + 1;
            return list.get(index);
        }

        public Object getPreviousValue() {
            List list = getList();
            int index = list.indexOf(getValue());

            index = (index <= 0) ? list.size() - 1 : index - 1;
            return list.get(index);
        }
    }
}
