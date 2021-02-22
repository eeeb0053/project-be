package com.example.demo.bkg.service;

import java.util.List;

import com.example.demo.bkg.domain.Booking;
import com.example.demo.cmm.domain.Pagination;

public interface BookingService {
	public List<Booking> findAll(Pagination page);
}
