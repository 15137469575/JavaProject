import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/SMS?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	public static void update() { // ��ɾ��
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// ��������
			Class.forName(JDBC_DRIVER);
			// �����ݿ⽨������
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// ����sql��ִ��(��ɾ�ģ���)
			stmt = connection.createStatement();

			// ��ɾ��
			String sql1 = "insert into student values('1907020318','������','��',201)"; // ����
			// String sql = "update student set stu_name = '��С��' where stu_name = '������'";
			// //�޸�
			// String sql = "delete from student where stu_id = '1907020319'"; // ɾ��
			// ִ��sql
			int count = stmt.executeUpdate(sql1); // ����ֵ��ʾ��ɾ�ļ�������
			// ������
			if (count > 0) {
				System.out.println("�����ɹ�");
			} else {
				System.out.println("����ʧ��");
			}

			// ��
			String sql = "select stu_id,stu_name,sex,age from student";
			// ִ��
			rs = stmt.executeQuery(sql);
			// ������
			while (rs.next()) {
				int _age = rs.getInt("age");
				String _name = rs.getString("stu_name");
				String _id = rs.getString("stu_id");
				String _sex = rs.getString("sex");
				System.out.print(_id + "---" + _name + "---" + _sex + "---" + _age);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		update();
	}
}