package AppTrain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u66F8\u540D\uFF1A");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel.add(textField);
		textField.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u4F5C\u80051\uFF1A");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(textField_1);
		textField_1.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u4F5C\u80052\uFF1A");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_2.add(textField_2);
		textField_2.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton btnNewButton = new JButton("\u78BA\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLManager.addBook(textField.getText(), textField_1.getText());
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_3.add(btnNewButton_1);
	}

}
