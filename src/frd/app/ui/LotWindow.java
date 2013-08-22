package frd.app.ui;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import frd.app.ui.components.CancelButton;
import frd.db.LotManager;
import frd.model.Lot;


public class LotWindow extends JDialog {

	JLabel title = new JLabel("Lotes");
	JButton cancel = new CancelButton(this);
	
	public LotWindow(){
		
		getContentPane().setLayout(new GridLayout(4,1));
		getContentPane().add(title);
		
		String[] columnNames = {"ID","FECHA DE CREACION","FECHA DE CADUCIDAD","CANTIDAD INICIAL", "CANTIDAD ACTUAL"};
		Object[][] rowData = null;
		
		try {
			List<Lot> lots = LotManager.getLots();
			rowData = new Object[lots.size()][4];
			int i = 0;
			for( Lot l : lots ){
				rowData[i][0] = l.getId();
				rowData[i][1] = l.getCreateDate();
				rowData[i][2] = l.getDueDate();
				rowData[i][3] = l.getInitialAmount();
				rowData[i++][4] = l.getCurrentAmount();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		getContentPane().add(cancel);
		setSize(300, 400);
		setVisible(true);
		
	}
	
}
