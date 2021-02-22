package com.example.demo.hal.controller;

import static com.example.demo.cmm.domain.Util.string;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.demo.cmm.domain.Pagination;
import com.example.demo.cmm.repository.Messenger;
import com.example.demo.exh.domain.Exhbn;
import com.example.demo.exh.repository.ExhbnRepository;
import com.example.demo.exh.service.ExhbnService;

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

@RestController @RequiredArgsConstructor
@RequestMapping("/halls")
public class HallController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final ExhbnService exhbnService;
    final Pagination page;
    final ExhbnRepository exhbnRepository;

    @PostMapping("")
    public Messenger save(@RequestBody Exhbn exhbn) {
    	logger.info("======== 전시회 등록 ========"+exhbn.toString());
        exhbnRepository.save(exhbn);
    	return Messenger.SUCCESS;
    }
    
    @GetMapping("/list")
    public List<Exhbn> findAll() {
    	logger.info("======== 전시회 목록 ========");
        return exhbnRepository.findAll();
    }
    
    /*
    @GetMapping("/list/{pageSize}/{pageNum}")
    public Map<?,?> list(@PathVariable String pageSize, @PathVariable String pageNum) {
    	logger.info("=========== 목록 진입 ===========");
        var map = new HashMap<String, Object>();
    	var page = new Pagination(
				integer.apply(pageSize),
				integer.apply(pageNum),
				exhbnRepository.count())
				;
    	map.put("list", exhbnService.list(page));
    	map.put("page", page);
        return map;
    }
    
    @GetMapping("/page/{pageSize}/{pageNum}/selectAll")
    public List<?> selectAll(@PathVariable String pageSize, @PathVariable String pageNum){
    	logger.info("exhbns List Execute ...");
    	var map = new HashMap<String, String>();
    	map.put("TOTAL_COUNT", Sql.TOTAL_COUNT.toString() + Table.exhbnS);	
        return exhbnRepository.selectAll(new Pagination(
				Table.exhbnS.toString(), 
				integer.apply(pageSize),
				integer.apply(pageNum),
				commonRepository.totalCount(map)));
    }
    */
    @GetMapping("/{exhbnNum}")
    public Optional<Exhbn> detail(@PathVariable int exhbnNum) {
    	logger.info("======== 전시회 상세 ========");
        return exhbnRepository.findById(exhbnNum);
    }
    
    @PutMapping("")
    public Messenger update(@RequestBody Exhbn exhbn){
    	logger.info("======== 목록 수정 ========"+exhbn.toString());
        exhbnRepository.update(exhbn);
    	return Messenger.SUCCESS;
    }
    
    @DeleteMapping("")
    public Messenger delete(@RequestBody Exhbn exhbn){
    	// logger.info("======== 목록 삭제 ========"+exhbn.getExhbnTitle());
        exhbnRepository.delete(exhbn);
        return Messenger.SUCCESS;
    }
    
    @GetMapping("/count")
    public String count() {
    	logger.info(String.format("Count exhbns ..."));
    	var map = new HashMap<String,String>();
    	return string.apply(exhbnRepository.count());
    }
   
}
