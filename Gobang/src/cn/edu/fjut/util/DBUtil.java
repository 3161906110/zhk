package cn.edu.fjut.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBUtil {
	static final String DB_URL = "jdbc:mysql://localhost:3306/Gobang";
	static final String USER = "root";
	static final String PASS = "123456";
	public static Connection getConn() throws SQLException {
		// 加载驱动(可以省略)
		return (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

	}
	//更新操作
	public static boolean execUpdate(String sql, Object... params) {
		Connection conn = null;
		int n = 0;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			// 通过connection对象得到PreparedStatement
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// 给?赋值
			setPreparedStatement(pstmt, params);
			// 要执行sql
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放资源
			closeAll(null, pstmt, conn);
		}

		return n > 0;
	}
	
	//查询操作
	public static Object executeQuery(String sql, Class cls, Object... params) {
		List<Object> list = new ArrayList();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			setPreparedStatement(pstmt, params);
			// ④ 执行查询
			rs = pstmt.executeQuery();

			// 结果集如何变成某个类的对象集合
			// rs ->emp 的集合 list<Emp>
			// rs ->dept 的集合 list<Dept>

			// 需要将查询结果进行处理
			while (rs.next()) {
				// 不确定查询的是哪张表
				// 需要将查询的结果 和 列对应在一起
				// empNo ->A101
				// empName -> 志军
				Object obj = convert(rs, cls);
				// 将obj存储到集合中
				list.add(obj);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return list;
	}
	
	//处理结果，返回类实例
	public static Object convert(ResultSet rs, Class cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();

			// 处理输出结果集
			ResultSetMetaData metaData = rs.getMetaData();
			int columncount = metaData.getColumnCount();
			// System.out.println("columnCount :" + columncount);

			for (int i = 1; i <= columncount; i++) {
				String columnLabel = metaData.getColumnLabel(i);
				// System.out.print(columnLabel + "==>");
				// 根据列名得到列的值
				// System.out.println(rs.getObject(columnLabel));

				// 将得到信息 赋值给emp/dept/user对象
				// =>obj
				// 赋值给obj就行了，可是怎么弄？ -列名columnLabel，其实也是 "属性名" , rs.getObject(columnLabel) ->属性的值
					//System.out.println(rs.getObject(columnLabel));
				BeanUtils.setProperty(obj, columnLabel, rs.getObject(i));
                
			}

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}

	
	
	
	
	public static void setPreparedStatement(PreparedStatement pstmt,Object... params) {
		if (null != params && null != pstmt) {
			for (int i = 0; i < params.length; i++) {
				try {
					pstmt.setObject(i + 1, params[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {

		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}