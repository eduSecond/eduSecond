<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>채팅</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .chat-container {
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .chat-header {
            padding: 1rem;
            background-color: #f8f9fa;
            border-bottom: 1px solid #dee2e6;
        }
        .chat-messages {
            flex: 1;
            overflow-y: auto;
            padding: 1rem;
            background-color: #f0f2f5;
        }
        .message {
            margin-bottom: 1rem;
            max-width: 70%;
        }
        .message.sent {
            margin-left: auto;
        }
        .message.received {
            margin-right: auto;
        }
        .message-content {
            padding: 0.5rem 1rem;
            border-radius: 1rem;
            background-color: white;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
        }
        .sent .message-content {
            background-color: #0084ff;
            color: white;
        }
        .chat-input {
            padding: 1rem;
            background-color: white;
            border-top: 1px solid #dee2e6;
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <div class="chat-header">
            <div class="d-flex justify-content-between align-items-center">
                <h5 class="mb-0">${room.otherUserName}</h5>
                <div class="dropdown">
                    <button class="btn btn-light" type="button" data-bs-toggle="dropdown">
                        <i class="bi bi-three-dots-vertical"></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">채팅방 나가기</a></li>
                        <li><a class="dropdown-item" href="#">신고하기</a></li>
                        <li><a class="dropdown-item" href="#">차단하기</a></li>
                    </ul>
                </div>
            </div>
            <small class="text-muted">${room.productName}</small>
        </div>

        <div class="chat-messages" id="messageContainer">
            <c:forEach items="${messages}" var="msg">
                <div class="message ${msg.senderId eq sessionScope.memberInfo.userId ? 'sent' : 'received'}">
                    <div class="message-content">
                        ${msg.message}
                        <small class="text-muted d-block">
                            <fmt:formatDate value="${msg.regDate}" pattern="HH:mm"/>
                        </small>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="chat-input">
            <div class="input-group">
                <input type="text" id="messageInput" class="form-control" placeholder="메시지를 입력하세요...">
                <button class="btn btn-primary" type="button" onclick="sendMessage()">전송</button>
            </div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        let stompClient = null;
        const roomId = ${room.roomId};
        const userId = '${sessionScope.memberInfo.userId}';
        const username = '${sessionScope.memberInfo.userName}';

        function connect() {
            const socket = new SockJS('/ws-stomp');
            stompClient = Stomp.over(socket);
            
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                
                stompClient.subscribe('/topic/chat/room/' + roomId, function(message) {
                    const received = JSON.parse(message.body);
                    displayMessage(received);
                });
            });
        }

        function sendMessage() {
            const messageInput = document.getElementById('messageInput');
            const content = messageInput.value.trim();
            
            if (!content) return;
            
            const message = {
                type: 'CHAT',
                roomId: roomId,
                senderId: userId,
                senderName: username,
                message: content
            };
            
            stompClient.send("/app/chat/message", {}, JSON.stringify(message));
            messageInput.value = '';
        }

        function displayMessage(message) {
            const messageContainer = document.getElementById('messageContainer');
            const messageDiv = document.createElement('div');
            const isSent = message.senderId === userId;
            
            messageDiv.className = `message ${isSent ? 'sent' : 'received'}`;
            messageDiv.innerHTML = `
                <div class="message-content">
                    ${message.message}
                    <small class="text-muted d-block">
                        ${new Date().toLocaleTimeString('ko-KR', {hour: '2-digit', minute:'2-digit'})}
                    </small>
                </div>
            `;
            
            messageContainer.appendChild(messageDiv);
            messageContainer.scrollTop = messageContainer.scrollHeight;
        }

        function leaveChatRoom() {
            if(confirm('채팅방을 나가시겠습니까?')) {
                const message = {
                    type: 'LEAVE',
                    roomId: roomId,
                    senderId: userId,
                    senderName: username
                };
                
                stompClient.send("/app/chat/leave", {}, JSON.stringify(message));
                window.location.href = '/message/list';
            }
        }

        // 페이지 로드 시 연결
        document.addEventListener('DOMContentLoaded', function() {
            connect();
            
            // Enter 키 이벤트 처리
            document.getElementById('messageInput').addEventListener('keypress', function(e) {
                if (e.key === 'Enter') {
                    sendMessage();
                }
            });

            // 채팅방 나가기 버튼에 이벤트 리스너 추가
            document.querySelector('.dropdown-item[href="#"]').addEventListener('click', function(e) {
                e.preventDefault();
                leaveChatRoom();
            });
        });
    </script>
</body>
</html>