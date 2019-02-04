var postH;
var postQuantity;

function fitting(postH, postQuantity, maxHeight){
    if( postH * postQuantity <= maxHeight || postQuantity === 0) {
        $(".frame_container").height(postH * postQuantity + postQuantity * 25)
    } else {
        $(".frame_container").height(maxHeight)
    }
}

function openFollows() {
    $(".window_header h5").html("followers");
    $(".follows_following_window").show("fast");
    let id = $(".follows_following_window").data("id");
    $.ajax({
        type: "POST",
        url: "/popup",
        data: {"uid": id, "query": "follows"},
        cache: false,
        success: function(response){
            $(".window_content").html(response);
        }
    });
}

function openFollowing() {
    $(".window_header h5").html("following");
    $(".follows_following_window").show("fast");
    let id = $(".follows_following_window").data("id");
    $.ajax({
        type: "POST",
        url: "/popup",
        data: {"uid": id, "query": "following"},
        cache: false,
        success: function(response){
            $(".window_content").html(response);
        }
    });
}

function openLiked(post) {
    $(".window_header h5").html("liked");
    $(".follows_following_window").show("fast");
    let id = $(post).data("id");
    $.ajax({
        type: "POST",
        url: "/popup",
        data: {"uid": id, "query": "like"},
        cache: false,
        success: function(response){
            $(".window_content").html(response);
        }
    });
}

$(document).ready(function(){

    if($(".error_content").children().length === 0) {
        console.log("ok");
        $(".star_char").hide();
    }

    $('#register').on('submit', function(event) {
        if (validateForm()) {
            $(".star_char").show();
            event.preventDefault();
        }
    });

    $(".post").mouseover(function() {
        $(this).css("background-color","rgba(255, 255, 255, 0.35)");
        $(this).css("transform","scale(1.01)");
    }).mouseout(function() {
        $(this).css("background-color","rgba(255, 255, 255, 0.2)");
        $(this).css("transform","scale(1)");
    });

    //result set hiding
    $(document).mouseup(function (e) {
        let container = $("#search_input, .search_result, .window");
        if (container.has(e.target).length === 0){
            $(".search_result").hide();
            $(".follows_following_window").hide("fast");
        }
    });


    // making application fit the windows after documets ready
    postH = $(".frame").height();
    postQuantity = $(".frame").length;
    let maxHeightFrameContainer = $( window ).height() - 163 - 50;
    fitting(postH, postQuantity, maxHeightFrameContainer);
});


    // resize frame container in case of resizing window
$(window).resize(function(){
    let maxHeightFrameContainer = $( window ).height() - 163 - 50;
    fitting(postH, postQuantity, maxHeightFrameContainer);
});

function validateForm() {
    $(".text-error").remove();
    // $(".star_char").hide();

    // Проверка логина
    let v_login = false;

    let login = $("#login");
    if (login.val().length < 4) {
         v_login = true;
        $(".error_content").append("<p class=\"text-error\">username must be at least 4 characters</p>")
    }
    login.toggleClass('error_form', v_login);

    // Проверка паролей
    let password = $("#password_input");
    let passwordConfirm = $("#password_input_confirm");

    let v_pass1 = !password.val();
    let v_pass2 = !passwordConfirm.val();

    if (password.val() !== passwordConfirm.val()) {
         v_pass2 = true;
        $(".error_content").append("<p class=\"text-error\">passwords doesn't match</p>");
    } else if (password.val().length < 6) {
         v_pass1 = true;
        $(".error_content").append('<p class="text-error">password must be at least 6 characters</p>');
    }
    password.toggleClass('error_form', v_pass1);
    passwordConfirm.toggleClass('error_form', v_pass2);

    return (v_login || v_pass1 || v_pass2);
}