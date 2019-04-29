
import javax.swing.SwingUtilities;

public class Homework {

    public static void main(String[] args) {

        //Menu.mainDialog();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI.createAndShowGUI();
            }
        });
    }
}