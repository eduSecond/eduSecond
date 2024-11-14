package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.dto.order.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<OrderListDTO> getOrderList(Map<String, Object> map);
    int totalCount(Map<String, Object> map);
    List<OrderListDTO> getOrderListSold(Map<String, Object> map);
    int totalCountSold(Map<String, Object> map);
    int confirmPurchase(@Param("productId") int productId, @Param("status") String status);
    int startDelivery(@Param("productId") int productId, @Param("status") String status);
}
