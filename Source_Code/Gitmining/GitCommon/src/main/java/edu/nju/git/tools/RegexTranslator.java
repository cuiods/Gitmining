package edu.nju.git.tools;

/**
 * This class is a tool for translating a string to a standard regex representation.
 * <p>Although the return value is still a String, but it is another form.
 * @author benchaodong
 * @date 2016-03-05
 */
public class RegexTranslator {
    /**
     * the method to translate string to regex.
     * @param keyword
     * @return a regex representation of the input string <tt>keyword</tt>
     */
    public static String translate(String keyword){
        StringBuilder builder = new StringBuilder();
        keyword = keyword.trim();
        for (int i=0; i<keyword.length(); i++) {
            char c = keyword.charAt(i);
            if (c == ' '){
                builder.append("(\\S)*");
            }
            else if (c == '?') {
                builder.append("\\S");
            }
            else if (c == '*') {
                builder.append("(\\S)*");
            }
            else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
