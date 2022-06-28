package pl.interia.rym.maciej.Structures;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import pl.interia.rym.maciej.GUI.DITWorkingArea;

public class DITProjectElement extends JPanel {
	
	private int id;
	
	private JTextField titleField;
	private JTextArea textArea;
	private JLabel markedLabel;
	private DITWorkingArea parentPanel;
	
	private int x,y;
	
	public DITProjectElement(int x, int y, DITWorkingArea panel) {
		this.x = x;
		this.y = y;
		this.parentPanel = panel;
		setBounds(x, y, 250, 130);
		setLayout(null);
		
		titleField = new JTextField("Title");
		titleField.setBounds(
							0,
							0, 
							200, 
							30);
		textArea = new JTextArea("Content");
		textArea.setBounds(
							0, 
							31, 
							250, 
							100);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		markedLabel = new JLabel("",SwingConstants.CENTER);
		markedLabel.setBounds(
							200, 
							0, 
							50, 
							30);
		
		this.add(titleField);
		this.add(textArea);
		this.add(markedLabel);
		
		addListenerToLabel();
	}
	
	public DITProjectElement(int id, int x, int y, String title, String content) {
		this.x = x;
		this.y = y;
		this.id = id;
		setBounds(x, y, 250, 130);
		setLayout(null);
		setVisible(true);
		
		titleField = new JTextField(title);
		titleField.setBounds(
							0,
							0, 
							200, 
							30);
		textArea = new JTextArea(content);
		textArea.setBounds(
							0, 
							31, 
							250, 
							100);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		markedLabel = new JLabel("",SwingConstants.CENTER);
		markedLabel.setBounds(
							200, 
							0, 
							50, 
							30);
		
		this.add(titleField);
		this.add(textArea);
		this.add(markedLabel);
		
		addListenerToLabel();
	}

	public Point getMiddlePoint() {
		return new Point(getX()+(getWidth()/2), getY()+(getHeight()/2));
	}
	
	private void addListenerToLabel() {
		DITProjectElement el = this;
		markedLabel.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON3) {
				parentPanel.callLinking(el);
			}
		}
	});
	}

	public void setTitle(String title) {
		titleField.setText(title);
	}
	
	public void setContent(String content) {
		textArea.setText(content);
	}
	
	public void addToJPanel(JPanel panel) {
		panel.add(this);
		panel.repaint();
	}
	
	public JTextField getTitleField() {
		return titleField;
	}

	public void setTitleField(JTextField titleField) {
		this.titleField = titleField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getMarkedLabel() {
		return markedLabel;
	}

	public void setMarkedLabel(JLabel markedLabel) {
		this.markedLabel = markedLabel;
	}

	public int getId() {
		return id;
	}
	
	public void setX() {
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public DITWorkingArea getParentPanel() {
		return parentPanel;
	}

	public void setParentPanel(DITWorkingArea parentPanel) {
		this.parentPanel = parentPanel;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public DITProjectElement() {
	}
}
