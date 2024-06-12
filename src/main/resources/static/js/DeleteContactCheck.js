let confirm = function(title, msg, p_id){
    swal({
        title : title,
        text : msg,
        type : "warning",
        showCancelButton : true,
        confirmButtonClass : "btn-danger",
        confirmButtonText : "예",
        cancelButtonText : "아니오",
        closeOnConfirm : false,
        closeOnCancel : true
    }, function(isConfirm){
        if(isConfirm){
            location.href = "/delete/" + p_id;
        }
    });
}

function ConfirmDelete(p_id){
    confirm("정말로 삭제할까요?", "이 작업은 되돌릴 수 없습니다.", p_id);
}