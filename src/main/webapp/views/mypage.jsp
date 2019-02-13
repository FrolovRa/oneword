<%@ page import="entities.User" %>
<%@ page import="entities.Post" %>
<%@ page import="app.Feed" %>
<%@ page import="java.util.Set" %>
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
        <div class="search_icon"><img src="../assets/icons-search.png" alt="search"></div>
        <div>
            <input id="search_input" autocomplete="off" type="text" name="search">
            <div class="search_result" style="display:none">
                <ul>

                </ul>
            </div>
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
                User u = (User) session.getAttribute("user");
                out.print(u.getUsername());
                Set<Post> feed = Feed.generate(u);
            %></div>
            <div class="follows" onclick="openFollows()"><%
                out.print("Followers " + u.getFollowers().size());
            %></div>
            <div class="following" onclick="openFollowing()"><%
                out.print("Following " + u.getFollowing().size());
            %></div>
        </div>
        <div class="add-post">
            <form method="post">
                <div id="label_input">Enter the word</div>
                <div id="input_word"><input id="word_input"  maxlength="20" autocomplete="off" type="text" placeholder="here!" name="word"></div>
                <div id="btn_send"><button id="sendWord" type="submit"><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="24" height="24" viewBox="0 0 224 224" style=" fill:#000000;">
                    <g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none"
                       text-anchor="none" style="mix-blend-mode: normal">
                        <path d="M0,224v-224h224v224z" fill="none"></path>
                        <g fill="#ffffff">
                            <path d="M205.33333,18.66667l-186.66667,67.86719l50.75,50.76823l98.58333,-81.30209l-81.30208,98.58333l50.76823,50.75z"></path>
                        </g>
                    </g>
                </svg></button></div>
                <div id="btn_send_label">Say it!</div>
            </form>
        </div>
        <div class="frame_container">
            <div class="left_part_frames">
            <%
                if(u.getPosts().isEmpty()) {
                    out.print("<p style='text-align: center'> do not have words yet </p>");
                } else {
                    for (Post p:u.getPosts()) {
                        out.print("<div class=\"frame\">\n" +
                                "           <p> " +
                                        TimeOfPublications.getTimeDifference(p.getDate()) +
                                "          </p>\n" +
                                "          <div class=\"post\" data-id=\""+ p.getPostId() + "\">\n" +
                                 "           <div class=\"rmv_btn\" onclick=\"remove(this)\"></div> " +
                                "            <div class=\"like\" onclick=\"like(this)\" ");
                        if (u.getFavorite().contains(p)){
                            out.print("style=\"background-image: url('/assets/icon-heart-on.png')\"");
                        }
                        out.print(
                                "             >\n " +
                                        "            </div>\n" + "<div class=\"like_count\" onclick=\"openLiked(this)\">" +
                                        p.getLiked().size() +
                                        "</div>" +
                                        "            <p class=\"content\">" + p.getWord() + "</p>\n" +
                                        "            <div class=\"username_wrapper\"><h6 class=\"username\">"
                                        + p.getOwner_id().getUsername() + "</h6> </div>"  +
                                        "          </div>\n" +
                                        "        </div>");
                    }
                }
            %>
            </div>
            <div class="right_part_frames">
                <%
                if(feed.isEmpty()) {
                out.print("<p style='text-align: center'> do not have words yet </p>");
                } else {
                    for (Post p:feed) {
                        out.print("<div class=\"frame\">\n" +
                "           <p> Time " +
                        TimeOfPublications.getTimeDifference(p.getDate()) +
                "           </p>\n" +
                "          <div class=\"post\" data-id=\""+ p.getPostId() + "\">\n" +
                "            <div class=\"like\" onclick=\"like(this)\" ");
                            if (u.getFavorite().contains(p)){
                                out.print("style=\"background-image: url('/assets/icon-heart-on.png')\"");
                }
                        out.print(
                "             >\n " +
                "            </div>\n" + "<div class=\"like_count\" onclick=\"openLiked(this)\">" +
                            p.getLiked().size() +
                "</div>" +
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

