package org.jlab.wedm.persistence.model.html;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ryans
 */
public class TextScreenObject extends HtmlScreenObject {

    private static final Logger LOGGER = Logger.getLogger(TextScreenObject.class.getName());

    public String value = null;
    public String align;
    public int numLines = 1;

    @Override
    public String toHtml(String indent, String indentStep, Point translation) {

        //String className = this.getClass().getSimpleName();        
        /*if (threeDimensional && (className.equals("ActiveButton") || className.equals(
                "ActiveMessageButton"))) {*/

 /*} else if (!threeDimensional && className.equals("ActiveMessageButton")) {
            styles.put("border", "1px solid black");
        } else if (className.equals("ActiveXTextDsp")) {
            //"border: 1px solid black;";
        }         */
 /*if (topShadowColor != null) {       
                styles.put("border-top", "2px solid " + topShadowColor.toRgbString());
                styles.put("border-left", "2px solid " + topShadowColor.toRgbString());
            }

            if (botShadowColor != null) {
                styles.put("border-bottom", "2px solid " + botShadowColor.toRgbString());
                styles.put("border-right", "2px solid " + botShadowColor.toRgbString());
            }      */
        Map<String, String> threeDStyles = new HashMap<>();
        Map<String, String> textStyles = new HashMap<>();

        /*float lineHeight = h / numLines;
        if (border && lineWidth != null) {
            lineHeight = lineHeight - (lineWidth * 2);
        }
        textStyles.put("line-height", lineHeight + "px");*/
        
        if (align != null) {
            textStyles.put("text-align", align);
        }

        if (border) {
            LOGGER.log(Level.INFO, "border being using in html text object!");

            float px = 1;

            if (lineWidth != null) {
                px = lineWidth;
            }

            String style = "solid";

            String colorStr = "black";

            if (fgColor != null) {
                colorStr = fgColor.toRgbString();
            }

            textStyles.put("border", px + "px " + style + " " + colorStr);
        }

        String className = this.getClass().getSimpleName();         
        if (!("ActiveButton".equals(className) || "ActiveMessageButton".equals(className) || "ActiveXTextDsp".equals(className)) || threeDimensional) {
            if (topShadowColor != null) {
                styles.put("border-top", "1px solid " + botShadowColor.toRgbString());
                styles.put("border-left", "1px solid " + botShadowColor.toRgbString());

                threeDStyles.put("border-top", "2px solid " + topShadowColor.toRgbString());
                threeDStyles.put("border-left", "2px solid " + topShadowColor.toRgbString());
            }

            if (botShadowColor != null) {
                styles.put("border-bottom", "1px solid " + topShadowColor.toRgbString());
                styles.put("border-right", "1px solid " + topShadowColor.toRgbString());

                threeDStyles.put("border-bottom", "2px solid " + botShadowColor.toRgbString());
                threeDStyles.put("border-right", "2px solid " + botShadowColor.toRgbString());
            }
        }

        String html = startHtml(indent, indentStep, translation);

        String threeDStyleStr = getStyleString(threeDStyles);
        String textStyleStr = getStyleString(textStyles);
        String indentPlusOne = indent + indentStep;
        String indentPlusTwo = indentPlusOne + indentStep;
        
        String val = org.apache.taglibs.standard.functions.Functions.escapeXml(value);
        
        if(val == null || "".equals(val)) {
            val = " ";
        }
        
        html = html + indentPlusOne + "<div class=\"border-3d\" " + threeDStyleStr + ">\n";
        html = html + indentPlusTwo + "<div class=\"screen-text\" " + textStyleStr + ">";
        html = html + val  + "</div>\n";
        html = html + indentPlusOne + "</div>\n";
        html = html + endHtml(indent, indentStep);

        return html;
    }
}
