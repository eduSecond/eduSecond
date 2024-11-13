<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            width: 100vw;
            overflow: hidden;
        }

        .chat-container {
            width: 100%;
            height: 100vh;
            background-color: #b2c7d9;
            display: flex;
            flex-direction: column;
        }

        .chat-header {
            background-color: #a1b1c1;
            padding: 10px 20px;
            border-bottom: 1px solid #ddd;
        }

        .chat-header .btn-close {
            color: #000;
            opacity: 0.7;
        }

        .chat-header .btn-close:hover {
            opacity: 1;
        }

        .chat-header h3 {
            margin: 0;
            color: #000;
        }

        .product-info {
            background-color: #ffffff;
            padding: 15px 20px;
            border-bottom: 1px solid #ddd;
        }

        .product-info .product-content {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .product-info img {
            width: 50px;
            height: 50px;
            object-fit: cover;
            border-radius: 5px;
        }

        .product-info h4 {
            margin: 0;
            font-size: 1rem;
            flex-grow: 1;
        }

        .product-info .btn-product {
            background-color: #fef01b;
            border: none;
            color: #000;
            padding: 5px 15px;
            border-radius: 5px;
            font-size: 0.9rem;
        }

        .product-info .btn-product:hover {
            background-color: #e6d819;
        }

        .chat-messages {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
        }

        .message {
            margin-bottom: 20px;
            display: flex;
            align-items: flex-start;
            position: relative;
        }

        .message.sent {
            justify-content: flex-end;
        }

        .message-content {
            max-width: 60%;
            padding: 10px 15px;
            border-radius: 15px;
            position: relative;
        }

        .received .message-content {
            background-color: #ffffff;
        }

        .sent .message-content {
            background-color: #fff430;
        }

        .message-info {
            display: flex;
            align-items: flex-end;
            gap: 4px;
            position: absolute;
            bottom: 0;
            font-size: 0.7rem;
            color: #888;
            white-space: nowrap;
        }

        .sent .message-info {
            right: 100%;
            margin-right: 5px;
            display: flex;
            flex-direction: row-reverse;
            align-items: center;
            gap: 4px;
        }

        .received .message-info {
            left: 100%;
            margin-left: 5px;
        }

        .read-status {
            color: #6c757d;
        }

        .unread-status {
            color: #fef01b;
        }

        .chat-input {
            width: 100%;
            padding: 20px;
            background-color: #ffffff;
            border-top: 1px solid #ddd;
        }

        .chat-input .input-group {
            max-width: 100%;
        }

        .sender-name {
            font-size: 0.9rem;
            margin-bottom: 5px;
            color: #000;
        }

        .btn-send {
            background-color: #fef01b;
            border: none;
            color: #000;
        }

        .btn-send:hover {
            background-color: #e6d819;
        }

        /* 메시지 그룹화 */
        .message-group {
            margin-bottom: 20px;
        }

        .message-group .sender-name {
            margin-bottom: 8px;
        }

        .message-group .message {
            margin-bottom: 5px;
        }

        .message-group .message:last-child {
            margin-bottom: 0;
        }

        /* 데스크톱 화면에서는 최대 너비 제한 */
        @media (min-width: 800px) {
            .chat-container {
                width: 800px;
                margin: 0 auto;
            }
        }

        /* 드롭다 메뉴 스타일 */
        .dropdown-menu {
            min-width: 100px;
            padding: 0.5rem 0;
            margin: 0;
            background-color: #fff;
            border: 1px solid rgba(0,0,0,.15);
            border-radius: 0.25rem;
        }

        .dropdown-item {
            padding: 0.5rem 1rem;
            color: #212529;
            text-align: left;
            background-color: transparent;
            border: 0;
        }

        .dropdown-item:hover {
            background-color: #f8f9fa;
        }

        .dropdown-item.text-danger:hover {
            background-color: #fff5f5;
        }

        /* 날짜 구분선 스타일 */
        .chat-date {
            text-align: center;
            margin: 20px 0;
            position: relative;
        }

        .chat-date span {
            background-color: rgba(0, 0, 0, 0.1);
            padding: 5px 15px;
            border-radius: 15px;
            font-size: 0.8rem;
            color: #000;
            display: inline-block;
        }

        /* 헤더 내부 요소들 정렬 */
        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-left {
            display: flex;
            align-items: center;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <!-- 채팅방 헤더 -->
        <div class="chat-header">
            <div class="header-content">
                <div class="header-left">
                    <button type="button" class="btn" onclick="window.close()">
                        <i class="bi bi-x-lg"></i>
                    </button>
                </div>
                <div class="dropdown">
                    <button class="btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-three-dots-vertical"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><button class="dropdown-item" type="button">채팅방 나가기</button></li>
                        <li><button class="dropdown-item" type="button">신고하기</button></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><button class="dropdown-item text-danger" type="button">차단하기</button></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- 상품 정보 영역 -->
        <div class="product-info">
            <div class="product-content">
                <img src="${product.thumbnail.imagePath}" alt="상품 이미지">
                <h4>2023 갤럭시탭A9 플러스 11인치 64GB WIFI SM-X210</h4>
                <button class="btn-product" onclick="location.href='/product/view?productId=${productId}'">
                    제품상세
                </button>
            </div>
        </div>

        <!-- 채팅 메시지 영역 -->
        <div class="chat-messages">
            <!-- 11월 10일 -->
            <div class="chat-date">
                <span>2024년 11월 10일 일요일</span>
            </div>

            <div class="message-group">
                <div class="sender-name">강감찬</div>
                <div class="message received">
                    <div class="message-content">
                        안녕하세요 갤럭시탭 아직 파시나요?
                        <div class="message-info">
                            <span class="message-time">오후 2:30</span>
                            
                        </div>
                    </div>
                </div>
            </div>

            <div class="message-group">
                <div class="message sent">
                    <div class="message-content">
                        네 아직 판매중입니다
                        <div class="message-info">
                            <span class="message-time">오후 2:35</span>
                            <span class="read-status">읽음</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 11월 11일 -->
            <div class="chat-date">
                <span>2024년 11월 11일 월요일</span>
            </div>

            <div class="message-group">
                <div class="sender-name">강감찬</div>
                <div class="message received">
                    <div class="message-content">
                        네고 가능할까요?
                        <div class="message-info">
                            <span class="message-time">오전 10:15</span>
                            
                        </div>
                    </div>
                </div>
                <div class="message received">
                    <div class="message-content">
                        5만원 깎아주시면 바로 구매하고 싶습니다
                        <div class="message-info">
                            <span class="message-time">오전 10:16</span>
                            
                        </div>
                    </div>
                </div>
            </div>

            <div class="message-group">
                <div class="message sent">
                    <div class="message-content">
                        죄송하지만 가격 네고는 어렵습니다
                        <div class="message-info">
                            <span class="message-time">오전 11:20</span>
                            <span class="read-status">읽음</span>
                        </div>
                    </div>
                </div>
                <div class="message sent">
                    <div class="message-content">
                        거의 새 제품이라 이 가격이 최선입니다
                        <div class="message-info">
                            <span class="message-time">오전 11:20</span>
                            <span class="read-status">읽음</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 11월 12일 -->
            <div class="chat-date">
                <span>2024년 11월 12일 화요일</span>
            </div>

            <div class="message-group">
                <div class="sender-name">강감찬</div>
                <div class="message received">
                    <div class="message-content">
                        알겠습니다 그럼 내일 직거래 가능하신가요?
                        <div class="message-info">
                            <span class="message-time">오후 3:15</span>
                            
                        </div>
                    </div>
                </div>
            </div>

            <div class="message-group">
                <div class="message sent">
                    <div class="message-content">
                        네 가능합니다
                        <div class="message-info">
                            <span class="message-time">오후 3:30</span>
                            <span class="unread-status">1</span>
                        </div>
                    </div>
                </div>
                <div class="message sent">
                    <div class="message-content">
                        강남역 어떠신가요?
                        <div class="message-info">
                            <span class="message-time">오후 3:31</span>
                            <span class="unread-status">1</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="message-group">
                <div class="sender-name">강감찬</div>
                <div class="message received">
                    <div class="message-content">
                        좋습니다! 저녁 7시에 2번 출구 앞에서 뵐게요
                        <div class="message-info">
                            <span class="message-time">오후 4:00</span>
                            
                        </div>
                    </div>
                </div>
            </div>

            <div class="message-group">
                <div class="message sent">
                    <div class="message-content">
                        네 알겠습니다 :)
                        <div class="message-info">
                            <span class="message-time">오후 4:05</span>
                            <span class="read-status">읽음</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 입력창 -->
        <div class="chat-input">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="메시지 입력...">
                <button class="btn btn-send" type="button">전송</button>
            </div>
        </div>
    </div>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // 드롭다운 메뉴 클릭 이벤트 처리
            document.querySelectorAll('.dropdown-item').forEach(item => {
                item.addEventListener('click', function() {
                    const action = this.textContent;
                    switch(action) {
                        case '채팅방 나가기':
                            if(confirm('채팅방을 나가시겠습니까?')) {
                                // 채팅방 나가기 처리
                                console.log('채팅방 나가기');
                            }
                            break;
                        case '신고하기':
                            // 신고 처리
                            console.log('신고하기');
                            break;
                        case '차단하기':
                            if(confirm('이 사용자를 차단하시겠습니까?')) {
                                // 차단 처리
                                console.log('차단하기');
                            }
                            break;
                    }
                });
            });
        });
    </script>
</body>
</html>