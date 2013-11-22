<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../Teamspeak/templatemo_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../Teamspeak/css/coda-slider.css" type="text/css" charset="utf-8" />
<title>Assigning Manager</title>
</head>
<body>
	<div id="templatemo_wrapper">
	<div id="templatemo_top">
    </div>
    
	<div id="header">
            
            	<div id="sitetite">
            	</div>
            
                <ul class="navigation">
                    <li><a href="../Teamspeak/login.jsp">Login to get Teamspeak ID</a></li>
                    <li><a href="../Teamspeak/assign.jsp">Manage Field Ranks</a></li>
     			 </ul>
                
		  </div>
		  <div class="scroll">
      <div class="scrollContainer">
        <div class="panel" id="home">
        
          <h2>Manage Ranks Here</h2>
          <form method="POST" action='Controller' name="input">
	username:
    <input type="text" name="username">
    <br/>
			Assign rank:<select name="rank" id="dropdown">
                <option value="dropdown"></option>
                <option value="Private">Private</option>
                <option value="Corporal">Corporal</option>
                <option value="Sergeant">Sergeant</option>
                <option value="Staff Sergeant">Staff Sergeant</option>
                <option value="Master Sergeant">Master Sergeant</option>
                <option value="Sergeant Major">Sergeant Major</option>
            </select>
            <br/>
   Master password: <input type="password" name="password"><br/>
   Output current list: <input TYPE=checkbox name="output" VALUE="output"><br/>
    <input type="submit" value="submit"> <input type="reset" value="reset">
    </form>
          
          <div class="cleaner_h40"></div>
          
          
        </div>
        
</div>
    <div id="templatemo_footer">
		Designed by WNxJango Fett and WNxSgtCanadian.
	 </div>
</body>
</html>