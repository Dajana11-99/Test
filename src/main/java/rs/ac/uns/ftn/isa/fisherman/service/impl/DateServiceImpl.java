package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.service.DateService;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class DateServiceImpl implements DateService {

    @Override
    public List<LocalDateTime> findWeek() {
        List<LocalDateTime> thisWeek=new ArrayList<>();
        LocalDateTime currentDate=LocalDateTime.now();
        TemporalField week= WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDateTime startWeek= LocalDateTime.of(currentDate.with(week,1).toLocalDate(), LocalTime.MIN);
        LocalDateTime endWeek= LocalDateTime.of(currentDate.with(week,7).toLocalDate(),LocalTime.MAX);
        thisWeek.add(startWeek);
        thisWeek.add(endWeek);
        return thisWeek;
    }

    @Override
    public List<LocalDateTime> findMonth() {
        List<LocalDateTime> thisMonth=new ArrayList<>();
        LocalDateTime currentDate=LocalDateTime.now();
        YearMonth month= YearMonth.from(currentDate);
        LocalDateTime startMonth= LocalDateTime.of(month.atDay(1), LocalTime.MIN);
        LocalDateTime endMonth= LocalDateTime.of(month.atEndOfMonth(),LocalTime.MAX);
        thisMonth.add(startMonth);
        thisMonth.add(endMonth);
        return thisMonth;
    }

    @Override
    public List<LocalDateTime> findYear() {
        List<LocalDateTime> thisYear=new ArrayList<>();
        LocalDateTime currentDate=LocalDateTime.now();
        LocalDateTime startYear= LocalDateTime.of(currentDate.with(firstDayOfYear()).toLocalDate(), LocalTime.MIN);
        LocalDateTime endYear= LocalDateTime.of(currentDate.with(lastDayOfYear()).toLocalDate(),LocalTime.MAX);
        thisYear.add(startYear);
        thisYear.add(endYear);
        return thisYear;
    }
}
