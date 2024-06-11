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
        confirm("이대로 추가하시겠어요?", "작성한 내용은 잘 확인하셨나요?");
    }

    if(isValidCode(form)) {
        ConfirmAdd();
    }
}

// 유효성 검사
function isValidCode(form) {
    const name = form.name.value;
    const phone = form.phone.value;
    const address = form.address.value;

    if(name == ''){
        swal("경고: 이름은 필수입니다!","이름을 꼭 써주세요!", "warning");
        return false;
    }
    if(phone == ''){
        swal("경고: 전화번호는 필수입니다!","전화번호를 꼭 써주세요!", "warning");
        return false;
    }
    if(address == ''){
        swal("경고: 주소는 필수입니다!","주소를 꼭 써주세요!", "warning");
        return false;
    }
    else{
        return true;
    }
}