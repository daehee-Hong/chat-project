window.onload = function() {
    setLoginPageEvent();
};

function setLoginPageEvent() {
    // 회원가입 버튼
    document.getElementById('register')
        .addEventListener('click', register)
    // 회원가입 버튼
    document.getElementById('userIdCheck')
        .addEventListener('click', userIdCheck)
}

function register() {
    let sendObject = {};
    const $userId = document.getElementById('inputId');
    const $userPw = document.getElementById('inputPassword');
    const $userPwTest = document.getElementById('inputPasswordTest');
    const $nickName = document.getElementById('inputNickname');
    const $userIdStatus = document.getElementById('userIdStatus');

    if ($userIdStatus.value !== '1') {
        sweetAlert('error', '유효성에러', 'ID 중복확인을 해주세요', 3000);
        return;
    }
    if ($userId.value == '' || $userId.value == null) {
        sweetAlert('error', '유효성에러', 'ID를 입력해주세요', 3000);
        $userId.focus();
        return
    }else if ($nickName.value == '' || $nickName.value == null) {
        sweetAlert('error', '유효성에러', '닉네임을 입력해주세요', 3000);
        $nickName.focus();
        return
    }else if ($userPw.value == '' || $userPw.value == null) {
        sweetAlert('error', '유효성에러', '비밀번호를 입력해주세요', 3000);
        $userPw.focus();
        return
    }else if ($userPwTest.value == '' || $userPwTest.value == null) {
        sweetAlert('error', '유효성에러', '비밀번호를 전부 입력해주세요', 3000);
        $userPwTest.focus();
        return
    }
    if ($userPw.value !== $userPwTest.value) {
        sweetAlert('error', '유효성에러', '비밀번호가 같지않습니다.', 3000);
        $userPwTest.focus();
        return
    }

    sendObject.userId = $userId.value;
    sendObject.userPw = $userPw.value;
    sendObject.userPwTest = $userPwTest.value;
    sendObject.nickName = $nickName.value;

    console.log('sendObject : ', sendObject);

    // 회원가입시
    fetch("/api/v1/user/user-register", reqJsonOption('POST', sendObject))
        .then(res => res.json())
        .then(data => {
            if (data.status == 400){
                sweetAlert('error', '유효성검사', '값을 전부 입력해주세요', 3000);
                return;
            }else if (data.status == 500){
                sweetAlert('error', '회원가입 실패', '회원가입 중 에러가 발생했습니다.', 3000);
                return;
            }
                const title = data.title;
                const comment = data.comment;

                if (title == '등록완료'){
                    Swal.fire({
                        title: comment,
                        confirmButtonText: '확인',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.href="/user/login";
                        }
                    })
                }else {
                    sweetAlert('warning', title, comment, 3000);
                }
        })
        .catch(res => {
            sweetAlert('error', '연결오류', '서버 연결중 에러가 발생했습니다. \n 인터넷을 확인해주세요.', 3000)
        })
}
function userIdCheck() {
    let sendObject = {};
    const $userId = document.getElementById('inputId');
    let $userIdStatus = document.getElementById('userIdStatus');
    let $userIdCheck = document.getElementById('userIdCheck');

    if ($userId.value == '' || $userId.value == null){
        sweetAlert('error', '유효성에러', 'ID를 입력해주세요', 3000);
        return;
    }

    sendObject.userId = $userId.value;

    // ID 중복확인
    fetch("/api/v1/user/userIdCheck", reqJsonOption('POST', sendObject))
        .then(res => res.json())
        .then(data => {
            if (data.status == 400){
                sweetAlert('error', '유효성검사', 'ID를 입력해주세요', 3000);
                return;
            }else if (data.status == 500){
                sweetAlert('error', '확인실패', '확인중 에러가 발생했습니다.', 3000);
                return;
            }
            if (data == 1){
                sweetAlert('warning', 'ID 중복', '이미 존재하는 ID입니다', 3000);
            }else if (data == 0) {
                sweetAlert('info', '확인완료', '사용가능한 ID입니다', 3000);
                $userIdStatus.value = 1;
                $userIdCheck.disabled = true;
                $userId.disabled = true;
            }else if (data == -1) {
                sweetAlert('error', '확인실패', '확인중 에러가 발생했습니다.', 3000);
            }
        })
        .catch(res => {
            sweetAlert('error', '연결오류', '서버 연결중 에러가 발생했습니다. \n 인터넷을 확인해주세요.', 3000)
        })
}
