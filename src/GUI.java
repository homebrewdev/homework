import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.plaf.metal.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class GUI implements ActionListener{
    // названия для кнопок
    private static String button1text = "Вывести список деталей из базы данных";
    private static String button2text = "Добавить деталь в базу данных";
    private static String button3text = "Изменить параметры детали в базе данных";
    private static String button4text = "Удалить деталь из базы данных";


    JTextArea textView = new JTextArea(300, 6);

    JScrollPane scroll = new JScrollPane(textView);

    //getContentPane().add(scroll);
    //setLocationRelativeTo ( null );

    // поле на панели для ввода пользователем id
    JTextField idField = new JTextField();

    // Specify the look and feel to use by defining the LOOKANDFEEL constant
    // Valid values are: null (use the default), "Metal", "System", "Motif",
    // and "GTK"
    final static String LOOKANDFEEL = "System";

    // If you choose the Metal L&F, you can also choose a theme.
    // Specify the theme to use by defining the THEME constant
    // Valid values are: "DefaultMetal", "Ocean",  and "Test"
    final static String THEME = "Ocean";


    public Component createComponents() {

        JButton button1 = new JButton(button1text);
        button1.setActionCommand("Button 1 was pressed!");
        JButton button2 = new JButton(button2text);
        button2.setActionCommand("Button 2 was pressed!");
        JButton button3 = new JButton(button3text);
        button3.setActionCommand("Button 3 was pressed!");
        JButton button4 = new JButton(button4text);
        button4.setActionCommand("Button 4 was pressed!");

        ActionListener actionListener = new ClickActionListener();

        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        button3.addActionListener(actionListener);
        button4.addActionListener(actionListener);

        idField.setText("Тут необходимо вписать id детали!");

        //scroll.setBounds(10, 10, 400, 300);

        textView.setEditable(false); // set textArea non-editable
        scroll = new JScrollPane(textView);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel pane = new JPanel(new GridLayout(0, 1));
        pane.add(button1);
        pane.add(button2);
        pane.add(button3);
        pane.add(button4);
        pane.add(idField);
        pane.add(scroll);

        pane.setBorder(BorderFactory.createEmptyBorder(
                10, //top
                20, //left
                10, //bottom
                20) //right
        );

        return pane;
    }

    public void actionPerformed(ActionEvent e) { }

    public class ClickActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //если нажата button1 то
            if (e.getActionCommand() == "Button 1 was pressed!") {
                ArrayList<String> viewArray = new ArrayList<String>();
                viewArray = SQLiteDB.printAllParts();

                // проходим все строки массива вытащенного из БД
                String finalView = "";
                for (int i=0; i<viewArray.size(); i++) {
                    finalView += viewArray.get(i);
                }
                // выводим все что достали из БД в textView
                textView.setText(finalView);
            }
            //если нажата button2 то
            if (e.getActionCommand() == "Button 2 was pressed!") {
                System.out.println(e.getActionCommand());
            }
            //если нажата button3 то
            if (e.getActionCommand() == "Button 3 was pressed!") {
                System.out.println(e.getActionCommand());
            }
            //если нажата button4 то
            if (e.getActionCommand() == "Button 4 was pressed!") {
                int id;
                try {
                    id = Integer.parseInt(idField.getText());
                }
                // если в поле idView что-то кроме цифр, то id=0,
                // чтобы не удалить ненароком что-нибудь в БД
                catch (NumberFormatException exp)
                {
                    id = 0;
                }

                SQLiteDB.delPartFromDB(id);
                System.out.println(e.getActionCommand());
            }
        }
    }

    private static void initGUI() {
        String lookAndFeel = null;

        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            }

            else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }

            else if (LOOKANDFEEL.equals("GTK")) {
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            }

            else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: "
                        + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(lookAndFeel);

                // If L&F = "Metal", set the theme

                if (LOOKANDFEEL.equals("Metal")) {
                    if (THEME.equals("DefaultMetal"))
                        MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    else if (THEME.equals("Ocean"))
                        MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                    //else
                    //    MetalLookAndFeel.setCurrentTheme(new TestTheme());

                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                }
            }

            catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                        + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            }

            catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                        + lookAndFeel
                        + ") on this platform.");
                System.err.println("Using the default look and feel.");
            }

            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                        + lookAndFeel
                        + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }

    public static void createAndShowGUI() {
        //Set the look and feel.
        initGUI();

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Homework");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GUI app = new GUI();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
        frame.setBounds(300, 50, 500, 600);

        //Display the window.
        frame.setVisible(true);
    }
}