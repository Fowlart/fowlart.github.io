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
<h1><span style="color:#4CAF50;">Please translate word:</span></h1>
<h4>file last modified: ${file}</h4>
<h4>words count: ${count_of_words}/1000</h4>
<h4>total points: ${total_points}</h4>
<h4>avarage point: ${avg_point}</h4>
<h4>current progress: ${progress} %</h4>

<h1 align="center"><span style="color:#4CAF50;"> ${renderer.ukr_word}</span></h1>
<br/>
<font size="5" color="green" face="Arial"><details><font size="4" color="black" face="Arial">${renderer.ukr_word} - ${renderer.english_word}</font></details></font>	
<br/>
    
	<form action = "word" method = "GET">
		<input type="text" id="check_text" name ="check_text" autocomplete="off"/>
		
		<input type="submit" value="Check the word/show table"/>
		
	Filter: <select select id="selectoid" name="selectoid">
		 <option value="sort">sort</option>
		  <option value="import">import</option>
		 </select>
	</form>
    ${tableRenderer.tablecode}
</body>
</html>