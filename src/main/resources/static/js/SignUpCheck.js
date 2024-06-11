// 전송 처리
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
    const username = form.username.value;
    const agree = form.agreement.checked;

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
    if(agree == false){
        swal("약관에 동의하지 않으셨네요!", "약관에 동의해주세요!", "error");
        return false;
    }
    else{
        return true;
    }
}