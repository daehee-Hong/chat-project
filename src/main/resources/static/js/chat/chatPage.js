const chatRoomIdx = document.getElementById('chatRoomIdx').value;
$(function(){
    chatPageEvent();
    apiCell();
})

function chatPageEvent() {
    $(".chat").niceScroll();
    $('.chat-scroll-bottom').scrollTop($('.chat-scroll-bottom')[0].scrollHeight);
}

function apiCell() {
    // 채팅방 사용자 정보
    console.log(chatRoomIdx);
    fetch("/api/v1/chat/chat-room-users/" + chatRoomIdx)
        .then(res => res.text())
        .then(data => {
            const userList = JSON.parse(data);
            makeChatRoomUserList(userList);
        })
    // 채팅 정보

}
function makeChatRoomUserList(userList) {
    let userHtml = document.getElementById('chat-users');
    userList.map(v => {
        const userLoginStatus = v.userLoginStatus === 0 ? 'off' : 'online';
        let html = `<div class="user ${v.userIdx}">
                                <div class="avatar">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
                                    <div class="status ${userLoginStatus}"></div>
                                </div>
                                <div class="name ${v.userStatus == 1 ? 'text-danger' : ''}">${v.nickName}</div>
                                <div class="name">${v.userId}</div>
                           </div>`
        userHtml.innerHTML += html;
    })

}

