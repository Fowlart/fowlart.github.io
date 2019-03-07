<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <body>
      <form action = "hello" method = "GET">
         Search: <input type = "text" name = "search_text">
         <br />
		 Filter: <select select id="selectoid" name="selectoid">
		 <option value="all">all</option>
		 <option value="min">min</option>
		 <option value="max">max</option>
		 <option value="avg">avg</option>
		 </select>
		 <br />
         <input type = "submit" value = "Submit" />
      </form>
	  
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
</head>
<body>

<h2>Table of values</h2>
<table>
  <tr>
    <th>1st value</th>
    <th>2nd value</th>
  </tr>
   
   ${renderer.tablecode}
 
   </table>
	   
	  </body>
</html>