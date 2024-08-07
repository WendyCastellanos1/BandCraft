<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Error 404</h1>
        </div>
    </div>
</section>



<div class="container">
    <section>

        <div class="row text-center">
            <h1 class="page-title">Page not found.</h1>
        </div>

        <div class="row justify-content-center">

            <div class="col col-6"></div>

        </div>
    </section>


<section>
    <sec:authorize access="hasAnyAuthority('ADMIN')">
        <div style="margin-left:30px;" class="pb-5">
            <br><br>
            <c:if test="${not empty requestUrl}">
                <p>${requestUrl}</p>
            </c:if>
            <h3>Stack Trace</h3>
            <c:if test="${not empty message}">
                <p>${message}</p>
            </c:if>
            <c:if test="${not empty stackTrace}">
                <p>${stackTrace}</p>
            </c:if>
            <c:if test="${not empty rootCause}">
                <h3>Root Cause</h3>
                <p>${rootCause}</p>
            </c:if>
            <c:if test="${not empty rootTrace}">
                <p>${rootTrace}</p>
            </c:if>
        </div>
    </sec:authorize>
</section>


<jsp:include page="../include/footer.jsp"/>