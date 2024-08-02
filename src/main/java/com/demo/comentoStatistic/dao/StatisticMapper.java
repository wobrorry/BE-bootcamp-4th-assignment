package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.DayCountDto;
import com.demo.comentoStatistic.dto.OrganizationCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.dto.YearMonthCountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);

    DayCountDto selectDayLogin(Map<String, String> params);
    Double selectMonthlyAverageLogin(String yearMonth);
    Integer selectMonthlyLoginWithoutHolidays(String yearMonth);
    OrganizationCountDto selectMonthlyLoginByOrganization(Map<String, String> params);
}
