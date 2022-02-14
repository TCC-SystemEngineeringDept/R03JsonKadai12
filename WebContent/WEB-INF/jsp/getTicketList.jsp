<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import = "bean.Ticket,java.util.ArrayList,java.util.List" %>
<%	List<Ticket> list = (ArrayList<Ticket>)request.getAttribute("list");
	out.println("[");
	for(Ticket ticket : list){	
		out.print("{\"ID\":");
		out.print(ticket.getId());
		out.print(",\"OptName\":\"");
		out.print(ticket.getOptName());
		out.print("\"");
		out.print(",\"POINT\":");
		out.print(ticket.getPoint());
		out.println("},");
	}
	out.print("]");
%>