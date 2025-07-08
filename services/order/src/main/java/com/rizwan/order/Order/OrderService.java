package com.rizwan.order.Order;
import com.rizwan.order.Customer.CustomerClient;
import com.rizwan.order.OrderLine.OrderLineRequest;
import com.rizwan.order.OrderLine.OrderLineService;
import com.rizwan.order.Product.ProductClient;
import com.rizwan.order.Product.PurchaseRequest;
import com.rizwan.order.exception.BusinessException;
import com.rizwan.order.kafka.OrderConfirmation;
import com.rizwan.order.kafka.OrderProducer;
import com.rizwan.order.payment.PaymentClient;
import com.rizwan.order.payment.PaymentRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
//    private final OrderLineRepository orderLineRepo;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    private final OrderMapper mapper;
    public Integer createOrder(@Valid OrderRequest request) {
        //        check the customer -->open feign
        var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(()-> new BusinessException("Can not create order :: no Customer exist with provided id " + request.customerId()));

//        purchase the products -->product ms Rest template
       var purchaseProducts =  this.productClient.purchaseProducts(request.products());

//        persist order


       var order =  this.orderRepository.save(this.mapper.toOrder(request));
//        persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
           orderLineService.saveOrderLine(
                   new OrderLineRequest(
                           null,
                           order.getId(),
                           purchaseRequest.productId(),
                           purchaseRequest.quantity()
                   )

           );
        }
//        start payment process
//        send the order confirmation -->notification -ms (kafka)
        paymentClient.requestOrderPayment(new PaymentRequest(request.id(),request.amount(),request.paymentMethod(),order.getId(),order.getReference(),customer));
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts)
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
       return orderRepository.findById(orderId).map(mapper::fromOrder).orElseThrow(()->new EntityNotFoundException(String.format(  "No Order found with Provided Id: %d ",orderId)));

    }
}
