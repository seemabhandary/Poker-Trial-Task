<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/poker.js"></script>
</head>
<body>
<form action="process.jsp" method="post">
<TABLE id="pokerTable" width="350px" border="1">
<TR><TD> Players </TD> <TD colspan="5" align="center"> CARDS </TD></TR>
<TR><TD>	1	</TD> 
	<TD>	<input name="Hand1Input1" type="text" size ="5" value="D5">	</TD>
	<TD>	<input name="Hand1Input2" type="text" size ="5" value="C7">	</TD>	
	<TD>	<input name="Hand1Input3" type="text" size ="5" value="H6">	</TD>	
    <TD>	<input name="Hand1Input4" type="text" size ="5" value="S8">	</TD>	
    <TD>	<input name="Hand1Input5" type="text" size ="5" value="D9">	</TD>	
</TR>       

<TR><TD>	2 </TD>
	<TD>	<input name="Hand2Input1" type="text" size ="5" value="HK">	</TD>	
	<TD>  	<input name="Hand2Input2" type="text" size ="5"	value="H10">	</TD>	
	<TD>  	<input name="Hand2Input3" type="text" size ="5"	value="HQ">	</TD>	
    <TD>  	<input name="Hand2Input4" type="text" size ="5" value="H9">	</TD>	
    <TD>   	<input name="Hand2Input5" type="text" size ="5" value="HJ">	</TD>	
</TR>

</TABLE>
<INPUT type="button" value="Add Players !" onclick="addPlayer('pokerTable')" />
<INPUT align = "middle" type="submit" value="Play !">
</form>
</body>
</html>