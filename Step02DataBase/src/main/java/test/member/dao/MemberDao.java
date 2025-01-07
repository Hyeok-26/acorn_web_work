package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	//회원 한명의 전보를 추가하는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn=new DbcpBean().getConn();
			//실행할 미완성의sql문
			String sql="""
					INSERT INTO member
					(num, name, addr)
					values(member_seq.NEXTVAL, ?, ?)
					""";
			pstmt=conn.prepareStatement(sql);
			//? 에 값을 바인딩
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			//sql 문 실행하고 변화된 row 의 갯수 리턴
			rowCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}
		if(rowCount > 0) {
			return true;
		}else {
			return false;
		}
	}//insert() 메소드
	//회원 한 명의 정보를 삭제하고 성공 여부를 리턴하는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
					delete from member
					where num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setInt(1, num);
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	//회원 한 명의 정보를 수정하고 성공 여부를 리턴하는 메소드
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 미완성의 sql 문
			String sql = """
					update member
					set name = ?,addr = ?
					where num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값을 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			// sql 문 실행하고 변화된 row 의 갯수 리턴받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<MemberDto>getList(){
		List<MemberDto>list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql문 작성
			String sql="""
					SELECT num, name, addr
					from member
					ORDER BY num ASC
					""";
			pstmt = conn.prepareStatement(sql);
			//? 에 값 바인딩할게 있으면 여기서 하기
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//회원 한명의 정보가 담긴 dto 를 List 객체에 누적 시키기
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
			}catch (Exception e) {}
		}
		//List 객체 리턴
		return list;
	}//getlist()
}//class
