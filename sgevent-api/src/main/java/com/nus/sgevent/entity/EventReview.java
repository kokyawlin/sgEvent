package com.nus.sgevent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EventReview {
    @Id
    @GeneratedValue
    private UUID reviewId; // 使用UUID作为主键类型，并自动生成值
    
    private UUID eventId; // 假设EventId也是UUID类型
    private UUID userId;  // 假设UserId也是UUID类型
    private int rating;   // 使用int类型来存储评分，根据需要可以改为double
    private String comment;
}
