package AppTrain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_3;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("\u5E33\u865F\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("新細明體", Font.PLAIN, 16));
		textField.setColumns(15);
		panel.add(textField);
		
		panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("\u5BC6\u78BC\uFF1A");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("新細明體", Font.PLAIN, 16));
		passwordField.setColumns(15);
		panel_1.add(passwordField);
		
		panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNewButton = new JButton("\u767B\u5165");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLManager SQL = new SQLManager("jdbc:mysql://localhost:3306/bookmanager", textField.getText(), passwordField.getText(), LoginGUI.this);	
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_2.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 5));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(LoginGUI.class.getResource("/Image/KPoacAq3Vc_small.jpg")));
		contentPane.add(lblNewLabel_2, BorderLayout.CENTER);
		
		lblNewLabel_3 = new JLabel("\u5716\u66F8\u9928\u7BA1\u7406\u7CFB\u7D71");
		lblNewLabel_3.setBackground(new Color(128, 255, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3, BorderLayout.SOUTH);
	}

}
