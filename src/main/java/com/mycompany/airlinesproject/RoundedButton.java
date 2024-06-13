package com.mycompany.airlinesproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Customized JButton with rounded corners and customizable colors for different states.
 * Author: Ester
 */
public class RoundedButton extends JButton {

    private boolean over;
    private Color fill;
    private Color line;

    private Color fillOriginal;
    private Color fillOver;
    private Color fillClick;
    private Color lineOriginal;
    private Color lineOver;

    private int strokeWidth = 2;

    /**
     * Default constructor to initialize the button with default colors and behaviors.
     */
    public RoundedButton() {
        fillOriginal = new Color(0, 0, 0);
        fillOver = new Color(72, 172, 239);
        fillClick = new Color(128, 128, 128);
        lineOriginal = new Color(255, 255, 255);
        lineOver = new Color(189, 195, 199);
        fill = fillOriginal;
        line = lineOriginal;

        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBackground(fillOriginal);
        setForeground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                fill = fillOriginal;
                line = lineOriginal;
                over = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                fill = fillOver;
                line = lineOver;
                over = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (over) {
                    fill = fillOver;
                    line = lineOver;
                } else {
                    fill = fillOriginal;
                    line = lineOriginal;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setSelected(!isSelected());
                if (isSelected()) {
                    fill = fillClick;
                } else {
                    fill = fillOriginal;
                }
            }
        });
    }

    /**
     * Get the original fill color of the button.
     *
     * @return The original fill color.
     */
    public Color getFillOriginal() {
        return fillOriginal;
    }

    /**
     * Set the original fill color of the button.
     *
     * @param fillOriginal The original fill color to set.
     */
    public void setFillOriginal(Color fillOriginal) {
        this.fillOriginal = fillOriginal;
    }

    /**
     * Get the fill color when the mouse is over the button.
     *
     * @return The fill color for mouse over state.
     */
    public Color getFillOver() {
        return fillOver;
    }

    /**
     * Set the fill color when the mouse is over the button.
     *
     * @param fillOver The fill color for mouse over state to set.
     */
    public void setFillOver(Color fillOver) {
        this.fillOver = fillOver;
    }

    /**
     * Get the fill color when the button is clicked.
     *
     * @return The fill color for click state.
     */
    public Color getFillClick() {
        return fillClick;
    }

    /**
     * Set the fill color when the button is clicked.
     *
     * @param fillClick The fill color for click state to set.
     */
    public void setFillClick(Color fillClick) {
        this.fillClick = fillClick;
    }

    /**
     * Get the original line color of the button.
     *
     * @return The original line color.
     */
    public Color getLineOriginal() {
        return lineOriginal;
    }

    /**
     * Set the original line color of the button.
     *
     * @param lineOriginal The original line color to set.
     */
    public void setLineOriginal(Color lineOriginal) {
        this.lineOriginal = lineOriginal;
    }

    /**
     * Get the line color when the mouse is over the button.
     *
     * @return The line color for mouse over state.
     */
    public Color getLineOver() {
        return lineOver;
    }

    /**
     * Set the line color when the mouse is over the button.
     *
     * @param lineOver The line color for mouse over state to set.
     */
    public void setLineOver(Color lineOver) {
        this.lineOver = lineOver;
    }

    /**
     * Get the stroke width of the button border.
     *
     * @return The stroke width in pixels.
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Set the stroke width of the button border.
     *
     * @param strokeWidth The stroke width in pixels to set.
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            g2d.setColor(fill);
            g2d.fillRoundRect(s, s, w, h, h, h);

            g2d.setStroke(new BasicStroke(s));
            g2d.setColor(line);
            g2d.drawRoundRect(s, s, w, h, h, h);
            g2d.dispose();
        }
        super.paintComponent(g);
    }
}