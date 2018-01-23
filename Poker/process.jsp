<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.poker.task.*, java.util.*" %>

<%

Map<String,String[]> mp = request.getParameterMap();
/*
Enumeration<String> paramNames = request.getParameterNames();

while(paramNames.hasMoreElements())
{
	System.out.println(paramNames.nextElement());
}



System.out.println("map -->"+paramNames.toString());
 */
 for (Map.Entry<String,String[]> entry : mp.entrySet()) 
    System.out.println("Key = " + entry.getKey() +
                     ", Value = " + entry.getValue()); 


List<String> lstHand1 =  new ArrayList<String>(5);
lstHand1.add(request.getParameter("Hand1Input1"));
lstHand1.add(request.getParameter("Hand1Input2"));
lstHand1.add(request.getParameter("Hand1Input3"));
lstHand1.add(request.getParameter("Hand1Input4"));
lstHand1.add(request.getParameter("Hand1Input5"));


List<String> lstHand2 =  new ArrayList<String>(5);
lstHand2.add(request.getParameter("Hand2Input1"));
lstHand2.add(request.getParameter("Hand2Input2"));
lstHand2.add(request.getParameter("Hand2Input3"));
lstHand2.add(request.getParameter("Hand2Input4"));
lstHand2.add(request.getParameter("Hand2Input5"));




String winningHand = PokerUtil.play(request.getParameterMap());

System.out.println(winningHand);


%>

<%=winningHand%>

</body>
</html>