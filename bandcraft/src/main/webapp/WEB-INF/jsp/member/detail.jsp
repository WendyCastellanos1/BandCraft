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
                        <tr><td><b>Gender</b></td>               <td>${memberKey.gender}</td></tr>
                        <tr><td><b>Gender Comment</b></td>       <td>${memberKey.genderComment}</td></tr>
                        <tr><td><b>Generation</b></td>           <td>${memberKey.generation}</td></tr>
                        <tr><td><b>Cell Phone</b></td>           <td>${memberKey.phoneCell}</td></tr>
                        <tr><td><b>Alt Phone</b></td>            <td>${memberKey.phoneAlt}</td></tr>
                        <tr><td><b>Alt Email</b></td>            <td>${memberKey.emailAlt}</td></tr>
                        <tr><td><b>Speaks Spanish</b></td>       <td>${memberKey.speaksSpanish}</td></tr>
                        <tr><td><b>Speaks Portuguese</b></td>    <td>${memberKey.speaksPortuguese}</td></tr>
                        <tr><td><b>Bio</b></td>                  <td>${memberKey.bio}</td></tr>
                        <tr><td><b>Social Media Url</b></td>     <td>${memberKey.socialMediaUrl}</td></tr>
                        <tr><td><b>Date Created</b></td>         <td>${memberKey.dateCreated}</td></tr>
                        <tr><td><b>Date Updated</b></td>         <td>${memberKey.dateUpdated}</td></tr>
                        <tr><td><b>Last Updated Id</b></td>       <td>${memberKey.lastUpdatedId}</td></tr>

                        <!-- list of talents for this member -->
                        <tr style="color:seagreen"><td ><b>Member Talents:  </b></td>  <td>${memberTalentsKey.size()} result(s)</td></tr>
                        <tr>
                            <section>
                                <div class="container">
                                    <div class="row">
                                        <div class ="col-12">
                                            <table class="table">
                                                <tr>
                                                    <th><b>Id</b></th>
                                                    <!--       <th><b>Talent Id</b></th>  -->
                                                    <!--        <th><b>Talent Name</b></th>   -->
                                                    <th><b>Date Created</b></th>
                                                    <th><b>Date Updated</b></th>
                                                    <th><b>Last Updated Id</b></th>
                                                </tr>

                                                <c:forEach items="${memberTalentsKey}" var="memberTalent">
                                                    <tr>
                                                        <td>${memberTalent.id}</td>
<%--                                                 <!--   <td>${memberTalent.talentId}</td>   -->--%>
<%--                                                 <!--        <td>${memberTalent.talentName}</td>    -->--%>
                                                        <td>${memberTalent.dateCreated}</td>
                                                        <td>${memberTalent.dateUpdated}</td>
                                                        <td>${memberTalent.lastUpdatedId}</td>
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

