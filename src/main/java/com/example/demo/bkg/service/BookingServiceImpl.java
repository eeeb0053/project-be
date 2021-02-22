package com.example.demo.bkg.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.demo.bkg.domain.Booking;
import com.example.demo.bkg.repository.BookingRepository;
import com.example.demo.cmm.domain.Pagination;

@Service @RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
	final BookingRepository bookingRepository;
	public List<Booking> findAll(Pagination page) {
		return null; /*bookingRepository.findAll().stream()
				.sorted(comparing(Booking::getBookNum).reversed())
				.skip(page.getPageSize() * (page.getPageNum()-1))
				.limit(page.getPageSize())
				.collect(Collectors.toList());*/
	}
}
