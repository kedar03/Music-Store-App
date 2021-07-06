<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Listen Track Samples</title>
</head>
<body>
<c:if test="${empty user}">
  <jsp:forward page="userWelcome.jsp"/>
</c:if>

<c:if test="${empty user.user}">
  <jsp:forward page="register.jsp"/>
</c:if>
<c:set target="${user}" property="productCode" value="${param.prodCode}"/> 
<h3>Listen Track Samples</h3>
<h4>Description: ${user.product.description}</h4>
Price: $${user.product.price}
<br>
<br>
 <table>
    <tr>
      <td width="300"><b>Title</b></td>
    </tr>
      
    <c:forEach var="track" items="${user.product.tracks}">
    <tr>
      <td>${track.title}</td>
      <c:url var="download" value="downloadTrack.jsp">
      	<c:param name="trackNum" value="${track.trackNumber}"/>
      	<c:param name="prodCode" value="${user.product.code}"/>
      	<c:param name="sampleFileName" value="${track.sampleFilename}"/>
      </c:url>
      <td width="50"><a href="${download}">Play</a></td>
    </tr>
    </c:forEach>
 
  </table>
<br>
	<c:url var="cart" value="cart.jsp">
		<c:param name="prodCode" value="${user.productCode}"/>
		<c:param name="quantity" value="1"/>
		<c:param name="cmd" value="add"/>
	</c:url>
 	<a href="${cart}"> Add to cart</a>
	<br>
	<c:url var="Cart" value="cart.jsp"/>
	<a href="${Cart}"> Show Cart</a>
	<br>
	<c:url var="catalog" value="catalog.jsp"/>
	<a href="${catalog}">Browse Catalog</a>
	<br>
	<c:url var="userWelcome" value="userWelcome.jsp"/>
	<a href="${userWelcome}"> Back to User Homepage</a>
</body>
</html>
