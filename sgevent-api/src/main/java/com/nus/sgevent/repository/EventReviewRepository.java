
package com.nus.sgevent.repository;
import java.util.List;
import com.nus.sgevent.entity.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventReviewRepository extends JpaRepository<EventReview, UUID> {
    // 添加自定义查询方法，例如根据 eventId 查找评价
    List<EventReview> findByEventId(UUID eventId);
}
