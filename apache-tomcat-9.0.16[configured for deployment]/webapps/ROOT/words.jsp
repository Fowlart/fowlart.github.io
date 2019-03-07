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
<h1 align="center"><span style="color:#FFFF00;"> більш-менш</span></h1>
<h1 align="center"><span style="color:#FF0000;"> погано</span></h1>
<h1 align="center"><span style="color:#000000;"> нейтральне слово</span></h1>
    <form action = "word" method = "GET">
        <br/> <button type="submit">Check the word/next</button>
		<input type="text" spellcheck="true">
		
	<br />
	Filter: <select select id="selectoid" name="selectoid">
		 <option value="all">last seen</option>
		 <option value="all">all</option>
		 <option value="min">min</option>
		 <option value="max">medium</option>
		 <option value="avg">max</option>
		 </select>
	</form>
<h2>Table of values</h2>
<table>
  <tr>
    <th>ENG</th>
    <th>UKR</th>
	<th>RATIO</th>
  </tr>
      
 </table>
</body>
</html>