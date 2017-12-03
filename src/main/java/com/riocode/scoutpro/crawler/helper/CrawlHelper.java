package com.riocode.scoutpro.crawler.helper;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class CrawlHelper {
    
    public static String getElementData(Element doc, String selector, boolean own){
        if(own) 
            return doc.select(selector).first().ownText().trim();
        
        return doc.select(selector).first().text().trim();
    }
    
    public static Element getElement(Element doc, String selector){
        return getElements(doc, selector).first();
    }
    
    public static Elements getElements(Element doc, String selector){
        return doc.select(selector);
    }
    
}
