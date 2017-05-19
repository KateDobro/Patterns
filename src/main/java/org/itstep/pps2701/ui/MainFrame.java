package org.itstep.pps2701.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private static final String MAIN_FRAME_TITLE = "DK_Pattern_Exam";


    // конструктор главного окна
    public MainFrame() throws HeadlessException{
        super(MAIN_FRAME_TITLE);
        setLocation(250, 250);
        setSize(400, 500);
        setResizable(false);
        setJMenuBar(createMenuBar());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Builder pattern", new BuilderTab());  // задача 1
//        tabbedPane.addTab(new GrantTab()); // задача 2
//        tabbedPane.addTab(new CompositeTab()); // задача 3

        getContentPane().add(tabbedPane);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem exitItem = new JMenuItem("Выход");

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        return menuBar;
    }
}
