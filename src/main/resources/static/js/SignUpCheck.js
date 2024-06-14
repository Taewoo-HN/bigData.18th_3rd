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
        swal("ERROR: ID칸이 비어있습니다.", "ID칸을 기입해주세요", "error");
        return false;
    }
    if(userpw == ''){
        swal("ERROR: 비밀번호칸이 비어있습니다", "비밀번호칸을 기입해주세요!", "error");
        return false;
    }
    if(username == ''){
        swal("ERROR: 이름칸이 비어있습니다", "이름칸을 기입해주세요!", "error");
        return false;
    }
    if(email == ''){
        swal("ERROR: 이메일칸이 비어있습니다.", "이메일칸을 채워주세요", "error");
        return false;
    }
    else{
        return true;
    }
}