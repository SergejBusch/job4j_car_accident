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
            <td><input type='text' name='name' required></td>
        </tr>
        <tr>
            <td>Type:</td>
            <td>
                <select name="type.id" required>
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Rules:</td>
            <td>
                <select name="rIds" multiple required>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Save" /></td>
        </tr>
    </table>
</form>
</body>
</html>
