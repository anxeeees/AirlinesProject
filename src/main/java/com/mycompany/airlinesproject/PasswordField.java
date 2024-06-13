package com.mycompany.airlinesproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

/**
 * Customized password field with rounded border and customizable colors.
 * Author: Ester
 */
public class PasswordField extends JPasswordField {
    private Color fillColor;
    private Color lineColor;
    private int strokeWidth;

    /**
     * Default constructor that sets default colors and border properties.
     */
    public PasswordField() {
        fillColor = new Color(255, 255, 255); // Default fill color
        lineColor = new Color(227, 227, 226); // Default line color
        strokeWidth = 1; // Default stroke width
        setOpaque(false); // Make the field non-opaque
        setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10)); // Add padding to the field
    }

    /**
     * Constructor that allows setting a custom default line color.
     * @param defaultLineColor The custom default line color
     */
    public PasswordField(Color defaultLineColor) {
        this(); // Invoke the default constructor to set other default values
        lineColor = defaultLineColor; // Set the custom default line color
    }

    /**
     * Get the current fill color.
     * @return The current fill color
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Set the fill color.
     * @param fillColor The new fill color to set
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Get the current line color.
     * @return The current line color
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Set the line color.
     * @param lineColor The new line color to set
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Get the current stroke width.
     * @return The current stroke width
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Set the stroke width.
     * @param strokeWidth The new stroke width to set
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * Override the paintComponent method to customize the appearance.
     * This method paints the rounded rectangle with specified colors.
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            g2d.setColor(fillColor);
            g2d.fillRoundRect(s, s, w, h, h, h);
            g2d.setStroke(new BasicStroke(s));
            g2d.setColor(lineColor);
            g2d.drawRoundRect(s, s, w, h, h, h);
        }
        super.paintComponent(g);
    }
}