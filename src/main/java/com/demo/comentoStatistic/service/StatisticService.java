package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.DayCountDto;
import com.demo.comentoStatistic.dto.OrganizationCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    public YearCountDto getYearLogins(String year) {
        return statisticMapper.selectYearLogin(year);
    }

    public YearMonthCountDto getYearMonthLogins(String yearMonth) {
        return statisticMapper.selectYearMonthLogin(yearMonth);
    }

    public DayCountDto getDayLogins(String yearMonth, String date) {
        Map<String, String> params = new HashMap<>();
        params.put("yearMonth", yearMonth);
        params.put("date", date);
        return statisticMapper.selectDayLogin(params);
    }

    public double getMonthlyAverageLogins(String yearMonth) {
        return statisticMapper.selectMonthlyAverageLogin(yearMonth);
    }

    public int getMonthlyLoginsWithoutHolidays(String yearMonth) {
        return statisticMapper.selectMonthlyLoginWithoutHolidays(yearMonth);
    }

    public OrganizationCountDto getMonthlyLoginsByOrganization(String yearMonth, String organization) {
        Map<String, String> params = new HashMap<>();
        params.put("yearMonth", yearMonth);
        params.put("organization", organization);
        return statisticMapper.selectMonthlyLoginByOrganization(params);
    }
}
