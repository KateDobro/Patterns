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

    private String strFileSource = "";
    private String strFileDest = "";

    private JTextArea taSourceFile;
    private JTextArea taDestFile;

    public BuilderTab() {
        buildTabPanelBuilder();
    }

    private void buildTabPanelBuilder() {
        this.setLayout(new GridLayout(5,1));

        JButton btnFileSourceChooser = new JButton("Выбрать файл");
        JButton btnFileDestChooser = new JButton("Выбрать путь для сохранения");
        JButton btnConvertToXml = new JButton("Конвертировать в XML");

        JLabel lblSourceFile = new JLabel("Путь к файлу:");
        taSourceFile = new JTextArea();

        JLabel lblDestFile = new JLabel("Путь для сохранения файла:");
        taDestFile = new JTextArea();

        taSourceFile.setEditable(false);
        taDestFile.setEditable(false);

        btnFileSourceChooser.addActionListener(b -> getFileSourceDir());
        btnFileDestChooser.addActionListener(b -> getFileDestDir());
        btnConvertToXml.addActionListener(b -> convertToXml(strFileSource, strFileDest));

        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.add(btnFileSourceChooser, BorderLayout.NORTH);
        btnPanel.add(btnFileDestChooser, BorderLayout.CENTER);
        btnPanel.add(btnConvertToXml, BorderLayout.SOUTH);

        this.add(btnPanel);
        this.add(lblSourceFile);
        this.add(taSourceFile);
        this.add(lblDestFile);
        this.add(taDestFile);
    }

    private void getFileSourceDir(){
        JFileChooser fileChooser = new JFileChooser();

        String userDir = System.getProperty("user.home");

        fileChooser.setCurrentDirectory(new File(userDir +"/Desktop"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text files", "txt", "TXT"));

        int ret = fileChooser.showDialog(null,null);

        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            strFileSource = file.getPath();
            taSourceFile.setText(strFileSource);
        }
    }

    private void getFileDestDir() {
        JFileChooser fileChooser = new JFileChooser();

        String userDir = System.getProperty("user.home");

        fileChooser.setCurrentDirectory(new File(userDir +"/Desktop"));
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int ret = fileChooser.showDialog(null,"Выбрать папку");

        if(ret == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            strFileDest = file.getPath();
            taDestFile.setText(strFileDest);
        }
    }

    private void convertToXml(String source, String destination) {

        if(!source.isEmpty()){

            String fileName = destination + "convertedXML.xml";


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
                createOkDialog(savedFile.getPath());
            }

        } else {
            createErrorDialog();
        }

    }

    private void createOkDialog(String filePath){
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
