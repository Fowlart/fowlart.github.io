<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #4CAF50;
  color: white;
}
</style>
<body>
<h1>Please translate word:</h1>
<h1 align="center"><span style="color:#4CAF50;"> ${renderer.ukr_word}</span></h1>
<br/>
<font size="5" color="green" face="Arial"><details><font size="4" color="black" face="Arial">${renderer.ukr_word} - ${renderer.english_word}</font></details></font>	
<br/>
    
	<form action = "word" method = "GET">
		<input type="text" id="check_text" name ="check_text"/>
		
		<input type="submit" value="Check the word/next"/>
		
 	
		
	Filter: <select select id="selectoid" name="selectoid">
		 <option value="max">max</option>
		  <option value="min">min</option>
		 </select>
	</form>
	
<table>
  <tr>
    <th>ENG</th>
    <th>UKR</th>
	<th>RATIO</th>
  </tr>
    ${tableRenderer.tablecode}
 </table>
</body>
</html>