let main = {
    init : function(){
        let _this = this;
        $("#btn-save").on("click", function(){
            _this.save();
        });

        $("#btn-update").on("click", function(){
            _this.update();
        });

        $("#btn-delete").on("click", function(){
            _this.delete();
        });
    },
    save : function(){
        let data = {
            title : $("#title").val(),
            author : $("#author").val(),
            content : $("#content").val()
        };

        $.ajax({
            type : "POST", // 생성
            url : "/api/v1/posts",
            dataType : "json",
            contentType : "application/json; charset=UTF-8",
            data : JSON.stringify(data)
        }).done(function(){
            alert("글이 등록되었습니다.");
            window.location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update : function(){
         let data = {
             title : $("#title").val(),
             content : $("#content").val()
         };

         let id = $("#id").val();

         $.ajax({
             type : "PUT", // 수정
             url : "/api/v1/posts/" + id,
             dataType : "json",
             contentType : "application/json; charset=UTF-8",
             data : JSON.stringify(data)
         }).done(function(){
              alert("글이 수정되었습니다.");
              window.location.href = "/";
         }).fail(function(error) {
              alert(JSON.stringify(error));
         });
    },

    delete : function(){
            let id = $("#id").val();

            $.ajax({
                type : "DELETE", // 삭제
                url : "/api/v1/posts/" + id,
                dataType : "json",
                contentType : "application/json; charset=UTF-8"
            }).done(function(){
                alert("글이 삭제되었습니다.");
                window.location.href = "/";
            }).fail(function(error) {
                alert(JSON.stringify(error));
            });
        }
};
main.init();
