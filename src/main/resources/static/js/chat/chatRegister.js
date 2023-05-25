window.onload = function() {
    setChatRoomRegisterPageEvent();
};
function setChatRoomRegisterPageEvent() {
    // 공개 비공개 상태 변경
    document.querySelectorAll('input[type=radio][name="paymentMethod"]')
        .forEach(v => {
          v.addEventListener('change',function (){
              if (this.value == 1){
                  $('#chatRoomStatus').hide();
                  $('#chatRoomPw').val('');
                  $('#chatRoomPwTest').val('');
              }else {
                  $('#chatRoomStatus').show();
              }
          })
        })
    // 등록
    document.getElementById('chatRoomRegisterBtn')
        .addEventListener('click', chatRoomRegister);
}
function chatRoomRegister() {
    let sendObject = {};
    const chatRoomTitle = document.getElementById('chatRoomTitle').value;
    const chatRoomIntroduce = document.getElementById('chatRoomIntroduce').value;
    const chatRoomStatus = document.querySelector('input[type=radio][name="paymentMethod"]:checked').value;
    const chatRoomPw = document.getElementById('chatRoomPw').value;
    const chatRoomPwTest = document.getElementById('chatRoomPwTest').value;

    if (chatRoomTitle == '' || chatRoomTitle == null){
        sweetAlert('warning', '유효성검사', '채팅방 제목을 입력해주세요.', 3000);
        return
    }else if (chatRoomStatus == '' || chatRoomStatus == null){
        sweetAlert('warning', '유효성검사','채팅방 공개여부를 선택해주세요.', 3000);
        return
    }

    if (chatRoomStatus == 0){
        if ((chatRoomPw == '' || chatRoomPw == null) || (chatRoomPwTest == '' || chatRoomPwTest == null)){
            sweetAlert('warning', '유효성검사','비밀번호를 전부 입력해주세요.', 3000);
            return
        }else if (chatRoomPw !== chatRoomPwTest){
            sweetAlert('warning', '유효성검사','비밀번호가 같지않습니다.', 3000);
            return
        }
    }

    sendObject.chatRoomTitle = chatRoomTitle;
    sendObject.chatRoomIntroduce = chatRoomIntroduce ?? '';
    sendObject.chatRoomStatus = chatRoomStatus ?? 1;
    sendObject.chatRoomPw = chatRoomPw ?? '';
    sendObject.chatRoomPwTest = chatRoomPwTest ?? '';

    fetch("/api/v1/chat/chat-room-register", reqJsonOption('POST', sendObject))
        .then(res => res.json())
        .then(data => {
            const title = data.title;
            const comment = data.comment;

            if (data.status == 400 || data == -1){
                sweetAlert('warning', '유효성검사','항목을 다시확인해주세요.', 3000);
                return;
            }else if(data.status == 500){
                sweetAlert('error', title, comment, 3000);
                return;
            }

            if (title == '등록완료'){
                Swal.fire({
                    title: comment,
                    confirmButtonText: '확인',
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href="/chat/main";
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