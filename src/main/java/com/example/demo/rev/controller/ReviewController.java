package com.example.demo.rev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.example.demo.cmm.domain.Util.string;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cmm.domain.Pagination;
import com.example.demo.cmm.repository.Messenger;
import com.example.demo.rev.domain.Review;
import com.example.demo.rev.repository.ReviewRepository;
import com.example.demo.rev.service.ReviewService;

@RestController
@RequestMapping("/reviews") @RequiredArgsConstructor
public class ReviewController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	final ReviewService reviewService;
	final Pagination page;
	final ReviewRepository reviewRepository;
	
	@PostMapping("")
	public Messenger save(@RequestBody Review review) {
		logger.info("== 리뷰 등록 =="+review.toString());
		reviewRepository.save(review);
		return Messenger.SUCCESS;
	}
 
	@GetMapping("/list")
	public List<Review> findAll() {
		logger.info("== 게시판 목록 ==");
		return reviewRepository.findAll();
	}
	
	@GetMapping("/{reviewNum}")
	public Optional<Review> detail(@PathVariable int reviewNum) {
		logger.info("== 게시글 상세 ==");
		return reviewRepository.findById(reviewNum);
	}
	
	@PutMapping("")
	public Messenger update(@RequestBody Review review) {
		logger.info("== 게시글 수정 =="+review.toString());
		reviewRepository.update(review);
		return Messenger.SUCCESS;
	}
	
	@DeleteMapping("")
	public Messenger delete(@RequestBody Review review) {
		// logger.info("== 게시글 삭제 =="+review.getReviewTitle());
		reviewRepository.delete(review);
		return Messenger.SUCCESS;
	}

	@GetMapping("/count")
	public String count() {
		logger.info(String.format("Count reviews ..."));
		var map = new HashMap<String, String>();
		return string.apply(reviewRepository.count());
	}
	
}