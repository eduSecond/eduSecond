package net.fullstack7.edusecond.edusecond.service.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.edusecond.edusecond.domain.PaymentVO;
import net.fullstack7.edusecond.edusecond.dto.payment.PaymentDTO;
import net.fullstack7.edusecond.edusecond.mapper.PaymentMapper;
import net.fullstack7.edusecond.edusecond.mapper.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentServiceIf{
    private final PaymentMapper paymentMapper;
    private final ModelMapper modelMapper;
    @Override
    public void insert(PaymentDTO dto) {
        PaymentVO vo = modelMapper.map(dto, PaymentVO.class);
        paymentMapper.insert(vo);
    }
}
