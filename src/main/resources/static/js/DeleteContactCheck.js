var confirm = function(title, msg, personid){
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
            location.href = "/delete/" + personid;
        }
    });
}

function ConfirmDelete(personid){
    confirm("정말로 삭제할까요?", "이 작업은 되돌릴 수 없습니다.", personid);
}