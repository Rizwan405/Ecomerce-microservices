package com.rizwan.ecomerce.payment;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer orderId;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column( insertable = false)
    private LocalDateTime lastModifiedDate;

}
