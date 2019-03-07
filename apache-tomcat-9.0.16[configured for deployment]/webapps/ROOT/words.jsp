<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<h1></span><span style="color:#ff1900;">Please translate word:</span></h1>
<h3> ${renderer.english_word}</h3>
    <form action = "word" method = "GET">
        <br/> <button type="submit">Submit</button><input type="text" spellcheck="true">
    </form>
</body>
</html>