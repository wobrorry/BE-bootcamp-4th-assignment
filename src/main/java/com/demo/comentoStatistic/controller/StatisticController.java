package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.DayCountDto;
import com.demo.comentoStatistic.dto.OrganizationCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value="/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year){
        return ResponseEntity.ok(statisticService.getYearLogins(year));
    }

    @RequestMapping(value="/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearMonthCountDto> getYearMonthLoginCount(
            @PathVariable("year") String year, @PathVariable("month") String month) {
        return ResponseEntity.ok(statisticService.getYearMonthLogins(year + month));
    }

    @RequestMapping(value="/login/{yearMonth}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMonthlyLoginCount(
            @PathVariable("yearMonth") String yearMonth) {

        YearMonthCountDto result = statisticService.getYearMonthLogins(yearMonth);
        Map<String, Object> response = new HashMap<>();
        response.put("totCnt", result.getTotCnt());
        response.put("yearMonth", yearMonth);
        response.put("requestlog", "L");
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/login/{yearMonth}/{date}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDailyLoginCount(
            @PathVariable("yearMonth") String yearMonth,
            @PathVariable("date") String date) {

        DayCountDto result = statisticService.getDayLogins(yearMonth, date);
        Map<String, Object> response = new HashMap<>();
        response.put("totCnt", result.getTotCnt());
        response.put("yearMonth", yearMonth);
        response.put("date", date);
        response.put("requestlog", "L");
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/login/{yearMonth}/average", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMonthlyAverageLoginCount(
            @PathVariable("yearMonth") String yearMonth) {

        double average = statisticService.getMonthlyAverageLogins(yearMonth);
        Map<String, Object> response = new HashMap<>();
        response.put("averageLoginCount", average);
        response.put("yearMonth", yearMonth);
        response.put("requestlog", "L");
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/login/{yearMonth}/withoutHolidays", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMonthlyLoginsWithoutHolidays(
            @PathVariable("yearMonth") String yearMonth) {

        int count = statisticService.getMonthlyLoginsWithoutHolidays(yearMonth);
        Map<String, Object> response = new HashMap<>();
        response.put("totCnt", count);
        response.put("yearMonth", yearMonth);
        response.put("requestlog", "L");
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/login/{yearMonth}/{organization}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getMonthlyLoginCountByOrganization(
            @PathVariable("yearMonth") String yearMonth,
            @PathVariable("organization") String organization) {

        OrganizationCountDto result = statisticService.getMonthlyLoginsByOrganization(yearMonth, organization);
        Map<String, Object> response = new HashMap<>();
        response.put("totCnt", result.getTotCnt());
        response.put("organization", organization);
        response.put("yearMonth", yearMonth);
        response.put("requestlog", "L");
        response.put("is_success", true);
        return ResponseEntity.ok(response);
    }
}
