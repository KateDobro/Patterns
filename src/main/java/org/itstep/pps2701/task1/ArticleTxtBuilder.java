package org.itstep.pps2701.task1;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dk on 17.05.17.
 */
public class ArticleTxtBuilder implements ArticleBuilder {
    @Override
    public Article build(Object source) {

        File file=null;
        if(source instanceof String){
            file = new File(source.toString());
        }
        else if (source instanceof File)
        {
            file = (File) source;
        } else {
            throw new RuntimeException("Не поддерживаемый тип параметра");
        }

        if (!file.exists()){
            throw new RuntimeException("Файл не существует");
        }

        if (!file.canRead()){
            throw new RuntimeException("Файл недоступен для чтения");
        }

        Article article=new Article();

        try {
            StringBuilder strB=new StringBuilder();
            Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(
                    line ->
                    {
                        if(article.getHeader()==null)
                            article.setHeader(line);
                        else
                            if(article.getAuthors()==null)
                                article.setAuthors(line);
                            else
                                if(article.getHash()==null)
                                    article.setHash(line);
                                else
                                {
                                    strB.append(line);
                                }
                    });

            article.setText(strB.toString());
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка разбора файла");
        }

        String hash = calcHash(article.getText());
        if(!hash.equals(article.getHash())){
            article.setHash(hash);
            System.err.println("Хэш текста статьи не совпал"); // TODO:
        }

        return article;
    }

    private String calcHash(String source){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(source.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
