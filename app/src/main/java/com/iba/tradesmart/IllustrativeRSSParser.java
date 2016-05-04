package com.iba.tradesmart;

/**
 * Created by firdous on 03/05/16.
 */
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Illustrative, as in: nowhere near complete or ready for production use ;)
 */
public class IllustrativeRSSParser {

    private SAXParserFactory factory;

    public IllustrativeRSSParser(){
        factory = SAXParserFactory.newInstance();
    }

    public IllustrativeRSS parse(InputStream in) throws Exception {
        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        RSSHandler rss = new RSSHandler();
        reader.setContentHandler(rss);

        reader.parse(new InputSource(in));
        return rss.result;
    }

    class RSSHandler extends DefaultHandler {
        IllustrativeRSS result = new IllustrativeRSS();

        private String el;
        private String url;
        private StringBuilder title = new StringBuilder();
        private StringBuilder link = new StringBuilder();
        private RSSFeedItem feedItem;

        @Override
        public void startElement(
                String uri, String localName,
                String qName, Attributes attributes
        ) throws SAXException {
            if ("item".equals(qName)) {
                title.setLength(0);
                link.setLength(0);
                url = null;
            } else if ("enclosure".equals(qName)) {
                url = attributes.getValue("link");
            }
            el = localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if ("title".equals(el))
                title.append(ch,start,length);
            else if("link".equals(el))
                link.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("item".equals(localName) /*&& url != null*/) {
                result.add(link.toString(), title.toString());
            }
        }
    }

}