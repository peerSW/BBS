package com.tool;

/**
 * Created by yedaran on 2018/2/1.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Settings {
    private static String settings="settings.xml";

    //获取指定单个设置
    public String getSetting(String node1, String node2){
        SAXReader reader=new SAXReader();
        try{
            //读取settings.xml
            Document document = reader.read(new File( getUrl(settings) ) );
            Element rootElement=document.getRootElement();
            Element element1=rootElement.element(node1);
            Element element2=element1.element(node2);
            return element2.getText();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取指定一级节点下所有设置
    public Map<String,String> getSettings(String node){
        SAXReader reader=new SAXReader();
        try{
            //读取settings.xml
            Document document = reader.read(new File( getUrl(settings) ) );
            Element rootElement=document.getRootElement();
            Element element=rootElement.element(node);
            //获取指定以及节点下所有节点并遍历导入Map
            List<Element> elements=element.elements();
            Map<String,String> result=new HashMap<String, String>();
            for(Element el:elements){
                result.put(el.getName(),el.getText());
            }
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //修改指定二级节点下设置
    private void changeSetting(String node1, String node2,String setting){
        SAXReader reader=new SAXReader();
        try{
            //读取settings.xml
            Document document = reader.read(new File( getUrl(settings) ) );
            Element rootElement=document.getRootElement();
            Element element1=rootElement.element(node1);
            Element element2=element1.element(node2);
            element2.setText(setting);
            writerDocument(document);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writerDocument(Document document)throws Exception{
        //输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码
        format.setEncoding("UTF-8");
        //XMLWriter 指定输出文件以及格式
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File(getUrl(settings))),"UTF-8"), format);

        //写入新文件
        writer.write(document);
        writer.flush();
        writer.close();
    }

    private String getUrl(String fileName){
        ClassLoader classLoader=getClass().getClassLoader();
        String url = classLoader.getResource(fileName).toString().replace("file:/","");
        return url;
    }
}
