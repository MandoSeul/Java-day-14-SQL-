package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//DBMS�� �����ϱ� ���ؼ��� � ������ �ʿ�?
//DBMS�� IP�ּҿ� port
//â������ο��� �����ϱ� ���� ���̵� ���
public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // �����ص��� �˾Ƽ� ����libraryimport�Ǿ�������
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
			System.out.println("���ڸ� �Է��Ͻÿ� : ");
			int num = Integer.parseInt(sc.nextLine());
			System.out.println("���ڿ��� �Է��Ͻÿ� : ");
			String temp = sc.nextLine();
			//String sql = "INSERT INTO test VALUES(10, 'seul~~')"; // �̰� database�� �����ִ� ��
			//�� �Ѹ�ŭ database�� ��
			//���ڿ� ���·� ��ȯ?
//			String sql = "INSERT INTO test VALUES("+ num+","+ temp+")"; 
//			Statement stmt = conn.createStatement();
//			int result = stmt.executeUpdate(sql);
			String sql = "INSERT INTO test VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,temp);
			int result = pstmt.executeUpdate();
			System.out.println(result + "���� ���� ���� �޾ҽ��ϴ�.");
			//stmt�� ������ ���� -> executeUpdate:DML, executeQuery:DQL ,execute :DDL
			//DML�� ����� ������ ���� ���ڵ��� ����
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���� �Ϸ�");
	}
}
