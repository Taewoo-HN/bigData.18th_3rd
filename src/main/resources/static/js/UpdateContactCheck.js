function formSubmit() {
    const form = document.querySelector('form');

    var confirm = function(title, msg){
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

    function ConfirmUpdate(){
        confirm("이대로 수정하시겠어요?", "작성한 내용은 잘 확인하셨나요?");
    }

    if(isValidCode(form)) {
        ConfirmUpdate();
    }
}

// 유효성 검사
function isValidCode(form) {
    const name = form.name.value;
    const phone = form.phone_num.value;
    const address = form.address.value;

    if(name == ''){
        swal("WARNING: 이름칸이 비어있습니다","이름을 꼭 써주세요!", "warning");
        return false;
    }
    if(phone == ''){
        swal("WARNING: 전화번호칸이 비어있습니다.","전화번호를 꼭 써주세요!", "warning");
        return false;
    }
    if(address == ''){
        swal("WARNING: 주소칸이 비어있습니다.","주소를 꼭 써주세요!", "warning");
        return false;
    }
    else{
        return true;
    }
}