package com.riocode.scoutpro.scraper.helper;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class ScrapeHelper {
    
    public static String getElementData(Element doc, String selector, boolean own){
        Element e = null;
        if(own){ 
            e = doc.select(selector).first();
            if(e == null) return null;
            return e.ownText().trim();
        }
        e = doc.select(selector).first();
        if(e == null) return null;
        return e.text().trim();
    }
    
    public static Element getElement(Element doc, String selector){
        return getElements(doc, selector).first();
    }
    
    public static Elements getElements(Element doc, String selector){
        return doc.select(selector);
    }
    
    public static String getAttributeValue(Element doc, String selector, String attr){
        return doc.select(selector).first().attr(attr);
    }
    
}
