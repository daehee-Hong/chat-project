window.onload = function() {
    setLoginPageEvent();
};

function setLoginPageEvent() {

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

    const reqOption = {
        method : 'POST',
        headers : {
            'Context-Type' : 'application/json'
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