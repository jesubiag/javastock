package frd.app.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frd.app.ui.UserWindow;


public class Menu extends JMenuBar {

	public Menu(){

		JMenu file = new JMenu("Archivo");

		JMenuItem exitMenu = new JMenuItem("Salir");
		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		file.add(exitMenu);

		add(file);
		
		JMenu edit = new JMenu("Editar");
		
		JMenuItem users = new JMenuItem("Usuarios");
		users.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserWindow();
			}
		});
		edit.add(users);
		
		add(edit);
		
	}
}
