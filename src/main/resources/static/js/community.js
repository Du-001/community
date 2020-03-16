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
        $.getJSON("/comment/" + id, function (data) {
            $("#comment-" + id);
            let item = [];
            $.each(data.data, function (key, val) {
                item.push();
            });
            $("<ul/>", {
                "class": "my",
                html: item.join("")
            }).appendTo("body");
        });
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
                    if (confirm(response.message) === true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.5a3cd7aae6b520e7&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}