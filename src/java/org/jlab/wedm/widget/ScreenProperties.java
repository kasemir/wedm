package org.jlab.wedm.widget;

import org.jlab.wedm.persistence.model.EDLColor;
import org.jlab.wedm.persistence.model.EDLFont;

/**
 *
 * @author ryans
 */
public class ScreenProperties extends ScreenObject {
    public EDLColor textColor;
    public EDLColor ctlFgColor1;
    public EDLColor ctlFgColor2;
    public EDLColor ctlBgColor1;
    public EDLColor ctlBgColor2;
    public EDLFont ctlFont;
    public EDLFont btnFont;
    public String title;
}