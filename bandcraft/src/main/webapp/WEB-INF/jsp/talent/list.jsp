<!-- goes at TOP -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Talents</h1>
        </div>
    </div>
</section>

<!-- count of talents in the list; section>container>one row>one column w/h4; another row w/column headers; x row(s) with talents -->
<section>
    <div class="container">
        <div class="row pt-5 pb-5">
            <div class="col-12">
                <h4 class="text-center"> ${talentsKey.size()} result(s)</h4>
            </div>
        </div>
        <div class="row">
            <div class ="col-12">
                <table class="table">
                    <tr>
                        <th><b>Id</b></th>
                        <th><b>Name</b></th>
                        <th><b>Description</b></th>
                        <th><b>Small Photo URL</b></th>
                        <th><b>Large Photo URL</b></th>
                        <th><b>IsActive</b></th>
                        <th><b>Date Created</b></th>
                        <th><b>Date Updated</b></th>
                        <th><b>Last Updated Id</b></th>
                        <th><b>Action</b></th>
                    </tr>

                    <c:forEach items="${talentsKey}" var="talent">
                        <tr>
                            <td><a href="../talent/${talent.id}">${talent.id}</a></td>
                            <td>${talent.name}</td>
                            <td>${talent.description}</td>
                            <td><a href="${talent.urlSmallPhoto}">small photo</a></td>
                            <td><a href="${talent.urlLargePhoto}">large photo</a></td>
                            <td>${talent.isActive}</td>
                            <td>${talent.dateCreated}</td>
                            <td>${talent.dateUpdated}</td>
                            <td>${talent.lastUpdatedId}</td>
                            <td><a href="../talent/edit?id=${talent.id}">edit</a></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp" />