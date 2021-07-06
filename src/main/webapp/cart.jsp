<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/sidebar.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section>

<h1>Welcome to Cart </h1>
<h2>The added items are:-</h2>
		<table border=1>
			<tr>
  			  <th>ProductId</th>
    			<th>ProductQuantity</th> 
  			</tr>
  			<tr>
    			<td>${productId}</td>
    			<td>${productQuantity}</td> 
  			</tr>
  		</table>

<c:if test = "{!empty allProducts}">
<c:forEach items="${allProducts}" var="ap">
          <table border=1>
      <tr>
          <th>ProductId</th>
          <th>ProductQuantity</th> 
        </tr>
        <tr>
          <td>${ap.productId}</td>
          <td>${ap.productQuantity}</td> 
        </tr>
      </table>  
      </c:forEach>
</c:if>

	<br>
<p><a href="catalog.html"> continue shopping!</a></p>
<p><a href="beginCheckout.html">Checkout</a></p>

<jsp:include page="/includes/footer.jsp" />
