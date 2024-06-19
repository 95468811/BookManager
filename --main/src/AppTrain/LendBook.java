package AppTrain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LendBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendBook frame = new LendBook();
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
	public LendBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel.setLayout(fl_panel);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("\u4EE5\u66F8\u540D\u67E5\u8A62");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_3.add(textField);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("\u4EE5\u4F5C\u8005\u67E5\u8A62");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_4.add(textField_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\u7D50\u679C\uFF1A");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<>();
		SQLManager.bookComboBox(comboBox);
		panel_1.add(comboBox);
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 20));
		comboBox.setMaximumRowCount(Integer.parseInt(SQLManager.AllBook_count()));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton button = new JButton("\u67E5\u8A62");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty()) {
					System.out.println(123);
					comboBox.removeAllItems();
					SQLManager.SearchComboBox("SELECT a.b_name, b.w_name FROM book a, writer b WHERE a.w_id = b.w_id AND a.b_name=? AND b.w_name =?",comboBox, textField.getText(), textField_1.getText());
				} else if(!textField_1.getText().isEmpty()) {
					System.out.println(456);
					comboBox.removeAllItems();
					SQLManager.writerSearchComboBox("SELECT a.b_name, b.w_name FROM book a, writer b WHERE a.w_id = b.w_id AND b.w_name=?",comboBox, textField_1.getText());
				} else if(!textField.getText().isEmpty()) {
					comboBox.removeAllItems();
					System.out.println(789);
					SQLManager.bookSearchComboBox("SELECT a.b_name, b.w_name FROM book a, writer b WHERE a.w_id = b.w_id AND a.b_name=?", comboBox, textField.getText());
				}
			}
		});
		button.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button);
		
		JButton button_2 = new JButton("\u78BA\u8A8D\u501F\u51FA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = (String)comboBox.getSelectedItem();
				String[] lend = str.split("：");
				SQLManager.lendBook(lend[2]);
				dispose();
			}
		});
		button_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button_2);
		
		JButton button_1 = new JButton("\u91CD\u65B0\u6574\u7406");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LendBook().setVisible(true);
			}
		});
		button_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button_1);
	}

}
