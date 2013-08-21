package frd.app.ui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import frd.app.ui.components.Menu;


public class MainWindow extends JFrame{

	private JLabel title = new JLabel("Sistema de Stock");
	private JMenuBar menu = new Menu();

	public MainWindow(){
		getContentPane().setLayout(new FlowLayout());
		setTitle("Sistema de Stock");
        setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add(title);
		setJMenuBar(menu);
	}
}
