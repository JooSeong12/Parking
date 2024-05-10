package com.example.parkingProject.controller;

import com.example.parkingProject.dto.ParkingStateDto;
import com.example.parkingProject.entity.ParkingState;
import com.example.parkingProject.service.ParkingService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ParkingController {
    @Autowired
    EntityManager em;

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("parkingState")
    public String parkingState(Model model,
                               @PageableDefault(page = 0, size = 2, sort = "stateId",
                                       direction = Sort.Direction.ASC) Pageable pageable){
        parkingService.saveDto();
        Page<ParkingState> paging = parkingService.pagingList(pageable);
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers = parkingService.getPaginationBarNumbers(pageable.getPageNumber(), totalPage);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);

        return "parkingState";
    }
}
