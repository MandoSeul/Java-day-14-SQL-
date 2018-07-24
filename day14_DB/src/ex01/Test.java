package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//DBMS에 접속하기 위해서는 어떤 정보가 필요?
//DBMS의 IP주소와 port
//창고관리인에게 접속하기 위한 아이디 비번
public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "scott";
		String password = "tiger";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 생략해도됨 알아서 해줌libraryimport되어있으면
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
			System.out.println("숫자를 입력하시오 : ");
			int num = Integer.parseInt(sc.nextLine());
			System.out.println("문자열을 입력하시오 : ");
			String temp = sc.nextLine();
			//String sql = "INSERT INTO test VALUES(10, 'seul~~')"; // 이걸 database에 날려주는 것
			//즉 한만큼 database에 들어감
			//문자열 상태로 변환?
//			String sql = "INSERT INTO test VALUES("+ num+","+ temp+")"; 
//			Statement stmt = conn.createStatement();
//			int result = stmt.executeUpdate(sql);
			String sql = "INSERT INTO test VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2,temp);
			int result = pstmt.executeUpdate();
			System.out.println(result + "개의 행이 영향 받았습니다.");
			//stmt를 날리는 종류 -> executeUpdate:DML, executeQuery:DQL ,execute :DDL
			//DML은 결과가 영향을 받은 레코드의 갯수
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("연결 완료");
	}
}
