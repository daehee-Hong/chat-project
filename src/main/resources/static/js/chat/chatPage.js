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
    fetch("/api/v1/chat/chat-room/" + chatRoomIdx + "/users")
        .then(res => res.text())
        .then(data => {
            const userList = JSON.parse(data);
            makeChatRoomUserList(userList);
        })
    // 채팅 정보
    fetch("/api/v1/chat/chat-room/" + chatRoomIdx + "/chat-message")
        .then(res => res.text())
        .then(data => {
            const chatList = JSON.parse(data);
            makeChatRoomChatList(chatList)
        })

}
function makeChatRoomUserList(userList) {
    let userHtml = document.getElementById('chat-users');
    userList.map(v => {
        let html = `<div class="user ${v.userIdx}">
                                <div class="avatar">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
                                    <div class="status ${v.userLoginStatus === 0 ? 'off' : 'online'}"></div>
                                </div>
                                <div class="name ${v.userStatus === 1 ? 'text-danger' : ''}">${v.nickName}</div>
                                <div class="name">${v.userId}</div>
                           </div>`
        userHtml.innerHTML += html;
    })
}
function makeChatRoomChatList(chatList){
    let chatHtml = document.getElementById('chat-messages');
    let html = '';
    chatList.map(v => {
        html = `<div class="answer ${v.userStatus == 1 ? 'left' : 'right'}">
                                        <div class="avatar">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="User name">
                                        </div>
                                        <div class="name">${v.nickName}</div>
                                        <div class="text">
                                            ${v.message}
                                        </div>
                                        <div class="time">${v.chatRegdate}</div>
                                    </div>`
        chatHtml.innerHTML += html;
    })
    html = `
                                    <div class="answer-add">
                                        <input placeholder="입력 후 엔터를 눌러주세요.">
                                        <span class="answer-btn answer-btn-1"></span>
                                        <span class="answer-btn answer-btn-2"></span>
                                    </div>
    `
    chatHtml.innerHTML += html;
}

