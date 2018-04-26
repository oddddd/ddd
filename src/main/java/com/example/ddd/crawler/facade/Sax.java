package com.example.ddd.crawler.facade;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Sax
 * @author wjp
 * @date Created in 上午11:58 2018/4/26
 */
public class Sax extends DefaultHandler {

    public Sax ()
    {
        super();
    }

    public void startDocument ()
    {
        System.out.println("Start document");
    }

    public void endDocument ()
    {
        System.out.println("End document");
    }

    public void startElement (String uri, String name,
                              String qName, Attributes atts)
    {
        if ("".equals (uri))
            System.out.println("Start element: " + qName);
        else
            System.out.println("Start element: {" + uri + "}" + name);
    }

    public void endElement (String uri, String name, String qName)
    {
        if ("".equals (uri))
            System.out.println("End element: " + qName);
        else
            System.out.println("End element:   {" + uri + "}" + name);
    }

    public void characters (char ch[], int start, int length)
    {
        System.out.print("Characters:    \"");
        for (int i = start; i < start + length; i++) {
            switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    break;
                case '"':
                    System.out.print("\\\"");
                    break;
                case '\n':
                    System.out.print("\\n");
                    break;
                case '\r':
                    System.out.print("\\r");
                    break;
                case '\t':
                    System.out.print("\\t");
                    break;
                default:
                    System.out.print(ch[i]);
                    break;
            }
        }
        System.out.print("\"\n");
    }

}
