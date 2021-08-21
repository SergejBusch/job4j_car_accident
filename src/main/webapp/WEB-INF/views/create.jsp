<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Save" /></td>
        </tr>
    </table>
</form>
</body>
</html>
