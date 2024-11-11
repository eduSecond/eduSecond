package net.fullstack7.edusecond.edusecond.util;

public class SearchTypes {
    public enum ProductSearch {
        ALL("all", "전체"),
        SELLER_ID("sellerId", "판매자"),
        PRODUCT_NAME("productName", "상품명");
        // PRICE_RANGE("priceRange", "가격대"),
        // QUALITY("quality", "상품상태");
        
        private final String code;
        private final String description;
        
        ProductSearch(String code, String description) {
            this.code = code;
            this.description = description;
        }
        
        public String getCode() { return code; }
        public String getDescription() { return description; }
    }
    
    public enum MemberSearch {
        ALL("all", "전체"),
        USER_ID("userId", "회원ID"),
        USER_NAME("userName", "이름"),
        USER_EMAIL("userEmail", "이메일"),
        USER_PHONE("userPhone", "전화번호");
        
        private final String code;
        private final String description;
        
        MemberSearch(String code, String description) {
            this.code = code;
            this.description = description;
        }
        
        public String getCode() { return code; }
        public String getDescription() { return description; }
    }
    
    public enum OrderSearch {
        ALL("all", "전체"),
//        ORDER_ID("orderId", "주문번호"),
        BUYER_ID("buyerId", "구매자"),
        PRODUCT_ID("productId", "상품번호"),
        ORDER_STATUS("orderStatus", "주문상태"),
        DELIVERY_STATUS("deliveryStatus", "배송상태");
        
        private final String code;
        private final String description;
        
        OrderSearch(String code, String description) {
            this.code = code;
            this.description = description;
        }
        
        public String getCode() { return code; }
        public String getDescription() { return description; }
    }
} 