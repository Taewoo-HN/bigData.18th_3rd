function formSubmit() {
    const form = document.querySelector('form');
    if (isValidCode(form)) {
        form.submit();
    }
}

// 유효성 검사
function isValidCode(form) {
    const userid = form.userid.value;
    const userpw = form.userpw.value;

    if(userid == ''){
        swal("에러: ID가 입력되지 않았습니다!", "ID를 입력하세요!", "error");
        return false;
    }
    if(userpw == ''){
        swal("에러: 비밀번호가 입력되지 않았습니다!", "비밀번호를 입력하세요!", "error");
        return false;
    }
    else{
        return true;
    }
}