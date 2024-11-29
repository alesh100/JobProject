package com.alesh.firstProject.review.impl;

import com.alesh.firstProject.company.Company;
import com.alesh.firstProject.company.CompanyService;
import com.alesh.firstProject.review.Review;
import com.alesh.firstProject.review.ReviewRepository;
import com.alesh.firstProject.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReview(Long CompanyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(CompanyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
       Company companyIsAvalaible = companyService.getCompanyById(companyId);
        if (companyIsAvalaible != null){
                updateReview.setCompany(companyService.getCompanyById(companyId));
                updateReview.setId(reviewId);
                reviewRepository.save(updateReview);
                return true;
    }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null &&
        reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return  true;
        }
        return false;
    }
}
