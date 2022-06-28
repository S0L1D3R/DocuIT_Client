package pl.interia.rym.maciej.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pl.interia.rym.maciej.Structures.DITProject;

public class DITProjectPanel extends JPanel {

	private DITProject loadedProject = new DITProject();
	private DITWorkingArea workingArea;
	private JTextField textField_Title;
	
	public DITProjectPanel() {
		loadedProject.setName("Brak_nazwy_Projektu");
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		textField_Title = new JTextField("Brak nazwy");
		textField_Title.setMaximumSize(new Dimension(5000, 40));
		textField_Title.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Title.setBorder(BorderFactory.createMatteBorder(1, 10, 1, 10, Color.red));
		
		workingArea = new DITWorkingArea(loadedProject);
		workingArea.setMaximumSize(new Dimension(5000, 5000));
		
		textField_Title.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadedProject.setName(textField_Title.getText());
			}
		});
		
		add(textField_Title);
		add(workingArea);
	}
	
	public void createNewProject() {
		loadedProject = new DITProject();
		loadedProject.setName("Brak_nazwy_Projektu");
		textField_Title.setText("Brak_nazwy_Projektu");
		workingArea.loadProject(loadedProject);
	}
	
	public boolean isProjectEmpty() {
		if(loadedProject.getElements().size() == 0) {
			return true;
		}
		return false;
	}

	public void loadProject(DITProject project) {
		if(project != null) {
			loadedProject = project;
			workingArea.loadProject(loadedProject);
			textField_Title.setText(loadedProject.getName());	
		}
	}

	public DITWorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(DITWorkingArea workingArea) {
		this.workingArea = workingArea;
	}

	public DITProject getLoadedProject() {
		return loadedProject;
	}

	public void setLoadedProject(DITProject loadedProject) {
		this.loadedProject = loadedProject;
	}
	
}
