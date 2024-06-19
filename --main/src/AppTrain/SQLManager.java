package AppTrain;

import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SQLManager {

	private static Connection sqlConnection;
	public static Component component;
	
	public SQLManager(String url, String user, String password, JFrame c) {		
		try {
			SQLManager.component = c;
			Class.forName("com.mysql.cj.jdbc.Driver");
			SQLManager.sqlConnection = DriverManager.getConnection(url, user, password);
			System.out.println("資料庫登入成功");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {						
						MainGUI frame = new MainGUI();
						frame.setVisible(true);
						c.dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.component , "帳號或密碼錯誤，請重新輸入", "錯誤", JOptionPane.INFORMATION_MESSAGE);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public static void addBook(String bookName, String writerName) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try(PreparedStatement ps = sqlConnection.prepareStatement("INSERT INTO allbook(b_name, w_id, b_date) VALUES (?, ?, ?)");
			PreparedStatement ps1 = sqlConnection.prepareStatement("SELECT * FROM writer WHERE w_name =?");
			PreparedStatement ps2 = sqlConnection.prepareStatement("SELECT a.* FROM allbook a, writer b WHERE a.b_name = ? AND a.w_id=b.w_id");
			PreparedStatement ps3 = sqlConnection.prepareStatement("SELECT a.* FROM allbook a, writer b WHERE a.b_name=? AND a.w_id= b.w_id AND b.w_name = ?")) {
			ps3.setString(1, bookName);
			ps3.setString(2, writerName);
			ResultSet rs = ps3.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(SQLManager.component, "該書籍已存在", "訊息", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			ps1.setString(1, writerName);
			ps2.setString(1, bookName);
			
			if(rs.next()) {
				System.out.println("該作者已存在，ID為："+rs.getInt("w_id")+"，作者："+rs.getString("w_name"));
			}else {
				addWriter(writerName);
			}
			rs = ps1.executeQuery();
			if(rs.next()) {
				ps.setString(1, bookName);
				ps.setInt(2, rs.getInt("w_id"));
				ps.setTimestamp(3,ts);
				ps.execute();
			}
			rs = ps2.executeQuery();
			if(rs.next()) {
				addBook(rs.getInt("b_id"), bookName, rs.getInt("w_id"));
			} else {
				JOptionPane.showMessageDialog(SQLManager.component,"出現問題，請聯絡管理員", "訊息", JOptionPane.INFORMATION_MESSAGE);
			}
			JOptionPane.showMessageDialog(SQLManager.component,"新增成功", "訊息", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component,"新增失敗，請重新嘗試","錯誤",JOptionPane.INFORMATION_MESSAGE);
		}	
	}

	private static void addBook(int id, String name, int w_id) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("INSERT INTO book(b_id, b_name, w_id) VALUES(?,?, ?)")) {
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, w_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void addWriter(String writerName) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("INSERT INTO writer(w_name) VALUES(?)")) {
			ps.setString(1, writerName);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String selectBook(String command) {
		StringBuffer sb = new StringBuffer("編號         書名         作者\n");
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				sb.append(resultSet.getString("b_id"));
				sb.append("                  ");
				sb.append(resultSet.getString("b_name"));
				sb.append("                 ");
				sb.append(resultSet.getString("w_name"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(sb);
	}
	
	public static String selectAllBook(String command) {
		StringBuffer sb = new StringBuffer("編號         書名         作者\n");
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				sb.append(resultSet.getString("b_id"));
				sb.append("                  ");
				sb.append(resultSet.getString("b_name"));
				sb.append("                 ");
				sb.append(resultSet.getString("w_name"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(sb);
	}
	
	public static String selectLendBook(String command) {
		StringBuffer sb = new StringBuffer("編號                  書名                              借出時間\n");
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				sb.append(resultSet.getString("b_id"));
				sb.append("                  ");
				sb.append(resultSet.getString("b_name"));
				sb.append("                 ");
				sb.append(resultSet.getString("l_time"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(sb);
	}
	
	public void deleteBook() {
		
	}
	
	public void updateBook() {
		
	}
	
	public void createTable(String tableName, String id) {
		
	}
	
	public void dropTable() {
		
	}
	
	public static void freeCommand(String sqlCommand, String... values) {
		StringBuffer sb = new StringBuffer();
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(sqlCommand)) {
			int i = 1;
			for(String v:values) {
				ps.setString(i, v);
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static String AllBook_count() {
		int maxBId =0;
		try(PreparedStatement ps = sqlConnection.prepareStatement("SELECT COUNT(*) FROM allbook")) {
			try (ResultSet resultSet = ps.executeQuery()) {
                // 如果有結果
                if (resultSet.next()) {
                    // 獲取結果中的數值
                    maxBId += resultSet.getInt(1);
                }
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"錯誤\"";
		}
		return String.valueOf(maxBId);
	}
	
	public static String book_count() {
		int maxId =0;
		try(PreparedStatement ps = sqlConnection.prepareStatement("SELECT COUNT(*) FROM book")) {
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()) {
				maxId += resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"錯誤\"";
		}
		return String.valueOf(maxId);
	}
	
	public static String lend_count() {
		int maxId = 0;
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT COUNT(*) FROM lend")) {
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				maxId += rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "\"錯誤\"";
		}
		return String.valueOf(maxId);
	}
	
	public static void lendComboBox(JComboBox comboBox) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT * FROM lend")) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name")+"。    借出時間"+rs.getTimestamp("l_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bookComboBox(JComboBox comboBox) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT a.b_name, b.w_name FROM book a, writer b WHERE a.w_id = b.w_id")) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void allBookComboBox(JComboBox comboBox) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT a.b_id, a.b_name ,b.w_name FROM allbook a, writer b")) {
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				comboBox.addItem("書名： "+resultSet.getString("b_name")+"。   作者："+resultSet.getString("w_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bookSearchComboBox(String command, JComboBox comboBox, String b_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ps.setString(1, b_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component, "找不到此書", "訊息", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void writerSearchComboBox(String command, JComboBox comboBox, String w_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ps.setString(1, w_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component, "找不到此作者的書", "訊息", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void SearchComboBox(String command, JComboBox comboBox, String b_name, String w_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ps.setString(1, b_name);
			ps.setString(2, w_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component, "無此書", "訊息", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void lendSearch(String command, JComboBox comboBox, String b_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement(command)) {
			ps.setString(1, b_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				comboBox.addItem("書名： "+rs.getString("b_name")+"。   作者："+rs.getString("w_name")+"。    借出時間："+rs.getTimestamp("l_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component, "找不到此書", "訊息", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void lendBook(String w_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("INSERT INTO lend(b_id, b_name, w_name) SELECT a.b_id, a.b_name, b.w_name FROM book a, writer b WHERE b.w_name = ? AND b.w_id = a.w_id");
			PreparedStatement ps2 = SQLManager.sqlConnection.prepareStatement("DELETE FROM book WHERE EXISTS(SELECT 1 FROM writer WHERE book.w_id = writer.w_id AND writer.w_name=?);")) {
			ps.setString(1, w_name);
			ps2.setString(1, w_name);
			ps.execute();
			ps2.execute();
			JOptionPane.showMessageDialog(component, "借用成功", "訊息", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(component, "發生錯誤");
		}
	}
	
	public static void returnBook(String b_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("INSERT INTO book(b_id, b_name,w_id) VALUES(?,?,?)");
			PreparedStatement ps1 = SQLManager.sqlConnection.prepareStatement("INSERT INTO returnbook(b_id, b_name,l_time) VALUES(?,?,?)");
			PreparedStatement ps2 = SQLManager.sqlConnection.prepareStatement("DELETE FROM lend WHERE b_name=?");
			PreparedStatement ps3 = SQLManager.sqlConnection.prepareStatement("SELECT a.*, b.l_time FROM allbook a INNER JOIN writer c ON c.w_id= a.w_id INNER JOIN lend b ON c.w_name = b.w_name WHERE a.b_name = ?")) {
			ps3.setString(1, b_name);
			ResultSet rs = ps3.executeQuery();
			if(rs.next()) {
				/*System.out.println(b_name);
				System.out.println( rs.getString("b_id"));
				System.out.println(rs.getInt("w_id"));
				System.out.println(rs.getTimestamp("l_time"));*/
				ps.setInt(1, rs.getInt("b_id"));
				ps.setString(2, rs.getString("b_name"));
				ps.setInt(3, rs.getInt("w_id"));
				ps1.setInt(1, rs.getInt("b_id"));
				ps1.setString(2, rs.getString("b_name"));
				ps1.setTimestamp(3, rs.getTimestamp("l_time"));
				ps2.setString(1, rs.getString("b_name"));
			}
			ps.execute();
			ps1.execute();
			ps2.execute();
			JOptionPane.showMessageDialog(component, "歸還完成");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(component, "歸還失敗");
		}
	}
	
	public static String selectReturn() {
		StringBuffer sb =new StringBuffer("書籍編號                 書名                          借出時間                                          歸還時間 \n");
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT * FROM returnbook")) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				sb.append("     ");
				sb.append(rs.getString("b_id"));
				sb.append("                ");
				sb.append(rs.getString("b_name"));
				sb.append("                ");
				sb.append(rs.getString("l_time"));
				sb.append("                ");
				sb.append(rs.getString("r_time"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(sb);
	}
	
	public static void breakBook(String b_name) {
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("INSERT INTO breakbook(b_id, b_name, w_id) VALUES(?,?,?)");
			PreparedStatement ps1 = SQLManager.sqlConnection.prepareStatement("DELETE FROM allbook WHERE b_id = ? LIMIT 1");
			PreparedStatement ps2 = SQLManager.sqlConnection.prepareStatement("DELETE FROM book WHERE b_id = ? LIMIT 1");
			PreparedStatement select = SQLManager.sqlConnection.prepareStatement("SELECT * FROM allbook WHERE b_name = ?")) {
			select.setString(1, b_name);
			ResultSet rs = select.executeQuery();
			if(rs.next()) {
				ps.setInt(1, rs.getInt("b_id"));
				ps.setString(2, rs.getString("b_name"));
				ps.setInt(3, rs.getInt("w_id"));
				ps1.setInt(1, rs.getInt("b_id"));
				ps2.setInt(1, rs.getInt("b_id"));
			}
			ps.execute();
			ps1.execute();
			ps2.execute();
			JOptionPane.showMessageDialog(SQLManager.component, "報廢完成");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(SQLManager.component, "報廢失敗");
		}
	}
	
	public static String selectBreak() {
		StringBuffer sb = new StringBuffer("書籍編號                 書名                          報廢時間 \n");
		try(PreparedStatement ps = SQLManager.sqlConnection.prepareStatement("SELECT * FROM breakbook")) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				sb.append("     ");
				sb.append(rs.getString("b_id"));
				sb.append("                ");
				sb.append(rs.getString("b_name"));
				sb.append("                ");
				sb.append(rs.getString("break_time"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.append("發生錯誤");
		}
		return String.valueOf(sb);
	}
}
