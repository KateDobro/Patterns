package org.itstep.pps2701.ui;


import org.itstep.pps2701.task1.Article;
import org.itstep.pps2701.task1.ArticleBuilder;
import org.itstep.pps2701.task1.ArticleService;
import org.itstep.pps2701.task1.ArticleTxtBuilder;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/*
Задача 1. Порождающие паттерны - паттерн Строитель (Builder)
Имеется текст статьи в формате TXT. Статья состоит из:
►заголовка
►фамилий авторов
►хэш-кода текста статьи
►собственно текста статьи
Написать приложение, моделирующее конвертирование статьи в формате TXT в формат XML
(создание тегов для составных частей статьи по правилам XML).
Проверять корректность хэш-кода
*/

public class BuilderTab extends JPanel{

    private JTextArea txtAreaTxtFile;
    private JTextArea txtAreaXmlFile;

    private String strFileSource = "";

    public BuilderTab() {
        buildTabPanelBuilder();
    }

    private void buildTabPanelBuilder() {
        setLayout(new FlowLayout());

        JButton btnFileChooser = new JButton("Выбрать файл");
        JButton btnConvertToXml = new JButton("Конвертировать в XML");

        JLabel lblTxtAreaTxtFile = new JLabel("Текстовый файл", SwingConstants.CENTER);
        JLabel lblTxtAreaXmlFile = new JLabel("XML файл", SwingConstants.CENTER);

        txtAreaTxtFile = new JTextArea(25,43);
        txtAreaXmlFile = new JTextArea(25,43);

        JScrollPane scrollPaneTxt = new JScrollPane(txtAreaTxtFile);
        JScrollPane scrollPaneXml = new JScrollPane(txtAreaXmlFile);

        btnFileChooser.setPreferredSize(new Dimension(1000,25));
        btnConvertToXml.setPreferredSize(new Dimension(1000,25));

        lblTxtAreaTxtFile.setPreferredSize(new Dimension(482,25));
        lblTxtAreaXmlFile.setPreferredSize(new Dimension(482,25));

        txtAreaTxtFile.setEditable(false);
        txtAreaXmlFile.setEditable(false);
        txtAreaTxtFile.setLineWrap(true);
        txtAreaXmlFile.setLineWrap(true);
        txtAreaTxtFile.setWrapStyleWord(true);
        txtAreaXmlFile.setWrapStyleWord(true);

        scrollPaneTxt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneXml.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        btnFileChooser.addActionListener(b -> getTxtFile());
        btnConvertToXml.addActionListener(b -> convertToXml(strFileSource));

        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.add(btnFileChooser, BorderLayout.NORTH);
        btnPanel.add(btnConvertToXml, BorderLayout.SOUTH);

        this.add(btnPanel);
        this.add(lblTxtAreaTxtFile);
        this.add(lblTxtAreaXmlFile);
        this.add(scrollPaneTxt);
        this.add(scrollPaneXml);
    }


    private void getTxtFile(){
        JFileChooser fileChooser = new JFileChooser();

        String userDir = System.getProperty("user.home");

        fileChooser.setCurrentDirectory(new File(userDir +"/Desktop"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt", "TXT"));

        int ret = fileChooser.showDialog(null,null);

        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try {
                readFileTo(file, txtAreaTxtFile);
                strFileSource = file.getPath();
            }catch (Exception ex){
                System.out.println("getTxtFile error");
                ex.printStackTrace();
            }
        }
    }


    private void convertToXml(String source) {

        if(!source.isEmpty()){
            String userDir = System.getProperty("user.home");
            String fileName = userDir +"/Desktop/convertedXML.xml";

            ArticleBuilder builder = new ArticleTxtBuilder();
            ArticleService service = new ArticleService();

            Article article = null;
            article = builder.build(source);

            File savedFile = null;
            if(article != null){
                Document doc = service.toXml(article);
                savedFile = service.save(doc, fileName);
            }

            if(savedFile != null){
                showOkDialog(savedFile.getPath());
                readFileTo(savedFile, txtAreaXmlFile);
            }

        } else {
            createErrorDialog();
        }

    }

    private void readFileTo(File file, JTextArea txtArea){
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            txtArea.read( br, null );
            br.close();
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("error line 143");
        }
    }

    private void showOkDialog(String filePath){
        JDialog okDialog = new JDialog();
        okDialog.setTitle("Успех!");
        okDialog.setSize(100,150);
        okDialog.setLocationRelativeTo(this.getRootPane().getParent());
        okDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panelError = new JPanel();
        JPanel btnPanel = new JPanel();

        JLabel lblDialog = new JLabel();
        JButton btnOk = new JButton("Ok");

        lblDialog.setText("Файл:\n " + filePath + "\n успешно сохранен");

        btnOk.addActionListener( b -> okDialog.dispose());

        btnPanel.add(btnOk);

        panelError.add(lblDialog);
        panelError.add(btnPanel, "south");

        okDialog.add(panelError);
        okDialog.pack();
        okDialog.setVisible(true);

    }


    private void createErrorDialog(){
        JDialog errDialog = new JDialog();
        errDialog.setTitle("Ошибка!");
        errDialog.setSize(100,150);
        errDialog.setLocationRelativeTo(this.getRootPane().getParent());
        errDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panelError = new JPanel();
        JPanel btnPanel = new JPanel();

        JLabel lblDialog = new JLabel();
        JButton btnOk = new JButton("Ok");

        lblDialog.setText("Файл не выбран!");

        btnOk.addActionListener( b -> errDialog.dispose());

        btnPanel.add(btnOk);

        panelError.add(lblDialog);
        panelError.add(btnPanel, "south");

        errDialog.add(panelError);
        errDialog.pack();
        errDialog.setVisible(true);
    }

}
