package pl.interia.rym.maciej.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.interia.rym.maciej.DITClientApp;
import pl.interia.rym.maciej.IO.DataProjectParser;
import pl.interia.rym.maciej.Structures.DITFolder;
import pl.interia.rym.maciej.Structures.DITProject;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;

import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;

public class DITMainFrame extends JFrame {
	
	private JPanel jP_Folders;
	private JPanel jP_Projects;
	private JPanel jP_TopCommands;
	private DITProjectPanel jP_ProjectPanel;
	
	private GridBagConstraints gbc_panel_1;
	private GridBagConstraints gbc_panel_2;
	private GridBagConstraints gbc_panel_3;
	
	private JButton btn_SaveProjectToFile;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btn_ShowOptions;
	private JButton btn_SaveProject_1;
	private JButton tbn_Refresh;
	
	private JList<String> list_Projects;
	private JList<String> list_Folders;
	
	private DITClientApp app;
	
	public DITMainFrame(String appname, DITClientApp app) {
		createGUI(appname);
		this.app = app;
	}
	
	private void createGUI(String appname) {
		setTitle(appname);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(800, 900));
//		setBounds(1920, 0, 1920, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100, 300, 250};
		gridBagLayout.rowHeights = new int[] {100, 900};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
	
		jP_TopCommands = new JPanel();
		jP_TopCommands.setMinimumSize(new Dimension(500, 100));
		jP_TopCommands.setPreferredSize(new Dimension(10, 100));
		jP_TopCommands.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_jP_TopCommands = new GridBagConstraints();
		gbc_jP_TopCommands.gridwidth = 3;
		gbc_jP_TopCommands.fill = GridBagConstraints.BOTH;
		gbc_jP_TopCommands.gridy = 0;
		gbc_jP_TopCommands.gridx = 0;
		gbc_jP_TopCommands.weighty = 0.5;
		gbc_jP_TopCommands.weightx = 1.0;
		getContentPane().add(jP_TopCommands, gbc_jP_TopCommands);
		jP_TopCommands.setLayout(new GridLayout(0, 8, 5, 5));
		
		JButton btn_SaveProject = new JButton("Zr\u00F3b kopie zapasow\u0105 (lokaln\u0105)");
		btn_SaveProject.setHorizontalAlignment(SwingConstants.LEFT);
		btn_SaveProject.addActionListener(createActionListener_BackupProject());
		
		btn_SaveProject_1 = new JButton("Zr\u00F3b kopie zapasow\u0105 (chmurow\u0105)");
		btn_SaveProject_1.addActionListener(createActionListener_SaveCurrentProjectToServer(this));
		btn_SaveProject_1.setHorizontalAlignment(SwingConstants.LEFT);
		jP_TopCommands.add(btn_SaveProject_1);
		jP_TopCommands.add(btn_SaveProject);
		
		btn_SaveProjectToFile = new JButton("Zapisz do konkretnego pliku");
		btn_SaveProjectToFile.addActionListener(createActionListener_SaveToFileDialogue(this));
		jP_TopCommands.add(btn_SaveProjectToFile);
		
		btnNewButton_3 = new JButton("Otw\u00F3rz projekt z pliku");
		btnNewButton_3.addActionListener(createActionListener_OpenFileDialogue(this));
		jP_TopCommands.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Otw\u00F3rz zaznaczony projekt");
		btnNewButton_4.addActionListener(createActionListener_OpenSelectedProjectInTheList());
		
		btn_ShowOptions = new JButton("Opcje");
		btn_ShowOptions.addActionListener(createActionListener_OpenOptionsWindow());
		jP_TopCommands.add(btn_ShowOptions);
		jP_TopCommands.add(btnNewButton_4);
		
		tbn_Refresh = new JButton("Odœwie¿ dane z serwera");
		tbn_Refresh.addActionListener(createActionListener_RefreshDataFromServer());
		
		JButton btn_NewProject = new JButton("Nowy projekt");
		btn_NewProject.addActionListener(createActionListener_NewProject());
		jP_TopCommands.add(btn_NewProject);
		jP_TopCommands.add(tbn_Refresh);
		
