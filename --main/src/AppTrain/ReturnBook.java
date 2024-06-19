package AppTrain;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ReturnBook extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
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
		
		JLabel lblNewLabel = new JLabel("\u66F8\u540D");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_3.add(textField);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\u7D50\u679C\uFF1A");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<>();
		SQLManager.lendComboBox(comboBox);
		panel_1.add(comboBox);
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 20));
		comboBox.setMaximumRowCount(Integer.parseInt(SQLManager.AllBook_count()));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton button = new JButton("\u67E5\u8A62");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textField.getText().isEmpty()) {
					comboBox.removeAllItems();
					SQLManager.lendSearch("SELECT * FROM lend", comboBox, textField.getText());
				}
			}
		});
		button.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button);
		
		JButton button_2 = new JButton("\u78BA\u8A8D\u6B78\u9084");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = (String)comboBox.getSelectedItem();
				String[] lend = str.split("：");
				String[] lend2 = lend[1].split("。");
				System.out.println(lend2[0]);
				SQLManager.returnBook(lend2[0].trim());
				dispose();
			}
		});
		button_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button_2);
		
		JButton button_1 = new JButton("\u91CD\u65B0\u6574\u7406");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ReturnBook().setVisible(true);
			}
		});
		button_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(button_1);
	}

}
