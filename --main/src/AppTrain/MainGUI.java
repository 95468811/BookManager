package AppTrain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("\u91CD\u65B0\u6574\u7406");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainGUI().setVisible(true);;
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u767B\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(50);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("\u7E3D\u66F8\u91CF\uFF1A"+SQLManager.AllBook_count()+"\u672C");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(SQLManager.component, SQLManager.selectAllBook("SELECT a.*, b.w_name FROM allbook a, writer b WHERE a.w_id = b.w_id"), "總書量", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("\u76EE\u524D\u66F8\u5EAB\u91CF"+SQLManager.book_count()+"\u672C");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(SQLManager.component, SQLManager.selectBook("SELECT a.*, b.w_name FROM book a, writer b WHERE a.w_id = b.w_id"), "館內書量", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton_2_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		
		JButton btnNewButton_3 = new JButton("<html><center>\u5DF2<br>\u501F<br>\u51FA<br>. .<br>\r\n"+SQLManager.lend_count()+"</center></html>");
		Timer timer = new Timer(500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setText("\u7E3D\u66F8\u91CF\uFF1A"+SQLManager.AllBook_count()+"\u672C");
				btnNewButton_2_1.setText("\u76EE\u524D\u66F8\u5EAB\u91CF"+SQLManager.book_count()+"\u672C");
				btnNewButton_3.setText("<html><center>\u5DF2<br>\u501F<br>\u51FA<br>. .<br>\r\n"+SQLManager.lend_count()+"</center></html>");
			}
		});
		timer.start();
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(SQLManager.component, SQLManager.selectLendBook("SELECT * FROM lend"), "已借出", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_2.add(btnNewButton_3);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_4 = new JButton("\u65B0\u589E\u66F8\u7C4D");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddBook().setVisible(true);;
			}
		});
		btnNewButton_4.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("\u501F\u51FA\u66F8\u7C4D");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LendBook().setVisible(true);
			}
		});
		btnNewButton_4_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_2 = new JButton("\u66F8\u7C4D\u5831\u5EE2");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BreakBook().setVisible(true);
			}
		});
		btnNewButton_4_2.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(btnNewButton_4_2);
		
		JButton btnNewButton_4_2_1 = new JButton("\u6B78\u9084\u7D00\u9304");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(SQLManager.component, SQLManager.selectReturn());
			}
		});
		
		JButton btnNewButton_4_2_1_1 = new JButton("\u5831\u5EE2\u7D00\u9304");
		btnNewButton_4_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(SQLManager.component, SQLManager.selectBreak());
			}
		});
		btnNewButton_4_2_1_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(btnNewButton_4_2_1_1);
		btnNewButton_4_2_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_3.add(btnNewButton_4_2_1);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton btnNewButton_3_1 = new JButton("<html><center>\u6B78<br>\u9084</center></html>");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReturnBook().setVisible(true);
			}
		});
		btnNewButton_3_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		panel_4.add(btnNewButton_3_1);
	}
}
