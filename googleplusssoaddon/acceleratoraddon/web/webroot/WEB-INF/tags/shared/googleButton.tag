<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/desktop/nav/pagination" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="googleUrl" required="false" rtexprvalue="true" %>

<c:url value="${googleUrl}" var="url" />

<a href="${url}">
	<button type="button" class="btn btn-default google">
		<i class="icon-Google_plus" aria-hidden="true"></i>&nbsp;&nbsp;	<spring:theme code="googlepluss.button.text" />
		
	</button>
</a>
