function comment() {
    let questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code === 1) {
                $("#commentId").hide();
            } else {
                if (response.code === 1002) {
                    //未登录
                    if (confirm(response.message) === true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.5a3cd7aae6b520e7&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "json"
    });
    console.log(questionId);
}