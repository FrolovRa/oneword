$(function(){
    $("#search_input").keyup(function(){
        let search = $("#search_input").val()
        if(search !== "") {
            $.ajax({
                type: "POST",
                url: "search",
                data: {"search": search},
                cache: false,
                success: function(response){
                    if(response !== "") {
                        $(".search_result").removeAttr("style");
                        $(".search_result > ul").html(response);
                    } else {
                        $(".search_result").hide();
                    }
                }
            });
        } else {
            $(".search_result").hide();
        }
        return false;
    });
});
