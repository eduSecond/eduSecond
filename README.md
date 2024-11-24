# 프로젝트 명 : 당신마켓 ( EduSecond )


## 📖 프로젝트 소개
> 선생님을 위한 중고거래 사이트 당신마켓
- 선생님을 위한 당신마켓은 중고거래가 필요한 교사가 손쉽게 필요한 물품들을 중고거래 할 수 있도록 도와주는 사이트입니다.

## 👥 팀원 소개
|이름|역할|담당 기능|GitHub|
|---|---|---|---|
|한인규|팀장|• 상품 결제<br>• 거래 후기<br>• 마이페이지|[@GitHub](https://github.com/Haninqq)|
|강경민|팀원|• 관리자 페이지<br>• DB 설계<br>• 채팅<br>• 필터|[@GitHub](https://github.com/GyeongMin2)|
|송수미|팀원|• 페이징 공통모듈 설계<br>• 상품 상세<br>• 찜목록<br>• 마이페이지|[@GitHub](https://github.com/SumiSong)|
|최사랑|팀원|• 프론트엔드 퍼블리싱<br>• 회원가입 및 로그인<br>• 회사 소개<br>• 공지 사항|[@GitHub](https://github.com/coeisarang)|

## 📅 프로젝트 일정

| 단계     | 11/11 | 11/12 | 11/13 | 11/14 | 11/15 | 11/16 | 11/17 |
| ------ | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| 요구분석   | ✓     |       |       |       |       |       |       |
| DB 설계  |       | ✓     | ✓     |       |       |       |       |
| 기능 명세서 |       |       | ✓     |       |       |       |       |
| 개발     |       | ✓     | ✓     | ✓     | ✓     | ✓     |       |
| 테스트&배포 |       |       |       |       |       | ✓     | ✓     |

## 🛠 개발 환경
### Frontend
- JSP
- JavaScript
- Bootstrap 5

### Backend
- Java
- Spring Framework
- Maven

### Database & Server
- MariaDB & MySQL
- Apache Tomcat 9.0.96

### Tools
- Git & GitHub
- IntelliJ IDEA

## 💡주요 기능
### 1. 회원 관리 (담당 : 최사랑)
- 회원가입
- 로그인
- 로그아웃
- 정보 수정

### 2. 상품 관리 (담당 : 한인규, 송수미)
- 상품 등록 수정 삭제
- 상품 상세
- 상품 결제
- 상품 찜
- 거래 후기

### 3. 채팅 관리 (담당 : 강경민)
- 채팅
- 채팅 목록
- 채팅 상세

### 4. 관리자 페이지 (담당 : 강경민)
- 공지사항 등록 수정 삭제
- 회원 관리
- 상품 관리

### 5. 페이징 모듈 (담당 : 송수미)
- 백엔드 페이징 모듈
- 프론트엔드 페이징 모듈

## 📊 ERD & Class Diagram & 화면 설계서
![ERD](https://github.com/GyeongMin2/MyPortfolio/blob/main/images/eduSecond/eduSecond_ERD.png)
![Class Diagram](https://github.com/GyeongMin2/MyPortfolio/blob/main/images/eduSecond/eduSecond_classdiagram.png)
![화면 설계서](https://github.com/GyeongMin2/MyPortfolio/blob/main/images/eduSecond/Screen_Design_Document.jpg)

## 💻 주요 코드

### 한인규
#### 상품 결제 구현
- 프로시저를 활용한 결제 처리
```sql
DELIMITER //

CREATE PROCEDURE reductAfterPayment(IN p_productId INT, IN orderQuantity INT)
BEGIN
    DECLARE availableQuantity INT;

    -- 현재 재고 확인
    SELECT quantity INTO availableQuantity
    FROM tbl_product
    WHERE productId = p_productId;

    -- 재고가 충분한지 확인
    IF availableQuantity >= orderQuantity THEN
        -- 재고 감소
        UPDATE tbl_product
        SET quantity = quantity - orderQuantity
        WHERE productId = p_productId;

        -- 감소 후 재고 확인 및 상태 변경
        IF (availableQuantity - orderQuantity) = 0 THEN
            UPDATE tbl_product
            SET productStatus = 'SOLDOUT'
            WHERE productId = p_productId;
        END IF;

    ELSE
        -- 재고 부족 시 예외 발생
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient stock';
    END IF;
END //

DELIMITER ;
```
```xml
<update id="reductionAfterPayment">
    CALL reductAfterPayment(#{productId}, #{orderQuantity})
</update>
```
```java
@GetMapping("/confirm")
    public String confirm(@RequestParam("paymentNumber") String paymentNumber,
                                @RequestParam(defaultValue = "1") int pageNo, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        int result = orderMapper.confirm(paymentNumber);

        if (result > 0) {
            PaymentDTO paymentDTO = paymentMapper.getPaymentInfo(paymentNumber);
            int result1 = productMapper.reductionAfterPayment(paymentDTO); // 상품정보에서 개수 차감하는 메서드

            if(result1 >0){
                JSFunc.alertLocation("구매 수락/상품 개수 차감.","/es/mypage/orderList_1?pageNo="+pageNo, response);
            } else{
                JSFunc.alertLocation("구매 수락/상품 개수 차감 오류. 수정 필요","/es/mypage/orderList_1?pageNo="+pageNo, response);
            }
        } else {
            JSFunc.alertBack("구매 수락 실패", response);
        }
        return null;
    }
```

### 최사랑
#### 반응형 헤더 구현
```css
@media (max-width: 1000px) {
  .menu-toggle {
    display: block;
  }

  .navbar nav ul {
    display: none; /* Hide by default */
    flex-direction: column;
    gap: 0;
    background-color: #ffffff;
    position: absolute;
    top: 60px;
    left: 0;
    width: 100%;
    padding: 10px;
    border-top: 1px solid #ffffff;
  }

  .navbar nav ul.show {
    display: flex;
    z-index: 1000;
  }

  .navbar nav ul li {
    padding: 10px 0;
  }
}
```

#### 네이버 지도 API 연동
- 회사 위치 표시를 위한 네이버 지도 API 구현
- 마커 커스텀 및 지도 옵션 설정
- 반응형 지도 크기 조절

```javascript
// 네이버 지도 API 초기화 및 설정
var mapOptions = {
    center: new naver.maps.LatLng(37.4787, 126.8866),
    zoom: 15
};

var map = new naver.maps.Map('map', mapOptions);

// 커스텀 마커 생성
var marker = new naver.maps.Marker({
    position: new naver.maps.LatLng(37.4787, 126.8866),
    map: map,
    icon: {
        url: 'https://path-to-your-arrow-icon.png',
        size: new naver.maps.Size(50, 50),
        scaledSize: new naver.maps.Size(50, 50),
        origin: new naver.maps.Point(0, 0),
        anchor: new naver.maps.Point(25, 50)
    }
});
```

```html
<!-- 지도를 표시할 HTML 요소 -->
<div class="timeline">
    <h2>찾아오는 길</h2>
    <hr>
    <div class="banner">
        <div id="map" style="width:100%;height:400px;"></div>
    </div>
    <p>
        도로명 : 서울 금천구 벚꽃로 266 마리오아울렛3관
        지번 : 가산동 60-20 우편번호 : 08511
    </p>
    <p style="text-align: left">
        17가산디지털단지역 3번 출구에서 247m
    </p>
</div>
```

### 강경민
#### WebSocket 채팅 구현
- WebSocket을 활용한 실시간 양방향 채팅 구현
- 하이브리드 통신 방식으로 안정성 확보
- 읽음 처리 및 채팅방 관리 기능

```java
@Override
protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    try {
        String roomId = session.getUri().getQuery().split("=")[1];
        ChatMessageDTO chatMessage = gson.fromJson(message.getPayload(), ChatMessageDTO.class);
        
        chatService.saveMessage(chatMessage);
        
        Map<WebSocketSession, String> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            String messageJson = gson.toJson(chatMessage);
            sessions.keySet().forEach(s -> {
                try {
                    if (s.isOpen()) {
                        s.sendMessage(new TextMessage(messageJson));
                    }
                } catch (Exception e) {
                    log.error("메시지 전송 실패", e);
                }
            });
        }
    } catch (Exception e) {
        log.error("메시지 처리 중 에러 발생", e);
    }
}
```

#### 채팅방 목록 조회 쿼리
- 읽지 않은 메시지 수 실시간 반영
- 최신 메시지 기준 정렬
- 사용자별 활성화된 채팅방만 조회

```sql
SELECT 
    c.roomId, c.productId, c.sellerId, c.buyerId,
    p.productName, pi.imagePath as productImage,
    m.userName as otherUserName,
    lcm.message as lastMessage,
    lcm.regDate as lastMessageDate,
    COALESCE((
        SELECT COUNT(*) 
        FROM tbl_chat_message 
        WHERE roomId = c.roomId 
        AND isRead = 'N' 
        AND senderId != #{userId}
    ), 0) as unreadCount
FROM tbl_chatroom c
LEFT JOIN tbl_product p ON c.productId = p.productId
LEFT JOIN tbl_product_image pi ON p.productId = pi.productId 
    AND pi.isMain = 'Y'
WHERE (c.sellerId = #{userId} AND c.sellerActive = 'Y') 
   OR (c.buyerId = #{userId} AND c.buyerActive = 'Y')
ORDER BY lcm.regDate DESC
```

#### 프론트엔드 WebSocket 연결
- 자동 재연결 및 에러 처리
- 하이브리드 통신 구현으로 안정성 확보

```javascript
function connect() {
    ws = new WebSocket(`ws://${window.location.host}/es/ws/chat?roomId=${roomId}`);
    ws.onmessage = onMessage;
    ws.onerror = (error) => {
        console.error('WebSocket Error:', error);
    };
}

function sendMessage() {
    const message = messageInput.value.trim();
    if (!message) return;
    
    const chatMessage = {
        type: 'CHAT',
        roomId: roomId,
        senderId: userId,
        message: message
    };

    // WebSocket 또는 HTTP 통신
    if (ws?.readyState === WebSocket.OPEN) {
        ws.send(JSON.stringify(chatMessage));
    } else {
        fetch('/message/send', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(chatMessage)
        });
    }
}
```
### 송수미
#### 페이징 모듈 구현
- 백엔드 페이징 모듈
- 프론트엔드 페이징 모듈
```java
public class Paging {
    private int pageNo;
    private int pageSize;
    private int blockSize;
    private int totalCnt;
    public int getStartIdx(){
        return  (pageNo - 1) * pageSize;
    }

    public int getTotalPage(){
        return  (int) Math.ceil((double) totalCnt / pageSize);
    }
    public int getStartBlockPage(){
        return ((pageNo - 1) / blockSize) * blockSize + 1;
    }
    public int getEndBlockPage() {
        int endPage = getStartBlockPage() + blockSize - 1;
        return Math.min(endPage, getTotalPage());
    }
    public boolean getPrevBlock () {
        return getStartBlockPage() > 1;
    }
    public boolean getNextBlock () {
        return getEndBlockPage() < getTotalPage();
    }
}
```
```java
private boolean validateListParameters(int pageNo, String searchCategory,
                                        String searchValue, HttpServletResponse response) {
        if (pageNo < 1) {
            JSFunc.alertBack("페이지 번호는 1 이상이어야 합니다.", response);
            return false;
        }
        
        if (searchCategory != null && !searchCategory.trim().isEmpty()
            && searchValue != null && !searchValue.trim().isEmpty()) {
            if (!("productName".equals(searchCategory) || "sellerId".equals(searchCategory))) {
                JSFunc.alertBack("유효하지 않은 검색 카테고리입니다: " + searchCategory, response);
                return false;
            }
        }
        return true;
    }
if (!validateListParameters(pageNo, searchCategory, searchValue, response)) {
                return null;
            }
```


## 🎯 트러블 슈팅

### 1. 제고 관리 로직 최적화 (담당자 : 한인규)
#### 문제상황
- 결제 시 재고 수량 체크와 상태 변경을 위해 DB에 중복 접근 발생
- 동시 주문 시 재고 관리의 일관성 보장 필요

#### 해결 방안
- 프로시저를 활용한 재고 관리 로직 최적화

#### 개선 효과
- DB 접근 횟수 감소로 성능 향상
- 재고 관리 로직 중앙화로 유지보수성 향상
***
### 2. 리눅스 환경 파일 업로드 경로 문제 (담당자 : 강경민)
#### 문제 상황
- 윈도우 환경에서 개발된 파일 업로드 기능이 리눅스 서버 배포 후 작동 불가
- Tomcat 내부 디렉토리 사용 시도 실패

#### 해결 방법
- 외부 디렉토리 경로 설정 및 file 프로토콜 적용
```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 외부 경로 파일 업로드 설정
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:///home/gyeongmini/uploads/")
            .setCachePeriod(3600);
            // 브라우저 캐시 설정을 통해 성능 최적화
    // 정적 리소스 설정
    registry.addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
}
```

#### 개선 효과
- 운영체제 독립적인 파일 업로드 처리 가능
- 파일 접근 보안 강화
- 캐시 설정으로 성능 최적화
***
### 3. 지도 API 연동 문제 (담당자 : 최사랑)
#### 문제 상황
- 기존 API 등록을 localhost로 등록하여 배포 후 지도 표시 불가

#### 해결 방법
- 네이버 지도 API 등록을 배포시 도메인으로 변경하여 지도 표시 문제 해결

#### 개선 효과
- 배포 후에도 지도 표시 가능
- 지도 API 연동 문제 해결
***
