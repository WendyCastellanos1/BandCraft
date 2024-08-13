<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Member: ${memberKey.firstName} ${memberKey.lastName}</h1>
        </div>
    </div>
</section>

<!-- member detail -->
<section>
    <div class="container">
        <div class="table">
            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <tr><td><b>Member Id</b></td>            <td>${memberKey.id}</td></tr>
                        <tr><td><b>Last Name</b></td>            <td>${memberKey.lastName}</td></tr>
                        <tr><td><b>First Name</b></td>           <td>${memberKey.firstName}</td></tr>
                        <tr><td><b>Nickname</b></td>             <td>${memberKey.nickname}</td></tr>

                        <tr><td><b>Gender</b></td>
                                <c:choose>
                                    <c:when test="${memberKey.gender eq 'm'}">
                                        <td>Male</td>
                                    </c:when>
                                    <c:when test="${memberKey.gender eq 'f'}">
                                       <td>Female</td>
                                    </c:when>
                                    <c:when test="${memberKey.gender eq 'o'}">
                                        <td>Other</td>
                                    </c:when>
                                    <c:otherwise>
                                       <td>Unknown</td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>

                        <tr><td><b>Gender Comment</b></td>       <td>${memberKey.genderComment}</td></tr>

                        <tr><td><b>Generation</b></td>
                                <c:choose>
                                    <c:when test="${memberKey.generation eq 'a'}">
                                        <td>Alpha</td>
                                    </c:when>
                                    <c:when test="${memberKey.generation eq 'z'}">
                                        <td>Gen Z</td>
                                    </c:when>
                                    <c:when test="${memberKey.generation eq 'm'}">
                                        <td>Millenials</td>
                                    </c:when>
                                    <c:when test="${memberKey.generation eq 'x'}">
                                        <td>Gen X</td>
                                    </c:when>
                                    <c:when test="${memberKey.generation eq 'b'}">
                                        <td>Boomer</td>
                                    </c:when>
                                    <c:when test="${memberKey.generation eq 's'}">
                                        <td>Silent Generation</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Unknown</td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>

                        <tr><td><b>Cell Phone</b></td>           <td>${memberKey.phoneCell}</td></tr>
                        <tr><td><b>Alt Phone</b></td>            <td>${memberKey.phoneAlt}</td></tr>
                        <tr><td><b>Alt Email</b></td>            <td>${memberKey.emailAlt}</td></tr>

                        <tr><td><b>Speaks Spanish</b></td>
                                <c:choose>
                                    <c:when test="${memberKey.speaksSpanish}">
                                        <td style="color:seagreen"><b>Yes</b></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="color:red">No</td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>

                        <tr><td><b>Speaks Portuguese</b></td>
                            <c:choose>
                                <c:when test="${memberKey.speaksPortuguese}">
                                    <td style="color:seagreen"><b>Yes</b></td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color:red">No</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>

                        <tr><td><b>Bio</b></td>                  <td>${memberKey.bio}</td></tr>
                        <tr><td><b>Social Media Url</b></td>     <td>${memberKey.socialMediaUrl}</td></tr>
                        <tr><td><b>Date Created</b></td>         <td>${memberKey.dateCreated}</td></tr>
                        <tr><td><b>Date Updated</b></td>         <td>${memberKey.dateUpdated}</td></tr>
                        <tr><td><b>Last Updated Id</b></td>       <td>${memberKey.lastUpdatedId}</td></tr>

                        <tr style="background-color: lightgreen">     <td>     <td>      </td></tr>

                        <!-- list of talents for this member -->
                        <tr style="color:seagreen"><td><b><h4>Member Talents:  </h4></b></td>  <td><b><h4>${memberTalentsKey.size()} result(s)</h4></b></td></tr>
                        <tr>
                            <section>
                                <div class="container">
                                    <div class="row">
                                        <div class ="col-12">
                                            <table class="table">
                                                <tr>
                                                    <th><b>Id</b></th>
                                                    <th><b>Talent Name</b></th>
                                                    <th><b>Talent Description</b></th>
<%--                                                    <th><b>Date Created</b></th>--%>
<%--                                                    <th><b>Date Updated</b></th>--%>
<%--                                                    <th><b>Last Updated Id</b></th>--%>
                                                </tr>

                                                <c:forEach items="${memberTalentsKey}" var="talent">
                                                    <tr>
                                                        <td>${talent.id}</td>
                                                        <td>${talent.name}</td>
                                                        <td>${talent.description}</td>
<%--                                                        <td>${talent.dateCreated}</td>--%>
<%--                                                        <td>${talent.dateUpdated}</td>--%>
<%--                                                        <td>${talent.lastUpdatedId}</td>--%>
                                                    </tr>
                                                </c:forEach>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </section>

                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>

