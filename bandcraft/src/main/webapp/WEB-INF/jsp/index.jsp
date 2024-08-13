<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="./include/header.jsp" />


<!-- a page header -->
<section style="background-color:aquamarine">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">Home</h1>
        </div>
    </div>
</section>

<br><br><br>

<!-- add an optional message that won't show unless it's passed in  -->
<h4 class="text-center" style="color:mediumpurple">${messageKey}</h4>

<br>

<h2 class="text-center">Connecting talented people to form bands</h2>


<jsp:include page="./include/footer.jsp"/>

