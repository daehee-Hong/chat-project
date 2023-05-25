$(function(){
    chatPageEvent();
})

function chatPageEvent() {
    $(".chat").niceScroll();
    $('.chat-scroll-bottom').scrollTop($('.chat-scroll-bottom')[0].scrollHeight);
}


