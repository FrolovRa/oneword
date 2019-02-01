function subscribe(obj){
    let user = $(obj).data("id");

    if($(obj).html() === "follow") {
        $.ajax({
            type: "POST",
            url: "/subscribe",
            data: {"subscribe": user},
            cache: false,
            success: function(response){
                $("#followers").html("Followers " + response);
                $(obj).html("unfollow");
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/subscribe",
            data: {"subscribe": user, "cancel": true},
            cache: false,
            success: function(response){
                $("#followers").html("Followers " + response);
                $(obj).html("follow");
            }
        });
    }

}