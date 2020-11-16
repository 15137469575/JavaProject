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

	public static void update() { // 增删改
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 加载驱动
			Class.forName(JDBC_DRIVER);
			// 与数据库建立连接
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 发送sql，执行(增删改，查)
			stmt = connection.createStatement();

			// 增删改
			String sql1 = "insert into student values('1907020318','王新明','男',201)"; // 插入
			// String sql = "update student set stu_name = '王小明' where stu_name = '王大名'";
			// //修改
			// String sql = "delete from student where stu_id = '1907020319'"; // 删除
			// 执行sql
			int count = stmt.executeUpdate(sql1); // 返回值表示增删改几条数据
			// 处理结果
			if (count > 0) {
				System.out.println("操作成功");
			} else {
				System.out.println("操作失败");
			}

			// 查
			String sql = "select stu_id,stu_name,sex,age from student";
			// 执行
			rs = stmt.executeQuery(sql);
			// 处理结果
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