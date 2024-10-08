<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Band Craft</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <!--  Bootstrap  -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!--  CSS
    <link rel="stylesheet" href="/pub/css/global.css">  -->

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid" style="background-color:mediumseagreen; font-weight: bold" >
       <a class="navbar-brand" href="#" style="background-color:darkturquoise; color:lightgreen;">Band Craft</a>
         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarSupportedContent">
             <ul class="navbar-nav me-auto mb-2 mb-lg-0">

<%--                 <sec:authorize access="!hasAnyAuthority('IS_AUTHENTICATED_REMEMBERED', 'IS_AUTHENTICATED_ANONYMOUSLY', 'IS_AUTHENTICATED_FULLY')">--%>
<%--                     <a href="/auth/login" >gives linked text on the NavBar, research the statuses</a>--%>
<%--                 </sec:authorize>--%>

                 <li class="nav-item">
                     <a class="nav-link active" aria-current="page" href="/">Home</a>
                 </li>

                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/account/create-account">Create Account</a>
                    </li>
                </sec:authorize>
                 <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/account/login">Login</a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasAnyAuthority('USER')">
                        <li class="nav-item">
                            <a class="nav-link" href="/member/create">Create Profile</a>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyAuthority('MEMBER')">
                        <li class="nav-item">
                            <a class="nav-link" href="/member/edit?=${memberIdKey}">Edit Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member-talent/create?memberId=${memberIdKey}">Add Talents</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member/${memberIdKey}">Profile Summary</a>
                        </li>
                    </sec:authorize>

                         <li class="nav-item">
                             <a class="nav-link" href="/account/logout">Logout</a>
                         </li>

                         <li class="nav-item">
                            <span class="nav-link"><sec:authentication property="name"/></span>
                         </li>

                    <sec:authorize access="hasAnyAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/member/search">Member Search</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member/list">Member List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/talent/create">Create Talent</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/talent/list">Talent List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/band/create">Create Band</a>
                        </li>

                        <!-- This will be added with event  module
                        <li class="nav-item">
                            <a class="nav-link" href="/event/create">Create Event</a>
                        </li>
                        <li class="nav-item">
                        <a class="nav-link" href="/band/list">Band List</a>
                        </li>
                        -->
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/dashboard">Admin Dashboard</a>
                        </li>
                    </sec:authorize>
                </sec:authorize>

            </ul>
        </div>
    </div>
</nav>


<!--
                 <li class="nav-item">
                     <a class="nav-link" href="/member/detail">My Profile</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="/event/list">Event List</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="/event/search">Event Search</a>
                 </li>
                 <li class="nav-item">
                     <a class="nav-link" href="/about">About</a>
                 </li>

 -->