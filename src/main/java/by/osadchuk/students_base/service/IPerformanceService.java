package by.osadchuk.students_base.service;

import by.osadchuk.students_base.dto.RateDto;

import java.util.List;

public interface IPerformanceService {
    void rate(RateDto rateDto);
    List<String> findByUser(String username);
}
