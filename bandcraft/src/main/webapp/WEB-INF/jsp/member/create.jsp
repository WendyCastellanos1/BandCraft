<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1>
                <c:if test="${empty form.id}">
                    <h1 class="text-center">Create Profile</h1>
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
                    <input type="hidden" name="id" value="${form.id}">  <!-- the member id  -->


                    <!-- firstName input field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="firstNameId" class="col-form-label"><b>First Name</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="firstNameId"
                                   name="firstName"
                                   class="form-control
                                           <c:if test="${bindingResult.hasFieldErrors('firstName')}">is-invalid</c:if>"
                                   value="${form.firstName}">
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
                            <label for="lastNameId" class="col-form-label"><b>Last Name</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="lastNameId"
                                   name="lastName"
                                   class="form-control
                                           <c:if test="${bindingResult.hasFieldErrors('lastName')}">is-invalid</c:if>"
                                   value="${form.lastName}">
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
                            <label for="nicknameId" class="col-form-label"><b>Nickname</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="nicknameId"
                                   name="nickname"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('nickname')}">is-invalid</c:if>"
                                   value="${form.nickname}">
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
                            <label for="phoneCellId" class="col-form-label"><b>Cell Phone</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="phoneCellId"
                                   name="phoneCell"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('phoneCell')}">is-invalid</c:if>"
                                   value="${form.phoneCell}">
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
                            <label for="phoneAltId" class="col-form-label"><b>Alternative Cell Phone</b></label>
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
                            <label for="fileId" class="col-form-label"><b>Profile Photo</b></label>
                        </div>
                        <div class="col-4">
                            <input type="file" id="fileId" name="profilePhoto" class="form-control">
                        </div>
                    </div>

                    <!-- birth generation dropdown(optional)  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <p><b>Birth Generation (Optional):</b></p>
                        </div>
                        <div class="col-4">
                            <select class="form-select" aria-label="Default select example" name="generation">
                                ${generationOptionsKey}
                            </select>
                        </div>
                    </div>


                    <!-- gender - 3 radio buttons  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <p><b>Gender:</b></p>
                        </div>

                        <div class="col-4">

                            <div class="form-check">
                                <input class = "form-check-input"
                                       type="radio"
                                       name="gender"
                                       id="maleId"
                                       value="m"
                                       <c:if test = "${form.gender == 'm'}">checked="checked"</c:if>
                                >
                                <label class="form-check-label" for="maleId">
                                    Male
                                </label>
                            </div>

                            <div class="form-check">
                                <input class="form-check-input"
                                       type="radio"
                                       id="femaleId"
                                       value="f"
                                       name="gender"
                                       <c:if test = "${form.gender == 'f'}">checked="checked"</c:if>
                                >
                                <label class="form-check-label" for="femaleId">
                                    Female
                                </label>
                            </div>

                            <div class="form-check">
                                <input class="form-check-input"
                                       type="radio"
                                       id="otherId"
                                       value="o"
                                       name="gender"
                                       <c:if test = "${form.gender == 'o'}">checked="checked"</c:if>
                                >
                                <label class="form-check-label" for="otherId">
                                    Other
                                </label>

                            </div>

                        </div>
                    </div>


                    <!-- gender comment (optional)  -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="genderCommentId" class="label_form"><b>Gender Comment:</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="genderCommentId"
                                   name="genderComment"
                                   class="form-control
                                        <c:if test="${bindingResult.hasFieldErrors('genderComment')}">is-invalid</c:if>"
                                        value="${form.genderComment}"
                                    >
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('genderComment')}">
                    <div class="row align-items-center justify-content-center">
                        <div class="offset-2 col-4">
                            <div style="color:red">
                                <c:forEach items="${bindingResult.getFieldErrors('genderComment')}" var="error">
                                    ${error.defaultMessage}<br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:if>


                    <!-- languages (optional, user may ignore)   two checkboxes -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <p><b>Can you communicate in either of these languages?</b></p>
                        </div>

                        <div class="col-4">

                            <!-- Spanish checkbox -->
                            <div class="form-check">
                                <input class="form-check-input"
                                       type="checkbox"
                                       id="speaksSpanishId"
                                       name="speaksSpanish"
                                        <c:if test="${form.speaksSpanish}"> checked</c:if>
                                >
                                <label class="form-check-label" for="speaksSpanishId">
                                    Spanish
                                </label>
                            </div>

                            <!-- Portuguese checkbox -->
                            <div class="form-check">
                                <input class="form-check-input"
                                       type="checkbox"
                                       id="speaksPortugueseId"
                                       name="speaksPortuguese"
                                        <c:if test="${form.speaksPortuguese}"> checked</c:if>
                                >

                                <label class="form-check-label" for="speaksPortugueseId">
                                    Portuguese
                                </label>
                            </div>

                        </div>
                    </div>

                    <!-- Misc. personal info -->
                    <!-- Bio textarea field -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="bioId" class="label_form"><b>Tell us about yourself (bio):</b> </label><br>
                        </div>
                        <div class="col-4">
                            <textarea style="text-align: left; vertical-align: top;"
                                      maxlength = "1000"
                                      id="bioId"
                                      rows = "5" cols = "40"
                                      name = "bio"
                                      <c:if test="${bindingResult.hasFieldErrors('bio')}">is-invalid</c:if>
                            >${form.bio}
                            </textarea><br>
                        </div>

                        <c:if test="${bindingResult.hasFieldErrors('bio')}">
                            <div class="row align-items-center justify-content-center">
                                <div class="offset-2 col-4">
                                    <div style="color:red">
                                        <c:forEach items="${bindingResult.getFieldErrors('bio')}" var="error">
                                            ${error.defaultMessage}<br>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>

                    <!-- Social Media URL  input field (optional, user may ignore, may not have) -->
                    <div class="row align-items-center justify-content-center pt-3">
                        <div class="col-2">
                            <label for="socialMediaUrlId" class="col-form-label"><b>Link for your public-facing social media account</b></label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="socialMediaUrlId"
                                   name="socialMediaUrl"
                                   class="form-control
                                           <c:if test="${bindingResult.hasFieldErrors('socialMediaUrl')}">is-invalid</c:if>"
                                   value="${form.socialMediaUrl}"
                            >
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('socialMediaUrl')}">
                    <div class="row align-items-center justify-content-center">
                        <div class="offset-2 col-4">
                            <div style="color:red">
                                <c:forEach items="${bindingResult.getFieldErrors('socialMediaUrl')}" var="error">
                                    ${error.defaultMessage}<br>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    </c:if>
                        </div>
                    </div>

                    <!-- submit button for the form -->
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


<jsp:include page="../include/footer.jsp"/>