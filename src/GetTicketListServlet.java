

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Ticket;
/**
 * Servlet implementation class GetPointServlet
 */
@WebServlet("/getTicketList")
public class GetTicketListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTicketListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
			InitialContext 	ic 	= new InitialContext();	
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/TEAM12");	
			Connection con = ds.getConnection();
			
			String user_Id = request.getParameter("USER_ID");
			String tenpo_Id = request.getParameter("TENPO_ID");
		
			
			String sql1 = "SELECT Point_code FROM point_hyou WHERE tenpo_code = ? AND riyousya_ID = ?";	
			PreparedStatement st = con.prepareStatement(sql1);
			st.setString(1,tenpo_Id);
			st.setString(2, user_Id);
			ResultSet rsPoint = st.executeQuery();
			
			int userPoint= 0;
			while(rsPoint.next()) {
				userPoint = rsPoint.getInt("Point_code");
			}
			
			String sql2 = "SELECT tenpo_code,Ticket_ID,Ticket_Name,Point_code FROM allticket_hyou WHERE tenpo_code = ? AND point_code <= ?";	//ユーザが使用することができるチケットを取得			
			st = con.prepareStatement(sql2);
			st.setString(1, tenpo_Id);
			st.setInt(2, userPoint);
			ResultSet rsTicket = st.executeQuery();
			
			List<Ticket> list = new ArrayList<Ticket>();  
			while(rsTicket.next()) {
				int storeId = rsTicket.getInt("Ticket_ID");
				String optName= rsTicket.getString("Ticket_Name");
				int point = rsTicket.getInt("point_code");
				list.add(new Ticket(storeId,optName,point));
			}
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/getTicketList.jsp");
			rd.forward(request, response);
			
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