		jP_ProjectPanel = new DITProjectPanel();
		jP_ProjectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jP_ProjectPanel.setPreferredSize(new Dimension(1000, 1000));
		gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weighty = 1.0;
		gbc_panel_1.weightx = 5.0;
		gbc_panel_1.insets = new Insets(0, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(jP_ProjectPanel, gbc_panel_1);
		
		jP_Projects = new JPanel();
		jP_Projects.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.weighty = 1.0;
		gbc_panel_2.weightx = 0.1;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		getContentPane().add(jP_Projects, gbc_panel_2);
		jP_Projects.setLayout(new BoxLayout(jP_Projects, BoxLayout.X_AXIS));
	
		jP_Folders = new JPanel();
		jP_Folders.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.weightx = 0.1;
		gbc_panel_3.weighty = 1.0;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 1;
		getContentPane().add(jP_Folders, gbc_panel_3);
		jP_Folders.setLayout(new BoxLayout(jP_Folders, BoxLayout.X_AXIS));
		
		list_Projects = new JList<String>();
		list_Projects.setMaximumSize(new Dimension(5000, 5000));
		list_Projects.setPreferredSize(new Dimension(1000, 500));
		list_Projects.addListSelectionListener(createListSelectionListener_ListOfProjects());
		jP_Projects.add(list_Projects);
		
		list_Folders = new JList<String>();
		list_Folders.setPreferredSize(new Dimension(1000, 500));
		list_Folders.setMaximumSize(new Dimension(5000, 5000));
		list_Folders.addListSelectionListener(createListSelectionListener_ListOfFolders());
		jP_Folders.add(list_Folders);
		
		setVisible(true);
	}

