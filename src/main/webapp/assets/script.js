var postH;
var postQuantity;

function fitting(postH, postQuantity, maxHeight){
    if( postH * postQuantity <= maxHeight || postQuantity === 0) {
        $(".frame_container").height(postH * postQuantity + postQuantity * 25)
    } else {
        $(".frame_container").height(maxHeight)
    }
}

$(document).ready(function(){
    $(".post").mouseover(function() {
        $(this).css("background-color","rgba(255, 255, 255, 0.35)");
        $(this).css("transform","scale(1.01)");
    }).mouseout(function() {
        $(this).css("background-color","rgba(255, 255, 255, 0.2)");
        $(this).css("transform","scale(1)");
    });

    //result set hiding
    $(document).mouseup(function (e) {
        let container = $("#search_input, .search_result");
        if (container.has(e.target).length === 0){
            $(".search_result").hide();
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

