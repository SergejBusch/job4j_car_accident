<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form  action="<c:url value='/update?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <td>Text:</td>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <td>Address:</td>
            <td><input type='text' name='address' value="${accident.address}"></td>
            <td>Accident type:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>Rules:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Update" /></td>
        </tr>
    </table>
</form>
</body>
</html>