function formlogSubmit() {
    const form = document.querySelector('#loginForm');
    if (isValidlogCode(form)) {
        form.submit();
    }
}

// 유효성 검사
function isValidlogCode(form) {
    const userid = form.user_id.value;
    const userpw = form.user_pw.value;

    if(userid == ''){
        swal("ERROR: ID가 입력되지 않았습니다", "ID를 입력하세요", "error");
        return false;
    }
    if(userpw == ''){
        swal("ERROR: 비밀번호가 입력되지 않았습니다", "비밀번호를 입력하세요", "error");
        return false;
    }
    else{
        return true;
    }
}