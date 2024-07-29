<!-- goes at TOP -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:orange">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1>
                <c:if test="${empty form.id}">
                    <h1 class="text-center">Registration 1</h1>
                </c:if>
                <c:if test="${not empty form.id}">
                    <h1 class="text-center">Edit Profile 1</h1>
                </c:if>
            </h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5 ">
            <div class="col-12">
                <form action="/member/regSubmit1" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${form.id}">  <!-- id is the member id  -->

                    <h2>Profile: Part 1</h2>     <!-- TODO look bad  -->
                    <div><p><h3>Name:</h3></div>        <!-- TODO look bad  -->

                    <!-- firstName input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="firstNameId" class="col-form-label">First Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="firstNameId"
                                   name="firstName"
                                   class="form-control
                                           <c:if test="${bindingResult.hasFieldErrors('firstName')}">is-invalid</c:if>"
                                   value="${form.firstName} ">
                        </div>
                    </div>

                    <c:if test="${bindingResult.hasFieldErrors('firstName')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- lastName input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="lastNameId" class="col-form-label">Last Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="lastNameId"
                                   name="lastName"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('lastName')}">is-invalid</c:if>"
                                   value="${form.lastName} ">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('lastName')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- nickname input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="nicknameId" class="col-form-label">Nickname</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="nicknameId"
                                   name="nickname"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('nickname')}">is-invalid</c:if>"
                                   value="${form.nickname} ">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('nickname')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('nickname')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div><h3>Communication:</h3></div>          <!-- TODO may look bad  -->

                    <!-- cell phone input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="phoneId" class="col-form-label">Cell Phone</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="phoneId"
                                   name="phone"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('phone')}">is-invalid</c:if>"
                                   value="${form.phone} ">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('phone')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('phone')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- alt cell phone input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="phoneAltId" class="col-form-label">Alternative Cell Phone</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="phoneAltId"
                                   name="phoneAlt"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('phoneAlt')}">is-invalid</c:if>"
                                   value="${form.phoneAlt} ">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('phoneAlt')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('phoneAlt')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>


                    <!-- File upload to put a profile photo url in the db   -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="file" class="col-form-label">File</label>
                        </div>
                        <div class="col-4">
                            <input type="file" id="file" name="file" class="form-control">
                        </div>
                    </div>


                    <!-- submit button for the form -->
                    <div class="row justify-content-center pt-3 ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Next</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>