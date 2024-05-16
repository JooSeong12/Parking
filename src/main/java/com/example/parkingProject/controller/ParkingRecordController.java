package com.example.parkingProject.controller;


import com.example.parkingProject.dto.ParkingRecordDto;
import com.example.parkingProject.dto.SearchParam;
import com.example.parkingProject.entity.ParkingRecord;
import com.example.parkingProject.service.PaginationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ParkingRecordController {
    @Autowired
    PaginationService paginationService;

    @GetMapping("/parkingRecord")
    public String parkingRecord(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "registerId", direction = Sort.Direction.ASC)Pageable pageable){
        Page<ParkingRecord> paging = paginationService.pagingList(pageable);
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers=paginationService.getPaginationBarNumbers(pageable.getPageNumber(),totalPage);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);
        return "/member/parkingRecord";
    }

    @GetMapping("/parkingRecord/search")
    public String recordSearch(Model model,
                               @RequestParam(required = false, name = "year")Long year,
                               @RequestParam(required = false, name = "month")Long month,
                               @RequestParam(required = false, name = "day")Long day,
                               @RequestParam(required = false, name = "keyword")String keyword,
                               @PageableDefault(page = 0, size = 10, sort = "registerId", direction = Sort.Direction.ASC)Pageable pageable){

        // 파라미터 유지용 dto
        SearchParam searchParam = new SearchParam();
        searchParam.setYear(year);
        searchParam.setMonth(month);
        searchParam.setDay(day);
        searchParam.setCarNumber(keyword);

        Page<ParkingRecord> paging = paginationService.searchRecord(year, month, day, keyword, pageable);
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers=paginationService.getPaginationBarNumbers(pageable.getPageNumber(),totalPage);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);
        model.addAttribute("param", searchParam);
        return "/member/searchRecord";
    }

}
