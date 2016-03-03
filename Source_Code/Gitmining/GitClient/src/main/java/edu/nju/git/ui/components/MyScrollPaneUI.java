package edu.nju.git.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * 
 * @author cuihao
 * 2015-11-25 19:23:20
 */
public class MyScrollPaneUI extends BasicScrollBarUI{
	/**
	 * arc number
	 */
	private final float ARC_NUMBER = 20.0f;
	/**
	 * pictures
	 */
	private final String PIC_BAR = "src/main/resources/pictures/units/bar.png";
	private final String PIC_UP = "src/main/resources/pictures/units/up.png";
	private final String PIC_DOWN = "src/main/resources/pictures/units/down.png";
	
	/**
	 * draw scroll bar
	 * thumbBounds has attributes: x y width height
	 */
	@Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
		Graphics2D g2 = (Graphics2D) g;
        int w = thumbBounds.width - 1;
        int h = thumbBounds.height - 1;
        Paint oldPaint = g2.getPaint();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.translate(thumbBounds.x, thumbBounds.y);

        Shape arcRect = new RoundRectangle2D.Float(0.0f, 0.0f, (float) w, (float) h, ARC_NUMBER, ARC_NUMBER);

        Paint arcRectPaint = null;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            arcRectPaint = new GradientPaint(0, 0, new Color(225, 225, 225),
                    thumbBounds.width, 0, new Color(162, 162, 162));
        } else {
            arcRectPaint = new GradientPaint(0, 0, new Color(225, 225, 225),
                    0, thumbBounds.height, new Color(162, 162, 162));
        }
        g2.setPaint(arcRectPaint);
        g2.fill(arcRect);

        g2.setColor(new Color(150, 150, 150));
        g2.draw(arcRect);

        g2.setColor(new Color(230, 230, 230));
        Rectangle bounds = arcRect.getBounds();
        g2.drawRoundRect(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 2, (int) ARC_NUMBER, (int) ARC_NUMBER);

        g2.translate(-thumbBounds.x, -thumbBounds.y);
        g2.setPaint(oldPaint);
	}
	
	/**
	 * draw scroll background
	 */
	@Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds){
		g.drawImage(new ImageIcon(PIC_BAR).getImage(), trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);
	}
	
	@Override
    protected void paintDecreaseHighlight(Graphics g) {
    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {
    }
    
    /**
     * draw up arrow
     */
    @Override
    protected javax.swing.JButton createDecreaseButton(int orientation) {
        return new BasicArrowButton(orientation) {

			private static final long serialVersionUID = 6645974556779003562L;

			@Override
            public void paint(Graphics g) {
            	g.drawImage(new ImageIcon(PIC_UP).getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
    }
    
    /**
     * draw down arrow
     */
    @Override
    protected javax.swing.JButton createIncreaseButton(int orientation) {

        return new BasicArrowButton(orientation) {

			private static final long serialVersionUID = 2812753955642319448L;

			@Override
            public void paint(Graphics g) {
                g.drawImage(new ImageIcon(PIC_DOWN).getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
    }
	
	
}
