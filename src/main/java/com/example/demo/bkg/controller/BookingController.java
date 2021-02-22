package com.example.demo.bkg.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bkg.domain.Booking;
import com.example.demo.bkg.repository.BookingRepository;
import com.example.demo.bkg.service.BookingService;
import com.example.demo.cmm.domain.Pagination;
import com.example.demo.cmm.repository.Messenger;

import static com.example.demo.cmm.domain.Util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController @RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	final BookingService bookingService;
	final BookingRepository bookingRepository;
    
    @PostMapping("")
    public Messenger save(@RequestBody Booking booking){
        logger.info("==============예매정보: "+booking.toString());
        bookingRepository.save(booking);
        return Messenger.SUCCESS;
    }
    @GetMapping("/list/{pageSize}/{pageNum}")
    public Map<?, ?> findAll(@PathVariable String pageSize,
    					@PathVariable String pageNum){
    	logger.info("Booking List Execute ...");
    	var map = new HashMap<String, Object>();
    	var page = new Pagination(
				integer.apply(pageSize),
				integer.apply(pageNum),
				bookingRepository.count());
    	map.put("list", bookingService.findAll(page));
    	map.put("page", page);
        return map;
    }
    @GetMapping("/{bookNum}")
    public Optional<Booking> detail(@PathVariable int bookNum){
        return bookingRepository.findById(bookNum);
    }
    @PutMapping("")
    public Messenger update(@RequestBody Booking booking){
    	// logger.info("==============수정할 예약번호: "+booking.getBookNum());
        bookingRepository.update(booking);
        return Messenger.SUCCESS;
    }
    @DeleteMapping("")
    public Messenger delete(@RequestBody Booking booking){
    	// logger.info("==============삭제할 예약번호: "+booking.getBookNum());
        bookingRepository.delete(booking);
        return Messenger.SUCCESS;
    }
}
