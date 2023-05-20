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

    const reqOption = {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(sendObject)
    }

    // 로그인시
    fetch("/login", reqOption)
        .then(res => res.json())
        .then(data => {
            console.log('resData : ' + data);
        })
}