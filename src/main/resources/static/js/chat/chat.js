window.onload = function() {
    makeChatTable();
};

function makeChatTable() {
    let sendObject = {};
    sendObject.search = '123';
    $('#chat-table').DataTable({
        paging: true, // Enable pagination
        searching: false, // Enable searching
        lengthChange : true,          // 페이지 조회 시 row를 변경할 것인지
        pageLength : 15,           // lengthChange가 false인 경우 조회 row 수
        processing : true,
        serverSide : true,        // serverside 사용 여부
        ajax : {
            url : "/api/v1/chat/chat-room",
            method : 'POST',
            dataType : 'json',
            contentType : 'application/json',
            data : function(d) {
                d.searchText = '1234';
                return JSON.stringify(d);
            },
            dataSrc : function (res){
                return res;
            }
        },
    });
}