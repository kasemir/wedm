package org.jlab.wedm.persistence.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ryans
 */
public class ActiveGroup extends ScreenObject {

    public List<ScreenObject> children = new ArrayList<>();

    @Override
    public String toHtml(String indent, String indentStep, Point translation) {
        this.setCommonAttributes(); // Visibility, ID, and classes       

        int originX = x + translation.x;
        int originY = y + translation.y;

        styles.put("width", w + "px");
        styles.put("height", h + "px");
        styles.put("left", originX + "px");
        styles.put("top", originY + "px");
        
        String attrStr = this.getAttributesString(attributes);
        String classStr = this.getClassString(classes);
        String styleStr = this.getStyleString(styles);
        
        String html = indent + "<div " + attrStr + " " + classStr
                + " " + styleStr + ">\n";        
        
        Point childTranslation = new Point(-x, -y);

        if (!children.isEmpty()) {
            //html = html + indent + "<div style=\"position: relative;\">\n";
            for (ScreenObject obj : children) {
                html = html + obj.toHtml(indent + indentStep + indentStep, indentStep,
                        childTranslation);
            }
            //html = html + indent + "</div>\n";
        }

        html = html + indent + "</div>\n";

        return html;
    }
}
