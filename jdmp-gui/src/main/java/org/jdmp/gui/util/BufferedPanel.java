package org.jdmp.gui.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import javax.swing.JPanel;

public class BufferedPanel extends JPanel implements CanBeRepainted, ComponentListener, UpdateListener {
	private static final long serialVersionUID = 7495571585267828933L;

	private int passes=4;
	
	private JPanel panel = null;

	private BufferedImage bufferedImage = null;

	
	public BufferedPanel(JPanel panel) {
		this.panel = panel;
		this.addComponentListener(this);
		GraphicsExecutor.scheduleUpdate(this);
		if(panel instanceof CanBeUpdated){
			((CanBeUpdated)panel).addUpdateListener(this);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (bufferedImage != null) {
			g2d.drawImage(bufferedImage, 0, 0, getWidth() , getHeight(), null);
		} else {
			g2d.addRenderingHints(UIDefaults.AALIAS);
			g2d.setColor(Color.gray);
			g2d.drawLine(0, 0, getWidth(), getHeight() );
			g2d.drawLine(getWidth() , 0, 0, getHeight() );
		}
	}

	public void paintUsingReflection(Graphics2D g2d) throws Exception {
		Method 	m = panel.getClass().getDeclaredMethod("paintComponent",new Class<?>[]{Graphics.class});
		boolean accessible=m.isAccessible();
		m.setAccessible(true);
		m.invoke(panel, new Object[]{g2d});
		m.setAccessible(accessible);
	}


	
	public void repaintUI() {
		try {
			int width=getWidth();
			width=width<1?1:width;
			int height=getHeight();
			height=height<1?1:height;
		    BufferedImage tempBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    panel.setSize(width, height);
		    Graphics2D g2d = (Graphics2D) tempBufferedImage.getGraphics();		
			paintUsingReflection(g2d);
			g2d.dispose();
			bufferedImage = tempBufferedImage;
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

	public void componentHidden(ComponentEvent e) {
		GraphicsExecutor.scheduleUpdate(this);
	}

	public void componentMoved(ComponentEvent e) {
		GraphicsExecutor.scheduleUpdate(this);
	}

	public void componentResized(ComponentEvent e) {
		GraphicsExecutor.scheduleUpdate(this);
	}

	public void componentShown(ComponentEvent e) {
		GraphicsExecutor.scheduleUpdate(this);
	}

	public void updated() {
		GraphicsExecutor.scheduleUpdate(this);		
	}

}

