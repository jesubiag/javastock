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
import frd.db.UserManager;
import frd.model.User;

public class UserWindow extends JDialog{

	JLabel title = new JLabel("Usuarios");
	JButton cancel = new CancelButton(this);

	public UserWindow(){
		
		getContentPane().setLayout(new GridLayout(3,1));
		getContentPane().add(title);

		String[] columnNames = {"ID","NOMBRE","CREADOR","FECHA"};
		Object[][] rowData = null;

		try {
			List<User> users = UserManager.getUsers();
			rowData = new Object[users.size()][4];
			int i = 0;
			for( User u : users ){
				rowData[i][0] = u.getId();
				rowData[i][1] = u.getUsername();
				rowData[i][2] = u.getCreatedBy();
				rowData[i++][3] = u.getCreateDate();
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
