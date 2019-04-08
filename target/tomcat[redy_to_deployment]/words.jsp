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
.day-forecast {
	margin: 0;
	padding: .3rem;
	background-color: #eee;
	font: 1rem 'Fira Sans', sans-serif;
}

</style>

<body>
<h1><span style="color:#4CAF50;">Please translate word: ${renderer.ukr_word}</span></h1>

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


<article class="day-forecast">
	<h2>file last modified</h2>
	<p>${file}</p>
</article>
<br/>
<article class="day-forecast">
	<h2>total words count</h2>
	<p>${count_of_words}</p>
</article>
<br/>
<article class="day-forecast">
	<h2>avarage point</h2>
	<p>${avg_point}</p>
</article>
<br/>
<article class="day-forecast">
	<h2>current progress</h2>
	<p>${progress}</p>
</article>
<br/>

<br/>

    ${tableRenderer.tablecode}
</body>
</html>