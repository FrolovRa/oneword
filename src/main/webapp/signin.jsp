<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>One Word</title>
    <link rel="stylesheet" type="text/css" href="assets/main.css">
</head>
<body>
<!-- header -->
<div class="wrapper">
  <div>
      <h1>OneWord</h1>
  </div>

  <div>       <!-- content -->
      <div>    <!-- buttons holder -->
        <form method="post">
                <label><h4>Username</h4></label>
                <input class="login_input" type="text" placeholder="enter username" name="name"><br />
                <label><h4>Password</h4></label>
                <input class="login_input" type="password" placeholder="enter your password" name="pass"><br />
                <input class="login_input" id="password_input_confirm" type="password" placeholder="confirm your password"><br />
                <button id="login_btn" type="submit"><h4>Sign in</h4></button>
        </form>
          <%
              if (request.getAttribute("name") != null) {
                  out.println("<p>User " + request.getAttribute("name") + " added!</p>");
              }
          %>
      </div>
  </div>
</div>
</body>
</html>
