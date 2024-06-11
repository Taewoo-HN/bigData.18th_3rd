// 전송 처리
function formsignSubmit() {
    const form = document.querySelector('#signupForm');
    if (isValidsignCode(form)) {
        form.submit();
    }
}

// 유효성 검사
function isValidsignCode(form) {
    const userid = form.user_id.value;
    const userpw = form.user_pw.value;
    const username = form.user_name.value;
    const email = form.email.checked;

    if(userid == ''){
        swal("에러: ID는 필수입니다!", "ID를 꼭 써주세요!", "error");
        return false;
    }
    if(userpw == ''){
        swal("에러: 비밀번호는 필수입니다!", "비밀번호를 꼭 써주세요!", "error");
        return false;
    }
    if(username == ''){
        swal("에러: 이름은 필수입니다!", "이름을 꼭 써주세요!", "error");
        return false;
    }
    if(email == ''){
        swal("에러: 이메일은 필수입니다!", "이메일을 넣어주세요", "error");
        return false;
    }
    else{
        return true;
    }
}