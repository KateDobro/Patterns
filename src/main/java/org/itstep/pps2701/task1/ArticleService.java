package org.itstep.pps2701.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ArticleService {

    public Document toXml(Article article){

        Document doc = null;
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("ARTICLE");
            doc.appendChild(rootElement);

            Element header = doc.createElement("HEADER");
            header.setTextContent(article.getHeader());
            rootElement.appendChild(header);

            Element authors = doc.createElement("AUTHORS");
            authors.setTextContent(article.getAuthors());
            rootElement.appendChild(authors);

            Element hash = doc.createElement("HASH");
            hash.setTextContent(article.getHash());
            rootElement.appendChild(hash);

            Element text = doc.createElement("TEXT");
            text.setTextContent(article.getText());
            rootElement.appendChild(text);

        }catch (Exception ex){
            throw new RuntimeException("Ошибка создания XML документа");
        }

        return doc;
    }

    public File save(Object source, String path){

        File result= null;
        try{
            if(source instanceof Document){
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource src = new DOMSource((Document)source);
                result = new File(path);
                StreamResult streamResult = new StreamResult(result);

                transformer.transform(src, streamResult);
            } else {
                throw new RuntimeException("Недопустимый тип параметра");
            }

        }catch (Exception ex){
            throw new RuntimeException("Ошибка сохранения XML документа");
        }

        return result;
    }

}
