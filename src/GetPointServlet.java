

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



/**
 * Servlet implementation class GetPointServlet
 */
@WebServlet("/getPoint")
public class GetPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPointServlet() {
        super();
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String tenpo_Id = request.getParameter("TENPO_ID");
		String user_Id = request.getParameter("USER_ID");
		
		try {
			InitialContext 	ic 	= new InitialContext();	
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/TEAM12");
			Connection con = (Connection) ds.getConnection();

			String sql = "SELECT tenpo_code,riyousya_ID,point_code FROM point_hyou WHERE tenpo_code = ? AND riyousya_ID = ?";
			
			PreparedStatement st =  con.prepareStatement(sql);
			st.setString(1,tenpo_Id);
			st.setString(2, user_Id);
			ResultSet rs = st.executeQuery();
			
			int point= 0;
			int line = 0;
			while(rs.next()) {
				line++;
				point = rs.getInt("point_code");
			}
			
			if(line==0) {
				sql = "INSERT INTO point_hyou(tenpo_code,riyousya_ID,point_code) VALUES(?,?,500)";
				st = con.prepareStatement(sql);
				st.setString(1,tenpo_Id);
				st.setString(2,user_Id);
				point = 500;
				st.executeUpdate();
			}
			
			request.setAttribute("point", point);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getPoint.jsp");
			rd.forward(request, response);
			
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}	
		


