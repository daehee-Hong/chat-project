const chatRoomIdx = document.getElementById('chatRoomIdx').value;
let client;
$(function(){
    chatPageEvent();
    apiCell();
    socketjs();
})

function socketjs(){
    let socket = new SockJS("/ws");
    client = Stomp.over(socket);
    client.connect({}, function (frame) {
        console.log("소켓 연결 : ", frame);
    })

    client.subscribe("/topic/message/" + chatRoomIdx,function (res){
        console.log(JSON.parse(res.body));
        console.log("다시확인 : ", res);
    })
}

function chatPageEvent() {
    $(".chat").niceScroll();

    document.getElementById('sendMessageBtn')
        .addEventListener('keyup', function (){
            if (window.event.keyCode == 13)
                    sendMessage(this.value);
        });
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
function sendMessage(message) {
    // fetch('/api/v1/chat/chat-room/' + chatRoomIdx + "/send-message")
    //     .then(res => res.text())
    //     .then(data => {
    //         console.log('data : ', data);
    //     })
    client.send("/chat/" + chatRoomIdx, {}, JSON.stringify({message : message, userIdx : 12}));
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
                                        <div class="name ${v.userStatus === 1 ? 'text-danger' : ''}">${v.nickName}</div>
                                        <div class="text ">
                                            ${v.message}
                                        </div>
                                        <div class="time">${v.chatRegdate}</div>
                                    </div>`
        chatHtml.innerHTML += html;
    })

    $('.chat-scroll-bottom').scrollTop($('.chat-scroll-bottom')[0].scrollHeight);
}


