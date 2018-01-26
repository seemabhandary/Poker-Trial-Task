<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>POKER GAME</title>

</head>
<body>
<form action="process.jsp" method="post" name="pokerForm">
<TABLE id="pokerTable" border="1">
<TR><TD> Players </TD> <TD colspan="5" align="center"> CARDS </TD></TR>

<TR>
	<TD>	1																		</TD> 
	<TD>	<input name="Player1Input1" type="text" size ="5" required maxlength="3">	</TD>
	<TD>	<input name="Player1Input2" type="text" size ="5" required maxlength="3">	</TD>
	<TD>	<input name="Player1Input3" type="text" size ="5" required maxlength="3">	</TD>
    <TD>	<input name="Player1Input4" type="text" size ="5" required maxlength="3">	</TD>
    <TD>	<input name="Player1Input5" type="text" size ="5" required maxlength="3">	</TD>
</TR>       

<TR>
	<TD>	2 																		</TD>
	<TD>	<input name="Player2Input1" type="text" size ="5" required maxlength="3">	</TD>
	<TD>  	<input name="Player2Input2" type="text" size ="5" required maxlength="3">	</TD>
	<TD>  	<input name="Player2Input3" type="text" size ="5" required maxlength="3">	</TD>
    <TD>  	<input name="Player2Input4" type="text" size ="5" required maxlength="3">	</TD>
    <TD>   	<input name="Player2Input5" type="text" size ="5" required maxlength="3">	</TD>
</TR>
</TABLE>
<INPUT type="submit" value="Play !">
</form>
</body>
</html>