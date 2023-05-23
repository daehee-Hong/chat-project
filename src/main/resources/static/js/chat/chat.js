window.onload = function() {
    makeChatTable();
    setMainChatRoomPageEvent();
};

function setMainChatRoomPageEvent() {
    document.getElementById('searchText')
        .addEventListener('keyup',  ()=>{
            if (window.event.keyCode == 13) {
                $('#chat-table').DataTable().ajax.reload();            }
        })
}

function makeChatTable() {
    const searchText = document.getElementById('searchText');
    const searchType = document.getElementById('searchType');
    const chatRoomTotal = document.getElementById('chatRoomTotal');
    $('#chat-table').DataTable({
        paging: true,
        searching: false,
        lengthChange : true,          // 페이지 조회 시 row를 변경할 것인지
        pageLength : 1,           // lengthChange가 false인 경우 조회 row 수
        processing : true,
        serverSide : true,        // serverside 사용 여부
        dom: 'rtip',
        bInfo : false,
        scrollX: false,
        ordering: false,
        oLanguage: {
            "sEmptyTable": "검색결과가 존재하지 않습니다."
        },
        ajax : {
            url : "/api/v1/chat/chat-room",
            method : 'POST',
            dataType : 'json',
            contentType : 'application/json',
            data : function(d) {
                d.searchText = searchText.value;
                d.searchType = searchType.value;
                return JSON.stringify(d);
            },
            dataSrc : function (res){
                chatRoomTotal.innerText = res.recordsTotal;
                return res.list;
            }
        },
        rowId : "chatRoomIdx",
        columns : [
            { "data": null,"sortable": false, "className" : "text-center vertical-align-middle",
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {"data": "chatRoomTitle", "className" : "text-center vertical-align-middle"},
            {"data": "nickName", "className" : "text-center vertical-align-middle"},
            {"data": "chatUserCount", "className" : "text-center vertical-align-middle"},
            {"data": "chatRoomRegdate", "className" : "text-center vertical-align-middle"},
            {"data": "chatRoomIdx", "className" : "text-center vertical-align-middle",
                render : function (data, type, row) {
                    let html = '';
                    if (row.isUserIn == 1){
                        html = '<button type="button" class="btn btn-secondary" value=' + data + '>' + "접속" + '</button>'
                    }else {
                        html = '<button type="button" class="btn btn-primary" value=' + data + '>' + "참여" + '</button>'
                    }
                    return html;
                }
            },
        ]
    });
}