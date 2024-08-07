<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1>
                <c:if test="${empty form.id}">
                    <h1 class="text-center">Create Talent</h1>
                </c:if>
                <c:if test="${not empty form.id}">
                    <h1 class="text-center">Edit Talent</h1>
                </c:if>
            </h1>
        </div>
    </div>
</section>


<section>
    <div class="container">
        <div class="row pt-5 ">
            <div class="col-12">
                <form action="/talent/createSubmit" method="post" >
                    <input type="hidden" name="id" value="${form.id}">  <!-- id is the talent id -->

                    <!-- name input -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="nameId" class="col-form-label">Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="nameId" name="name" class="form-control <c:if test="${bindingResult.hasFieldErrors('name')}">is-invalid</c:if>" value="${form.name}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('name')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- description input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="descriptionId" class="col-form-label">Description</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="descriptionId"
                                   name="description"
                                   class="form-control <c:if test="${bindingResult.hasFieldErrors('description')}">is-invalid</c:if>"
                                   value="${form.description}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('description')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- small photo url input -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="urlSmallPhotoId" class="col-form-label">URL: Small Photo</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="urlSmallPhotoId" name="urlSmallPhoto" class="form-control <c:if test="${bindingResult.hasFieldErrors('urlSmallPhoto')}">is-invalid</c:if>" value="${form.urlSmallPhoto}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('urlSmallPhoto')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('urlSmallPhoto')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Large photo url input -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="urlLargePhotoId" class="col-form-label">URL: Large Photo</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="urlLargePhotoId" name="urlLargePhoto" class="form-control <c:if test="${bindingResult.hasFieldErrors('urlLargePhoto')}">is-invalid</c:if>" value="${form.urlLargePhoto}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('urlLargePhoto')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('urlLargePhoto')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>



                    <div class="row justify-content-center pt-3 ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary" id="submitButton">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script>
    document.addEventListener(
        'DOMContentLoaded', () => {
            document.getElementById('submitButton').
            addEventListener('click', function () {
                alert('You have created a new talent!');
            });
        });
</script>

<jsp:include page="../include/footer.jsp" />