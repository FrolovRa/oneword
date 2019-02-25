<%@ page import="entities.User" %>
<%@ page import="entities.Post" %>
<%@ page import="app.TimeOfPublications" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Cache-Control" content="private">
    <title>One Word</title>
    <link rel="stylesheet" type="text/css" href="../assets/main.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../assets/script.js"></script>
    <script type="text/javascript" src="../assets/ajax_likebtn.js"></script>
    <script type="text/javascript" src="../assets/ajax_search.js"></script>
    <script type="text/javascript" src="../assets/ajax_subscribe.js"></script>
</head>
<body>
<!-- header -->
<div class="wrapperUser">
    <div class="logo_oneword">
        <h1>OneWord</h1>
    </div>
    <div class="search_wrapper">
        <div class="home_btn" onclick="location.href = '/my-page'">
            <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="28" height="28" viewBox="0 0 224 224" style=" fill:#000000;">
                <g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none"
                   text-anchor="none" style="mix-blend-mode: normal">
                    <path d="M0,224v-224h224v224z" fill="none"></path>
                    <g fill="#ffffff">
                        <g id="surface1">
                            <path d="M112,18.15625l-5.03125,4.8125l-91,91l10.0625,10.0625l8.96875,-8.96875v80.9375h63v-70h28v70h63v-80.9375l8.96875,8.96875l10.0625,-10.0625l-91,-91zM112,38.0625l63,63v80.9375h-35v-70h-56v70h-35v-80.9375z"></path>
                        </g>
                    </g>
                </g>
            </svg>
        </div>
        <!-- <div class="search_icon"><img src="assets/icons-search.png" alt="search"></div> -->
        <div class="search">
            <input id="search_input" autocomplete="off" type="text" name="search">
            <div class="search_result" style="display:none">
                <ul></ul>
            </div>
        </div>
        <div class="logout" onclick="logout()">
            <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
                 width="28" height="28"
                 viewBox="0 0 224 224"
                 style=" fill:#000000;"><g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><path d="M0,224v-224h224v224z" fill="none"></path><g fill="#ffffff"><g id="surface1"><path d="M35,21v182h126v-40.6875l-7.65625,-7.4375l-5.90625,-6.125h-0.4375v40.25h-98v-154h98v40.6875l6.34375,-6.5625l7.65625,-7.4375v-40.6875zM163.40625,78.96875l-10.0625,10.0625l15.96875,15.96875h-85.3125v14h85.3125l-15.96875,15.96875l10.0625,10.0625l28,-28l4.8125,-5.03125l-4.8125,-5.03125z"></path></g></g></g></svg>
        </div>
    </div>
    <div>
        <!-- content -->
        <div id="userInfo">
            <div><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="50" height="50" viewBox="0 0 224 224" style=" fill:#000000;">
                <g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none"
                   text-anchor="none" style="mix-blend-mode: normal">
                    <path d="M0,224v-224h224v224z" fill="none"></path>
                    <g id="original-icon" fill="#ffffff">
                        <g id="surface1">
                            <path d="M112,8.96c-44.5375,0 -80.64,12.04 -80.64,26.88v76.16c0,22.7675 24.2725,85.4875 80.64,98.56c56.3675,-13.0725 80.64,-75.7925 80.64,-98.56v-76.16c0,-14.84 -36.1025,-26.88 -80.64,-26.88zM112,17.92c47.04,0 71.68,13.3175 71.68,17.92v76.16c0,18.5325 -19.4075,69.545 -62.72,86.45v-28.21h-17.92v28.21c-43.3125,-16.905 -62.72,-67.9175 -62.72,-86.45v-76.16c0,-4.6025 24.64,-17.92 71.68,-17.92zM76.16,49.28c-8.9425,0 -18.5675,3.9375 -26.88,17.92c8.75,-6.09 16.1875,-8.96 22.4,-8.96c12.04,0 18.0425,14.105 19.88,15.96c2.625,2.625 6.8775,2.625 9.52,0c2.625,-2.625 2.625,-6.8775 0,-9.52c-1.645,-1.6275 -9.52,-15.4 -24.92,-15.4zM147.84,49.28c-15.4,0 -23.275,13.7725 -24.92,15.4c-2.625,2.6425 -2.625,6.895 0,9.52c2.6425,2.625 6.895,2.625 9.52,0c1.8375,-1.855 7.84,-15.96 19.88,-15.96c6.2125,0 13.65,2.87 22.4,8.96c-8.3125,-13.9825 -17.9375,-17.92 -26.88,-17.92zM71.68,76.16c-7.35,0 -13.8075,2.66 -17.92,6.72c4.1125,4.06 10.57,6.72 17.92,6.72c7.35,0 13.8075,-2.66 17.92,-6.72c-4.1125,-4.06 -10.57,-6.72 -17.92,-6.72zM152.32,76.16c-7.35,0 -13.8075,2.66 -17.92,6.72c4.1125,4.06 10.57,6.72 17.92,6.72c7.35,0 13.8075,-2.66 17.92,-6.72c-4.1125,-4.06 -10.57,-6.72 -17.92,-6.72zM49.28,116.48l22.4,35.84h31.36l8.96,-8.96l8.96,8.96h31.36l22.4,-35.84l-26.88,22.4h-17.92l-13.44,-13.44h-8.96l-13.44,13.44h-17.92z"></path>
                        </g>
                    </g>
                </g>
            </svg></div>

            <div><%
                String role = (String) session.getAttribute("role");
                Boolean usrRole = role.equals("user");

                User u = (User) request.getAttribute("user");
                out.print(u.getUsername());
            %></div >
             <%
                out.print("<div id=\"followers\" class=\"follows\"" );
                if(usrRole) {
                    out.print("onclick=\"openFollows()\">");
                } else {
                    out.print(">");
                }
                out.print("Followers " + u.getFollowers().size());
            %></div>
            <%
                out.print("<div id=\"following\" class=\"following\"" );
                if(usrRole) {
                    out.print("onclick=\"openFollowing()\">");
                } else {
                    out.print(">");
                }
                out.print("Following " + u.getFollowing().size());
            %></div>
        </div>
        <div class="subscribe_container">
            <button type="button" data-id="<% out.print(u.getId()); %>" onclick="subscribe(this)"><%
                User su = null;
                if(usrRole) {
                    su = (User) session.getAttribute("user");
                    if(su.getFollowing().contains(u)) {
                        out.print("unfollow");
                    } else out.print("follow");
                } else {
                  out.print("Log in for following");
                }
            %></button>
        </div>
        <div class="frame_container only_users_post">
            <%
                if(u.getPosts().isEmpty()) {
                    out.print("<p style='text-align: center'> do not have words yet </p>");
                } else {
                    for (Post p:u.getPosts()) {
                        out.print("<div class=\"frame\">\n" +
                                "           <p>" +
                                TimeOfPublications.getTimeDifference(p.getDate()) +
                                "           </p>\n" +
                                "          <div class=\"post\" data-id=\""+ p.getPostId() + "\">\n" +
                                "            <div class=\"like\"");
                        if(usrRole) {
                            if (su.getFavorite().contains(p)){
                                out.print(" onclick=\"like(this)\" style=\"background-image: url('/assets/icon-heart-on.png')\"");
                            }
                        }
                        out.print("          >\n " +
                                "         </div>\n" + "<div class=\"like_count\"");
                        if(usrRole) {
                            out.print("onclick=\"openLiked(this)\">");
                        } else {
                            out.print(">");
                        }
                        out.print(p.getLiked().size() + "</div>" +
                                "            <p class=\"content\">" + p.getWord() + "</p>\n" +
                                "            <div class=\"username_wrapper\"><h6 class=\"username\">"
                                        + p.getOwner_id().getUsername() + "</h6> </div>"  +
                                "          </div>\n" +
                                "        </div>");
                    }
                }
            %>
        </div>
    </div>
</div>
<div class="follows_following_window" data-id="<%out.print(u.getId());%>">
    <div class="window">
        <div class="window_header">
            <h5></h5>
        </div>
        <div class="window_content">
        </div>
    </div>
</div>
</body>
</html>
