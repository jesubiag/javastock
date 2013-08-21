package frd.app.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class CancelButton extends JButton {
	
	JDialog win;
	
	public CancelButton(JDialog window){
		super("Cancelar");
		win = window;
		
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				win.dispose();
			}
		});
	}
}
