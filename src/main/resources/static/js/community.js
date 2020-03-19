function comment() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    if (!content) {
        alert("回复不能为空");
        return;
    }
    saveContent(questionId, 1, content);
}

/**
 * 二级评论展开
 */
function collapseComment(e) {
    let id = e.getAttribute("data-id");
    let collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //折叠评论
        $("#comment-" + id).removeClass("in");
        e.removeAttribute("data-collapse")
        e.classList.remove("active");
    } else {
        let subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length == 1) {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    let madiaLeft = $("<div/>", {
                        "class": "media-left",
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    let madiaBody = $("<div/>", {
                        "class": "media-body",
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.createTime).format("YYYY-MM-DD HH:mm")
                    })));

                    let madia = $("<div/>", {
                        "class": "media",
                    }).append(madiaLeft).append(madiaBody);

                    let div = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                    }).append(madia);
                    subCommentContainer.prepend(div);
                });
            });
        }
        $("#comment-" + id).addClass("in");
        e.setAttribute("data-collapse", "in");
        e.classList.add("active");
    }
}

function subComment(e) {
    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    if (!content) {
        alert("回复不能为空");
        return;
    }
    saveContent(commentId, 2, content);
}

function saveContent(targetId, type, content) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code === 1) {
                window.location.reload();
            } else {
                if (response.code === 1002) {
                    //未登录
                    if (confirm(response.msg) === true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.5a3cd7aae6b520e7&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.msg);
                }
            }
        },
        dataType: "json"
    });
}

function showSelectTag() {
    $("#select-tag").show();

}

function selectTag(e) {
    let commentId = e.getAttribute("data-tag");
    let tags = $("#tag").val();
debugger;
    if ($.inArray(commentId,tags.split(",")) < 0){
        if(tags){
            $("#tag").val(tags+','+commentId);
        }else {
            $("#tag").val(commentId);
        }
    }
}