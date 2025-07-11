<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="Components/navbar.jsp"/>
<%--<jsp:include page="Components/footer.jsp"/>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Distilla'</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/index.css">
</head>
<body>
    <!--<img src="Images/wood-background.jpg" class="bg">-->
    <div class="container-text-and-logo">
        <div class="logo">

        </div>
        <div class="text">
            <h1> DISTILLA'</h1>
            <h2>Liquori, vini e birre artigianali, scopri la nostra selezione, <br> perfetta per assecondare ogni palato.</h2>
        </div>

    </div>
    <%@ include file="Components/categories.jsp" %>
    <div class="flexbox">
        <div class="rectangle-wrapped">
            <div class="rectangle-big">
            <!--<div class="popup">
                <img src="Images/birre.jpg">
                <img src="Images/vini.jpg">
                <img src="Images/cocktails.jpg">
            </div>-->
                <div id="rectangle-footer">

                </div>
            </div>

            <div class="info">
            <h3>Vieni a scoprirci!</h3>
            <p>  Info utili Info utili Info utili Info utili.</p>
            </div>

        </div>

        <section class="cards">
            <div id="one"></div>
            <div id="two"></div>
            <div id="three"></div>
        </section>
    </div>



<br/>
<!--<a href="hello-servlet">Hello Servlet</a> -->
</body>
</html>