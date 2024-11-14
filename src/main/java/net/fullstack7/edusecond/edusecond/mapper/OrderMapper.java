package net.fullstack7.edusecond.edusecond.mapper;

import net.fullstack7.edusecond.edusecond.dto.order.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    List<OrderListDTO> getOrderList(Map<String, Object> map);
    int totalCount(Map<String, Object> map);
    List<OrderListDTO> getOrderListSold(Map<String, Object> map);
    int totalCountSold(Map<String, Object> map);
}
