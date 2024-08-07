<!-- goes at TOP -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1>
                <c:if test="${empty form.id}">
                    <h1 class="text-center">Member Profile</h1>
                </c:if>
                <c:if test="${not empty form.id}">
                    <h1 class="text-center">Edit Profile</h1>
                </c:if>
            </h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5 ">
            <div class="col-12">
                <form action="/member/createSubmit" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${form.id}">  <!-- id is the member id  -->

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

                    <!-- cell phone input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="phoneId" class="col-form-label">Cell Phone</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="phoneCellId"
                                   name="phoneCell"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('phoneCell')}">is-invalid</c:if>"
                                   value="${form.phoneCell} ">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('phoneCell')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('phoneCell')}" var="error">
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


                    <!-- file upload to put a profile photo url in the db   -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="fileId" class="col-form-label">Profile Photo</label>
                        </div>
                        <div class="col-4">
                            <input type="file" id="fileId" name="file" class="form-control">
                        </div>
                    </div>

                    <!-- birth generation dropdown(optional)  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="generationId" class="label_form">Birth Generation (Optional):</label>
                        </div>
                        <div class="col-4">
                            <select name="generation" id="generationId">
                                <option value="ns">Select one...</option>
                                <option value="a">Gen Alpha</option>
                                <option value="z">Gen Z</option>
                                <option value="m">Millennial</option>
                                <option value="x">Gen X</option>
                                <option value="b">Baby Boomer</option>
                                <option value="s">Silent Generation</option>
                            </select>
                        </div>
                    </div>
                    <!-- generation TODO - Validation for the dropdown -->

                    <!-- gender  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="genderId" class="label_form">Gender:</label>
                        </div>
                        <div class="col-4">
                            <select name="gender" id="genderId">
                                <option value="ns">Select one...</option>
                                <option value="f">Female</option>>
                                <option value="m">Male</option>
                                <option value="o">Other</option>
                            </select>
                        </div>
                    </div>
                    <!-- gender TODO Validation for dropdown -->

                    <!-- gender comment (optional)  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="genderCommentId" class="label_form">Gender Comment:</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="genderCommentId" name="genderComment">
                        </div>
                    </div>




                    <!-- gender comment TODO validation: maximum character count -->


                    <!-- languages (optional, user may ignore)  -->
                    <!-- Spanish checkbox -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <p>Can you communicate in either of these languages?</p>
                        </div>
                        <div class="col-4">
                            <input type="checkbox" id="speaksSpanishId" name="speaksSpanish" value="speaksSpanish">
                            <label for="speaksSpanishId" class="label_form">Spanish</label>
                            <input type="checkbox"  id="speaksPortugueseId" name="speaksPortuguese" value="speaksPortuguese">
                            <label for="speaksPortugueseId" class="label_form">Portuguese</label>
                        </div>
                    </div>

                    <!-- Misc. personal info -->
                    <!-- Bio textarea field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="bioId" class="label_form">Tell us about yourself (bio): </label><br>
                        </div>
                        <div class="col-4">
                            <textarea maxlength = "1000" id="bioId" name = "bio" rows = "4" cols = "40"> </textarea><br>
                        </div>
                    </div>

                    <!-- Social Media URL  input field (optional, user may ignore, may not have) -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="socialMediaUrlId" class="col-form-label">Link to your public-facing social media account</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="socialMediaUrlId" name="socialMediaUrl">
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