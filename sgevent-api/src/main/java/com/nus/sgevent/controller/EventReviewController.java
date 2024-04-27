package com.nus.sgevent.controller;

import com.nus.sgevent.entity.EventReview;
import com.nus.sgevent.repository.EventReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")  // 允许所有域名的跨域请求
@RequestMapping(path = "/v1/review")  // 确保一致的基础路径
public class EventReviewController {

    @Autowired
    private EventReviewRepository reviewRepository;

    // 获取所有评价
    @GetMapping
    public List<EventReview> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 根据ID获取单个评价
    @GetMapping("/{id}")
    public ResponseEntity<EventReview> getReviewById(@PathVariable UUID id) {
        Optional<EventReview> review = reviewRepository.findById(id);
        return review.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根据Event ID获取所有相关评价
    @GetMapping("/event/{eventId}")
    public List<EventReview> getReviewsByEventId(@PathVariable UUID eventId) {
        return reviewRepository.findByEventId(eventId);
    }

    // 创建新的评价
    @PostMapping("/add")  // 明确添加路径，与前端调用匹配
    public EventReview createReview(@RequestBody EventReview eventReview) {
        return reviewRepository.save(eventReview);
    }

    // 更新评价
    @PutMapping("/{id}")
    public ResponseEntity<EventReview> updateReview(@PathVariable UUID id, @RequestBody EventReview updatedReview) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setRating(updatedReview.getRating());
                    review.setComment(updatedReview.getComment());
                    review.setEventId(updatedReview.getEventId());
                    review.setUserId(updatedReview.getUserId());
                    return ResponseEntity.ok(reviewRepository.save(review));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 删除评价
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        Optional<EventReview> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