	private ActionListener createActionListener_deleteCurrentProject() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		};
		return listener;
	}
	
	private ActionListener createActionListener_NewProject() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jP_ProjectPanel.createNewProject();
			}
		};
		return listener;
	}

	private ActionListener createActionListener_SaveCurrentProjectToServer(JFrame parentFrame) {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list_Folders.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(parentFrame, "Nie wybrano ¿adnego folderu!");
					return;
				}
				app.getConnection().saveProjectToServer(jP_ProjectPanel.getLoadedProject());
				System.out.println("Wyslanie danych projektu do serwera");
			}
		};
		return listener;
	}

	private ActionListener createActionListener_OpenSelectedProjectInTheList() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list_Projects.getSelectedIndex() >= 0) {
					jP_ProjectPanel.loadProject(DITFolder.getProjectFromFoldersWithID(getSelectedProjectInTheList()));
				}
			}
		}; 
		return listener;
	}
	
	private ListSelectionListener createListSelectionListener_ListOfFolders() {
		ListSelectionListener listener = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					int idOfFolder = Integer.parseInt(list_Folders.getSelectedValue().split("_")[0]);
					DITFolder folderFound = DITFolder.getFolderByID(idOfFolder);
					updateList_Projects(folderFound.getProjectsInsideFolderAsArray());
				}
			}
		};
		return listener;
	}
	
	//Nie potrzebuje tego???
	private ListSelectionListener createListSelectionListener_ListOfProjects() {
		ListSelectionListener listener = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					System.out.println("prj:" + list_Projects.getSelectedValue());
				}
			}
		};
		return listener;
	}
	
	private int getSelectedProjectInTheList() {
		return Integer.parseInt(list_Projects.getSelectedValue().split("_")[0]);
	}
	
	private ActionListener createActionListener_OpenOptionsWindow() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (Frame frame : JFrame.getFrames()) {
					if(frame instanceof DITAdditionalFrame_Options && frame.isVisible()) {
						frame.toFront();
						return;
					}
				}
				new DITAdditionalFrame_Options(DITClientApp.options, getX(), getY());
			}
		};
		return listener;
	}

	private ActionListener createActionListener_RefreshDataFromServer() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.getConnection().sendMessageToServer("Request->Data");
				updateList_Folders(DITFolder.getFoldersAsArray());
			}
		};
		
		return listener; 
	}
	
	private ActionListener createActionListener_BackupProject() {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jP_ProjectPanel.isProjectEmpty()) {
					return;
				}
				DataProjectParser parser = new DataProjectParser();
				parser.saveProjectToBackupsFolder(jP_ProjectPanel.getLoadedProject());
				
			}
		};
		return listener;
	}
	
	private ActionListener createActionListener_SaveToFileDialogue(JFrame frame) {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jP_ProjectPanel.isProjectEmpty()) {
					return;
				}
				FileDialog fileDialog = new FileDialog(frame, "Wybierz miejsce", FileDialog.SAVE);
				fileDialog.setFile(".txt");
				fileDialog.setDirectory(DITClientApp.appDir);
				fileDialog.setVisible(true);
				if(fileDialog.getFile() == null) {
					return;
				}
				
				DataProjectParser parser = new DataProjectParser(fileDialog.getDirectory());
				parser.saveProjectToFile(jP_ProjectPanel.getLoadedProject());
				
			}
		};
		return listener;
	}
	
	private ActionListener createActionListener_OpenFileDialogue(JFrame frame) {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(frame, "Wybierz miejsce", FileDialog.LOAD);
				fileDialog.setFile(".txt");
				fileDialog.setDirectory(DITClientApp.appDir);
				fileDialog.setVisible(true);
				if(fileDialog.getFile() == null) {
					return;
				}
				
				DataProjectParser parser = new DataProjectParser();
				DITProject projectToBeLoaded =  parser.readProjectFromFile(fileDialog.getDirectory(), fileDialog.getFile());
				jP_ProjectPanel.loadProject(projectToBeLoaded);
			}
		};
		return listener;
	}
	
	public void updateList_Projects(String[] arrayOfStrings) {
		list_Projects.setListData(arrayOfStrings);
	}
	
	public void updateList_Folders(String[] arrayOfStrings) {
		list_Folders.setListData(arrayOfStrings);
	}
	
	public DITProjectPanel getProjectPanel() {
		return jP_ProjectPanel;
	}

	public JPanel getjP_Folders() {
		return jP_Folders;
	}

	public void setjP_Folders(JPanel jP_Folders) {
		this.jP_Folders = jP_Folders;
	}

	public JPanel getjP_Projects() {
		return jP_Projects;
	}

	public void setjP_Projects(JPanel jP_Projects) {
		this.jP_Projects = jP_Projects;
	}

	public DITProjectPanel getjP_ProjectPanel() {
		return jP_ProjectPanel;
	}

	public void setjP_ProjectPanel(DITProjectPanel jP_ProjectPanel) {
		this.jP_ProjectPanel = jP_ProjectPanel;
	}

	public JPanel getjP_TopCommands() {
		return jP_TopCommands;
	}

	public void setjP_TopCommands(JPanel jP_TopCommands) {
		this.jP_TopCommands = jP_TopCommands;
	}

	public GridBagConstraints getGbc_panel_1() {
		return gbc_panel_1;
	}

	public void setGbc_panel_1(GridBagConstraints gbc_panel_1) {
		this.gbc_panel_1 = gbc_panel_1;
	}

	public GridBagConstraints getGbc_panel_2() {
		return gbc_panel_2;
	}

	public void setGbc_panel_2(GridBagConstraints gbc_panel_2) {
		this.gbc_panel_2 = gbc_panel_2;
	}

	public GridBagConstraints getGbc_panel_3() {
		return gbc_panel_3;
	}

	public void setGbc_panel_3(GridBagConstraints gbc_panel_3) {
		this.gbc_panel_3 = gbc_panel_3;
	}

	public JButton getBtn_SaveProjectToFile() {
		return btn_SaveProjectToFile;
	}

	public void setBtn_SaveProjectToFile(JButton btn_SaveProjectToFile) {
		this.btn_SaveProjectToFile = btn_SaveProjectToFile;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	public void setBtnNewButton_3(JButton btnNewButton_3) {
		this.btnNewButton_3 = btnNewButton_3;
	}

	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	public void setBtnNewButton_4(JButton btnNewButton_4) {
		this.btnNewButton_4 = btnNewButton_4;
	}

	public JButton getBtn_ShowOptions() {
		return btn_ShowOptions;
	}

	public void setBtn_ShowOptions(JButton btn_ShowOptions) {
		this.btn_ShowOptions = btn_ShowOptions;
	}

	public JButton getBtn_SaveProject_1() {
		return btn_SaveProject_1;
	}

	public void setBtn_SaveProject_1(JButton btn_SaveProject_1) {
		this.btn_SaveProject_1 = btn_SaveProject_1;
	}

	public JList<String> getList_Projects() {
		return list_Projects;
	}

	public void setList_Projects(JList<String> list_Projects) {
		this.list_Projects = list_Projects;
	}

	public JList<String> getList_Folders() {
		return list_Folders;
	}

	public void setList_Folders(JList<String> list_Folders) {
		this.list_Folders = list_Folders;
	}
}
