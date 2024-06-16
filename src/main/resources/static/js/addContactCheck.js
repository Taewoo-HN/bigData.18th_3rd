function formSubmit() {
    const form = document.querySelector('form');

    let confirm = function(title, msg){
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
                form.submit();
            }
        });
    }

    function ConfirmAdd(){
        confirm("연락처를 추가 합니다", "작성한 내용으로 연락처가 추가됩니다");
    }

    if(isValidCode(form)) {
        ConfirmAdd();
    }
}

// 유효성 검사
function isValidCode(form) {
    const name = form.name.value;
    const phone = form.phone_num.value;
    const address = form.address.value;

    if(name == ''){
        swal("WARNING","이름란을 작성해주세요!", "warning");
        return false;
    }
    if(phone == ''){
        swal("WARNING","전화번호란을 작성해주세요!", "warning");
        return false;
    }
    if(address == ''){
        swal("WARNING","주소란을 기입해주세요!", "warning");
        return false;
    }
    else{
        return true;
    }
}