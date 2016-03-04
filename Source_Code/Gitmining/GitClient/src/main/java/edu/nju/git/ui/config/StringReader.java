package edu.nju.git.ui.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * {@code StringReader} is used to read a path or a string written in<br>
 * a xml file. 
 * @author cuihao
 *
 */
public class StringReader {
	/**
	 * There is at least one string we must know when initialize the reader
	 */
	private static final String DEFAULT_STRING = "src/main/resources/string/";
	private static final String PATH_TAG = "path$";
	private static final String STRING_TAG = "string$";
	private static SAXReader reader = new SAXReader();
	/**
	 * root element of path.
	 */
	private static Element pathRoot;
	/**
	 * root element of string.
	 */
	private static Element stringRoot;
	/**
	 * map for faster searching.<br>
	 * If a string has been searched before, it will be saved here.<br>
	 * Next time it can be returned directly without verifying the name one by one.
	 */
	private static Map<String, String> known = new HashMap<String, String>();
	/*
	 * initialize when class loading. 
	 */
 	private static void initialize() {
 		try {
			pathRoot = reader.read(DEFAULT_STRING+"path.xml").getRootElement();
			stringRoot = reader.read(DEFAULT_STRING+"string.xml").getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
 	/**
 	 * read a path url written in "path.xml" by name.<br>
 	 * a map {@link #known} will store the result once searched.
 	 * @param name
 	 * 			name of the path
 	 * @return path
 	 * 			string of the path
 	 */
	public static String readPath(String name){
		if (pathRoot==null) {
			initialize();
		}
		if (known.containsKey(PATH_TAG+name)) {
			return known.get(PATH_TAG+name);
		}else {
			@SuppressWarnings("unchecked")
			Iterator<Element> eIterator = pathRoot.elementIterator();
			while (eIterator.hasNext()) {
				Element element = eIterator.next();
				if (element.attributeValue("name").equals(name)) {
					String result = element.attributeValue("value");
					known.put(PATH_TAG+name, result);
					return result;
				}
			}
		}
		return "";
	}
	
	/**
	 * read a string written in "string.xml" file by name
	 * a map {@link #known} will store the result once searched.
	 * @param name
	 * 			name of the string
	 * @return string
	 * 			required string
	 */
	public static String readString(String name){
		if (stringRoot==null) {
			initialize();
		}
		if (known.containsKey(STRING_TAG+name)) {
			return known.get(STRING_TAG+name);
		} else {
			@SuppressWarnings("unchecked")
			Iterator<Element> eIterator = stringRoot.elementIterator();
			while (eIterator.hasNext()) {
				Element element = eIterator.next();
				if (element.attributeValue("name").equals(name)) {
					String result = element.attributeValue("value");
					known.put(STRING_TAG+name, result);
					return result;
				}
			}
		}
		return "";
	}
}
