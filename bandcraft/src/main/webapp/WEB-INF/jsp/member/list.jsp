<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Members</h1>
        </div>
    </div>
</section>

<!-- count of members in the list; section>container>one row>one column w/h4; another row w/column headers; x row(s) with members -->
<section>
    <div class="container">
        <div class="row pt-5 pb-5">
            <div class="col-12">
                <h4 class="text-center"> ${membersKey.size()} result(s)</h4>
            </div>
        </div>
        <div class="row">
            <div class ="col-12">
                <table class="table">
                    <tr>
                        <th><b>Id</b></th>
                        <th><b>First Name</b></th>
                        <th><b>Last Name</b></th>

                        <th><b>Cell Phone</b></th>


                        <th><b>Gender</b></th>
                        <th><b>Gender Comment</b></th>
                        <th><b>Gen- eration</b></th>
                        <th><b>Bio</b></th>
                        <th><b>Spanish</b></th>
                        <th><b>Portuguese</b></th>


                        <th><b>IsActive</b></th>
                        <th><b>Date Created</b></th>
                        <th><b>Date Updated</b></th>
                        <th><b>Last Updated Id</b></th>
                        <th><b>Action</b></th>
                    </tr>

                    <c:forEach items="${membersKey}" var="member">
                        <tr>
                            <td><a href="../member/${member.id}">${member.id}</a></td>
                            <td>${member.firstName}</td>
                            <td>${member.lastName}</td>

                            <td>${member.phoneCell}</td>


                            <td>${member.gender}</td>
                            <td>${member.genderComment}</td>
                            <td>${member.generation}</td>
                            <td>${member.bio}</td>
                            <td>${member.spanish}</td>
                            <td>${member.portuguese}</td>


                            <td>${member.isActive}</td>
                            <td>${member.dateCreated}</td>
                            <td>${member.dateUpdated}</td>
                            <td>${member.lastUpdatedId}</td>
                            <td><a href="../member/edit?id=${member.id}">edit</a></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp" />