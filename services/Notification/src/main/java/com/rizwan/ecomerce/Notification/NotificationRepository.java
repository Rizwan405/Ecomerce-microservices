package com.rizwan.ecomerce.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<NotificationEntity,String> {
}
