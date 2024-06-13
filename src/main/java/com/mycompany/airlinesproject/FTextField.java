package com.mycompany.airlinesproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * The FTextField class extends JTextField to provide a custom look and feel.
 * It allows setting the fill color, line color, and stroke width for the border.
 * This class uses antialiasing for smoother rendering.
 *
 * <p>Usage example:</p>
 * <pre>{@code
 * FTextField textField = new FTextField();
 * textField.setFillColor(Color.WHITE);
 * textField.setLineColor(Color.GRAY);
 * textField.setStrokeWidth(2);
 * }</pre>
 *
 * @see JTextField
 * @see Color
 * @see BasicStroke
 * @see Graphics2D
 * @see RenderingHints
 */
public class FTextField extends JTextField {
    private Color fillColor;
    private Color lineColor;
    private int strokeWidth;

    /**
     * Default constructor that sets default values for fill color, line color, and stroke width.
     */
    public FTextField() {
        fillColor = new Color(255, 255, 255); // Default fill color
        lineColor = new Color(227, 227, 226); // Default line color
        strokeWidth = 1; // Default stroke width
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
    }

    /**
     * Constructor that allows setting a custom default line color.
     *
     * @param defaultLineColor The custom default line color.
     */
    public FTextField(Color defaultLineColor) {
        fillColor = new Color(255, 255, 255); // Default fill color
        lineColor = defaultLineColor; // Custom default line color
        strokeWidth = 1; // Default stroke width
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
    }

    /**
     * Retrieves the current fill color of the text field.
     *
     * @return The current fill color.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill color of the text field.
     *
     * @param fillColor The fill color to set.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Retrieves the current line color of the text field border.
     *
     * @return The current line color.
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets the line color of the text field border.
     *
     * @param lineColor The line color to set.
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Retrieves the current stroke width of the text field border.
     *
     * @return The current stroke width.
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Sets the stroke width of the text field border.
     *
     * @param strokeWidth The stroke width to set.
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    /**
     * Custom paint method to draw the text field with custom fill color, line color, and border.
     *
     * @param g The Graphics object used for painting.
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