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
                <form action="/member-talent/memberTalentSubmit" method="post">
                    <input type="hidden" name="id" value="${form.id}">  <!-- id refers to member  -->

                    <h2>Profile: Part 2</h2>     <!-- TODO look bad  -->
                    <div><p><h3>My Favorite Talents:</h3></div>        <!-- TODO look bad  -->

                    <!-- languages (optional, user may ignore)  -->
                    <!-- Spanish checkbox -->
                    <div class="row align-items-center justify-content-center pt-3">

                        <div class="col-2">
                            <input type="checkbox" id="keyboard" name="keyboard" value="keyboard">
                            <label for="keyboard">Keyboard</label><br>

                            <input type="checkbox" id="electricGuitar" name="electricGuitar" value="electricGuitar">
                            <label for="electricGuitar">Electric Guitar</label><br>

                            <input type="checkbox" id="acousticGuitar" name="acousticGuitar" value="acousticGuitar">
                            <label for="acousticGuitar">Acoustic Guitar</label><br>

                            <input type="checkbox" id="bassGuitar" name="bassGuitar" value="bassGuitar">
                            <label for="bassGuitar">Bass Guitar</label><br>

                            <input type="checkbox" id="drumSet" name="drumSet" value="drumSet">
                            <label for="drumSet">Drum Set</label><br>

                            <input type="checkbox" id="leadVocals" name="leadVocals" value="leadVocals">
                            <label for="leadVocals">Lead Vocals</label><br>

                            <input type="checkbox" id="harmonyVocals" name="harmonyVocals" value="harmonyVocals">
                            <label for="harmonyVocals">Harmony Vocals</label><br>

                            <input type="checkbox" id="soundSystem" name="soundSystem" value="soundSystem">
                            <label for="soundSystem">Sound System (run the soundboard)</label><br>

                            <input type="checkbox" id="roadie" name="roadie" value="roadie">
                            <label for="roadie">Roadie (set-up, tune instruments, etc.)</label><br>

                            <input type="checkbox" id="setArrangement" name="setArrangement" value="setArrangement">
                            <label for="setArrangement">Musical Set Arrangement (transpose, arrange, etc.)</label><br>
                        </div>
                    </div>

                    <!-- submit button for the form -->
                    <div class="row justify-content-center pt-3 ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Next</button>
                        </div>
                    </div>
                </form>

                <video width="320" height="240"  controls autoplay muted  loop>
                    <source src="/videos/female_drummer_sings.mp4" type="video/mp4" >
                </video>

            </div>
        </div>
    </div>
</section>


<jsp:include page="../include/footer.jsp" />