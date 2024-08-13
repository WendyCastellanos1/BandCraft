<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Member Search</h1>
        </div>
    </div>
</section>

<!-- search form; section>container>one row>one column>form w/label and input, button -->
<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action="../member/search">
                    <div class="mb-3">
                        <label for="search" class="form-label"><h4>Enter a first name, last name, or nickname: </h4></label>
                        <input type="text" value="${searchKey}" class="form-control" id="search" name="search" placeholder="Enter search term"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
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

        <!-- show the rows of members -->
        <div class="row">
            <div class ="col-12">
                <table class="table">
                    <tr>
                        <th><b>Member Id</b></th>
                        <th><b>Last Name</b></th>
                        <th><b>First Name</b></th>
                        <th><b>Nickname</b></th>
                        <th><b>Gender</b></th>
                        <th><b>GenderComment</b></th>
                        <th><b>Generation</b></th>
                    </tr>

                    <c:forEach items="${membersKey}" var="member">
                        <tr>
                            <td><a href="../member/${member.id}">${member.id}</a></td>
                            <td>${member.lastName}</td>
                            <td>${member.firstName}</td>
                            <td>${member.nickname}</td>
                            <td>${member.gender}</td>
                            <td>${member.genderComment}</td>
                            <td>${member.generation}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />