window.onload = function() {
    makeChatTable();
};

function makeChatTable() {
        const searchText = document.getElementById('searchText');
        const searchType = document.getElementById('searchType');
        const chatRoomTotal = document.getElementById('chatRoomTotal');
        $('#chat-table').DataTable({
            paging: true,
            searching: false,
            lengthChange : true,          // 페이지 조회 시 row를 변경할 것인지
            pageLength : 5,           // lengthChange가 false인 경우 조회 row 수
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
            drawCallback: function(settings) {
                setMainChatRoomPageEvent();
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
                {"data": "chatRoomStatus", "className" : "text-center vertical-align-middle",},
                {"data": "chatRoomIdx", "className" : "text-center vertical-align-middle",
                    render : function (data, type, row) {
                        let html = '';
                        if (row.isUserIn == 1){
                            html = '<button type="button" class="btn btn-secondary chat-join" value=' + '1_' + row.chatRoomStatus + '_' + data + '>' + "접속" + '</button>'
                        }else {
                            html = '<button type="button" class="btn btn-primary chat-join" value=' + '0_' + row.chatRoomStatus + '_'+data + '>' + "참여" + '</button>'
                        }
                        return html;
                    }
                },
            ]
        });
}

function setMainChatRoomPageEvent() {
    document.getElementById('searchText')
        .addEventListener('keyup',  ()=>{
            if (window.event.keyCode == 13) {
                $('#chat-table').DataTable().ajax.reload();            }
        })
    document.querySelectorAll('.chat-join')
        .forEach(v => {
            v.addEventListener('click', function () {
                const type = this.value.split('_')[0];
                const chatRoomStatus = this.value.split('_')[1];
                const chatRoomIdx = this.value.split('_')[2];

                console.log('type : ', type);
                console.log('chatRoomStatus : ', chatRoomStatus);
                console.log('chatRoomIdx : ', chatRoomIdx);
                
                // 비공개
                if (chatRoomStatus == '비공개'){
                    Swal.fire({
                        title: '비밀번호를 입력해주세요.',
                        input: 'text',
                        inputAttributes: {
                            autocapitalize: 'off'
                        },
                        showCancelButton: true,
                        confirmButtonText: '입력',
                        showLoaderOnConfirm: true,
                        preConfirm: (login) => {
                            return fetch('/api/v1/chat/chat-room-pw-check', reqJsonOption('POST', {chatRoomIdx : chatRoomIdx, chatRoomPw : login}))
                                .then(response => {
                                    return response.json()
                                })
                                .catch(error => {
                                    Swal.showValidationMessage(
                                        `Request failed: ${error}`
                                    )
                                })
                        },
                        allowOutsideClick: () => !Swal.isLoading()
                    }).then((result) => {
                        if (result.value == null) return;
                        const res = result.value;

                        if (res.title == '접속완료'){
                            location.href = "/chat/chat-room/" + chatRoomIdx;
                        }else {
                            sweetAlert('warning', res.title, res.comment, 3000);
                            return;
                        }
                    })
                }else {// 공개
                    location.href = "/chat/chat-room/" + chatRoomIdx;
                }

            })
        })
}
