window.onload = function() {
    setLoginPageEvent();
};

function setLoginPageEvent() {
    // 로그인 버튼
    document.getElementById('login')
        .addEventListener('click', login)
    // 회원가입 버튼
    document.getElementById('joinMember')
        .addEventListener('click', () => {
            location.href="/user/register";
        })
}

function login() {
    let sendObject = {};
    const userId = document.getElementById('inputId').value;
    const userPw = document.getElementById('inputPassword').value;

    if (userId == '' || userId == null) {
        alert('ID를 입력해주세요.');
        return
    }else if (userPw == '' || userPw == null) {
        alert('PW를 입력해주세요.');
        return
    }

    sendObject.userId = userId;
    sendObject.userPw = userPw;

    console.log('sendObject : ', sendObject);

    // 로그인시
    fetch("/api/v1/user/login", reqJsonOption('POST', sendObject))
        .then(res => res.json())
        .then(data => {
            if (data.status == 400){
                sweetAlert('error', '유효성검사', '빈칸을 입력해주세요.', 3000);
                return;
            }
            if (data == 0){
                sweetAlert('waring', '유효성검사', 'ID 또는 비밀번호가 다릅니다.', 3000);
                return;
            }else if (data == -1){
                sweetAlert('waring', '에러발생', '로그인 중 에러가 발생했습니다.', 3000);
                return;
            }else if (data == 1){
                location.href = "/chat/main";
            }
            console.log('resData : ' + data);
        })
        .catch(res => {
            sweetAlert('error', '연결오류', '서버 연결중 에러가 발생했습니다. \n 인터넷을 확인해주세요.', 3000)
        })
}