<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1>
                <c:if test="${empty form.id}">
                    <h1 class="text-center">Choose Member Talents</h1>
                </c:if>
                <c:if test="${not empty form.id}">
                    <h1 class="text-center">Edit Member Talents</h1>
                </c:if>
            </h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5 pb-5">
            <div class="col-12">
                <form action="../member-talent/createSubmit" method="get">
                    <input type="hidden" name="id" value="${memberIdKey}">  <!-- the member id  -->
                    <h3 style="text-align: center" >Member Id ${memberIdKey}:  Select the talents you are willing to use in the band</h3>
                    <h4 class="text-center">${talentsKey.size()} result(s)</h4>
                </form>
            </div>
        </div>
        <div class="row">
            <div class ="col-12">
                <table class="table">
                    <tr>
                        <th><b>Id</b></th>
                        <th><b>Name</b></th>
                        <th><b>Description</b></th>
                        <!-- <th><b>Order of Preference</b></th> -->
                        <th><b>Status</b></th>
                        <th><b>Links</b></th>

                    </tr>

                    <c:forEach items="${talentsKey}" var="talent">
                        <tr>
                            <td>${talent.id}</td>
                            <td>${talent.name}</td>
                            <td>${talent.description}</td>
                            <!-- TODO order of preference functionality -->

                            <c:choose>
                                <c:when test="${talent.isMapped}">
                                    <td>already selected</td>
                                    <td>----------</td>
                                </c:when>
                                <c:otherwise>
                                    <td>available</td>
                                    <td><a href="../member-talent/createSubmit?memberId=${memberIdKey}&talentId=${talent.id}">Add Talent</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</section>

