package com.rizwan.order.Order;

import com.rizwan.order.OrderLine.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="customer_id")

public class OrderEntity {
    @Id
    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String customerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false, insertable = false)
    private LocalDateTime lastModifiedDate;
}
