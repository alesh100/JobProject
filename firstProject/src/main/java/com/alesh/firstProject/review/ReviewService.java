package com.alesh.firstProject.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long CompanyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
