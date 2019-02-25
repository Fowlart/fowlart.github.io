<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="models.*"%>
<!DOCTYPE html>
<html>
   <body>
      <form action = "HelloForm" method = "GET">
         Search: <input type = "text" name = "search_text">
         <br />
		 Filter: <select select id="selectoid" name="selectoid">
		 <option value="min">min</option>
		 <option value="max">max</option>
		 <option value="avg">avg</option>
		 <option value="all">all</option>
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

<h2>Colored Table Header</h2>
<table>
  <tr>
    <th>Goods name</th>
    <th>price</th>
  </tr>
  <%HelloJspRenderer.getTableCode();%>
   </table>
	   
	  </body>
</html>