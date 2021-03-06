let postH;
let postQuantity;
let time;
let out;

function fitting(postH, postQuantity, maxHeight){
    if( postH * postQuantity <= maxHeight || postQuantity === 0) {
        $(".frame_container").height((postH + 25) * postQuantity)
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

function openLiked(like) {
    let post = $(like).parent();
    console.log($(like).html());
    if($(like).html() !== "0") {
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
}

function remove(post) {
    let id = $(post).parent().data("id");
    $.ajax({
        type: "POST",
        url: "/remove",
        data: {"postid": id},
        cache: false,
        success: function(){
            hidePost(post)
        }
    });
}

$(document).ready(function(){

    $("#word_form").submit(function(e) {

        $.ajax({
            type: "POST",
            url: "/create-post",
            data: $("#word_form").serialize(),
            success: function(post) {
                $(".left_part_frames").prepend(post);
                    $(".new-post")
                        .animate({
                    height: 102,
                    margin: "20 auto auto"
                        }, 500)
                        .animate({
                        opacity: 1
                        }, 600);
            }
        });
        e.preventDefault()
    });


    if($(".error_content").children().length === 0) {
        $(".star_char").hide();
    }

    $('#register').on('submit', function(event) {
        if (validateForm()) {
            $(".star_char").show();
            event.preventDefault();
        }
    });



    //result set hiding
    $(document).mouseup(function (e) {
        let container = $("#search_input, .search_result, .window");
        if (container.has(e.target).length === 0){
            $(".search_result").hide();
            $(".follows_following_window").hide("fast");
            $(".window_content").children().remove();
        }
    });


    // making application fit the windows after documets ready
    postH = $(".frame").height();
    postQuantity = $(".frame").length;
    let maxHeightFrameContainer = $( window ).height() - 163 - 70;
    fitting(postH, postQuantity, maxHeightFrameContainer);
});


    // resize frame container in case of resizing window
$(window).resize(function(){
    let maxHeightFrameContainer = $( window ).height() - 163 - 70;
    fitting(postH, postQuantity, maxHeightFrameContainer);
});


//for sign in
function validateForm() {
    $(".text-error").remove();
    // $(".star_char").hide();

    // login validation
    let v_login = false;

    let login = $("#login");
    if (login.val().length < 4) {
         v_login = true;
        $(".error_content").append("<p class=\"text-error\">username must be at least 4 characters</p>")
    }
    login.toggleClass('error_form', v_login);

    // password validation
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

//for log in
function validateFormLog() {
    $(".text-error").remove();
    // $(".star_char").hide();

    // login validation
    let v_login = false;

    let login = $("#login");
    if (login.val().length < 4) {
        v_login = true;
        $(".error_content").append("<p class=\"text-error\">username must be at least 4 characters</p>")
    }
    login.toggleClass('error_form', v_login);

    // password validation
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

function hidePost(post) {
    let frame = $(post).parent().parent();

    frame.animate({
        opacity:0
    }, 600).animate({
        height:0,
        margin:0
    },500)
}

// word filter
$(function() {
    let txt = $("#word_input");
    let func = function() {
        txt.val(txt.val().replace(/\s/g, ''));
    };
    txt.keyup(func).blur(func);
});

$(document).on('mouseenter','.post', function() {
        let post = $(this);
        window.clearTimeout(out);
        out = null;
        if (!time) {
            time = window.setTimeout(function() {
                post.children(".rmv_btn").show("fast");
            }, 600);
        }
        $(this).css("background-color","rgba(255, 255, 255, 0.35)");
        $(this).css("transform","scale(1.01)");
}).on('mouseleave','.post', function() {
    let post = $(this);
    window.clearTimeout(time);
    time = null;
    out = window.setTimeout(function(){
        post.children(".rmv_btn").hide("fast");
    }, 2);
    $(this).css("background-color","rgba(255, 255, 255, 0.2)");
    $(this).css("transform","scale(1)");
});


function logout() {
    $.ajax({
        type: "POST",
        url: "/logout",
        cache: false,
        success: function(){
            document.location.href = '/'
        }
    });
}

