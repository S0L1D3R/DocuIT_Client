package pl.interia.rym.maciej.GUI;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import pl.interia.rym.maciej.Structures.DITLink;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;

public class DITWorkingArea extends JPanel {
	
	private DITProject currentProject;
	
	private DITProjectElement firstLinkingElement;
	private DITProjectElement secondLinkingElement;

	public DITWorkingArea(DITProject project) {
		currentProject = project;
		DITWorkingArea panel = this;
		panel.setLayout(null);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					DITProjectElement newProjectElement = new DITProjectElement(e.getX(), e.getY(), panel);
					add(newProjectElement);
					currentProject.addNewElement(newProjectElement);
					repaint();
				}
				else if(e.getButton() == MouseEvent.BUTTON2) {
					System.out.println(e.getX() + "<>" + e.getY() + " | " + currentProject.getLinks().size());
				}
			}
		});
	}
	
	public void loadProject(DITProject project) {
		removeAll();
		firstLinkingElement = null;
		secondLinkingElement = null;
		currentProject = project;
		for (DITProjectElement element : currentProject.getElements()) {
			element.setParentPanel(this);
			add(element);
		}
		repaint();
	}
	
	public void callLinking(DITProjectElement element) {
		if(firstLinkingElement == null) {
			firstLinkingElement = element;
			firstLinkingElement.getMarkedLabel().setText("[]");
			return;
		}
		
		if(firstLinkingElement.getId() == element.getId()) {
			firstLinkingElement.getMarkedLabel().setText("");
			firstLinkingElement = null;
			return;
		}
		
		if(secondLinkingElement == null) {
			secondLinkingElement = element;
			
			DITLink link = new DITLink(firstLinkingElement, secondLinkingElement);
			
			if(!currentProject.isLinkAlreadyCreated(link)) {
				currentProject.addLink(link);
				firstLinkingElement.getMarkedLabel().setText("");
				
				firstLinkingElement = null;
				secondLinkingElement = null;
				repaint();
			}
			else {
				firstLinkingElement.getMarkedLabel().setText("");
				
				firstLinkingElement = null;
				secondLinkingElement = null;
				currentProject.removeLinkByItsElements(link);
				repaint();
			}
		}
		
		/*
		if(firstLinkingElement == null) {
			firstLinkingElement = element;
			firstLinkingElement.getMarkedLabel().setText("[]");
			return;
		}
		if(secondLinkingElement == null) {
			secondLinkingElement = element;
			DITLink link = new DITLink(firstLinkingElement, secondLinkingElement);
			
			if(isLinkAlreadyCreated(link)) {
				System.out.println("exists");
				firstLinkingElement.getMarkedLabel().setText("");
				
				removeLinkByItsElements(link);
				
				firstLinkingElement = null;
				secondLinkingElement = null;

				repaint();
				return;
			}
			
			if(element == firstLinkingElement) {
				element.getMarkedLabel().setText("");
				firstLinkingElement = null;
				return;
			}
			firstLinkingElement.getMarkedLabel().setText("");
			
			links.add(link);
			repaint();
			
			firstLinkingElement = null;
			secondLinkingElement = null;
			return;
		}
		firstLinkingElement.getMarkedLabel().setText("");
		firstLinkingElement = null;
		*/
	}
	
	
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setStroke(new BasicStroke(5));
    	for (DITLink ditLink : currentProject.getLinks()) {
    		g2.drawLine(
    				ditLink.getMiddleOfFirstElement().x,
    				ditLink.getMiddleOfFirstElement().y,
    				ditLink.getMiddlePointOfSecondElement().x,
    				ditLink.getMiddlePointOfSecondElement().y);
		}
//    	for (DITLink ditLink : links) {
//    		g2.drawLine(
//    				ditLink.getElement1().getX(),
//    				ditLink.getElement1().getY(),
//    				ditLink.getElement2().getX(),
//    				ditLink.getElement2().getY());
//		}
    }

}
