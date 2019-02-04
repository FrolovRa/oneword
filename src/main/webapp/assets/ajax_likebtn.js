function like(obj){
    let id = $(obj).parent().data("id");
    if(obj.style.backgroundImage === 'url("/assets/icon-heart-on.png")') {
        $.ajax({
            type: "POST",
            url: "/like",
            data: {"post_id": id,  "cancel": true},
            cache: false,
            success: function(res){
                changeBackground(obj);
                $(obj).parent().children(".like_count").html(res);
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/like",
            data: {"post_id": id},
            cache: false,
            success: function(res){
                changeBackground(obj);
                $(obj).parent().children(".like_count").html(res);
            }
        });
    }
}

function changeBackground(obj) {
  if (obj.style.backgroundImage === 'url("/assets/icon-heart-on.png")'){
      obj.removeAttribute("style");
  } else {
      $(obj).css("background-image", "url('/assets/icon-heart-on.png')");
  }
}
