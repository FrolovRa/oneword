<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>One Word</title>
    <link rel="stylesheet" type="text/css" href="assets/main.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="assets/script.js"></script>
</head>
<body>
<!-- header -->
<div class="wrapper">
  <div>
      <h1>OneWord</h1>
  </div>

  <div>       <!-- content -->
      <div>    <!-- buttons holder -->
        <form action="${pageContext.request.contextPath}/login" method="post">
                <label><h4>Username</h4></label>
                <input class="login_input" type="text" placeholder="enter username" name="name"><br />
                <label><h4>Password</h4></label>
                <input class="login_input" id="password_input" type="password" placeholder="enter your password" name="pass"><br />
            <button id="login_btn" type="submit" ><h4>Login</h4></button>
            <span> or </span>
            <a href="${pageContext.request.contextPath}/signing">Sign in</a>
        </form>
      </div>
  </div>
    <div class="error_anchor">
        <div class="error">
            <div class="star_char">*</div>
            <div class="error_content">
                <%
                    if (request.getAttribute("username") != null) {
                        out.println("<p class=\"text-error\">User " + request.getAttribute("username") + " does not exist</p>");
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
