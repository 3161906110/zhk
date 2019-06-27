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
		// ��������(����ʡ��)
		return (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

	}
	//���²���
	public static boolean execUpdate(String sql, Object... params) {
		Connection conn = null;
		int n = 0;
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			// ͨ��connection����õ�PreparedStatement
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// ��?��ֵ
			setPreparedStatement(pstmt, params);
			// Ҫִ��sql
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ͷ���Դ
			closeAll(null, pstmt, conn);
		}

		return n > 0;
	}
	
	//��ѯ����
	public static Object executeQuery(String sql, Class cls, Object... params) {
		List<Object> list = new ArrayList();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			setPreparedStatement(pstmt, params);
			// �� ִ�в�ѯ
			rs = pstmt.executeQuery();

			// �������α��ĳ����Ķ��󼯺�
			// rs ->emp �ļ��� list<Emp>
			// rs ->dept �ļ��� list<Dept>

			// ��Ҫ����ѯ������д���
			while (rs.next()) {
				// ��ȷ����ѯ�������ű�
				// ��Ҫ����ѯ�Ľ�� �� �ж�Ӧ��һ��
				// empNo ->A101
				// empName -> ־��
				Object obj = convert(rs, cls);
				// ��obj�洢��������
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
	
	//��������������ʵ��
	public static Object convert(ResultSet rs, Class cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();

			// ������������
			ResultSetMetaData metaData = rs.getMetaData();
			int columncount = metaData.getColumnCount();
			// System.out.println("columnCount :" + columncount);

			for (int i = 1; i <= columncount; i++) {
				String columnLabel = metaData.getColumnLabel(i);
				// System.out.print(columnLabel + "==>");
				// ���������õ��е�ֵ
				// System.out.println(rs.getObject(columnLabel));

				// ���õ���Ϣ ��ֵ��emp/dept/user����
				// =>obj
				// ��ֵ��obj�����ˣ�������ôŪ�� -����columnLabel����ʵҲ�� "������" , rs.getObject(columnLabel) ->���Ե�ֵ
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