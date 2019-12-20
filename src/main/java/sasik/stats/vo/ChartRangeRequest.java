package sasik.stats.vo;

import lombok.Data;
import sasik.stats.domain.User;
import sasik.stats.services.ChartService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.Map;

@Data
public class ChartRangeRequest
{

    private String range;

    public String getRange() {
        if (null == range) {
            range = Range.DAY.name();
        }

        return range;
    }

    public enum Range
    {
        DAY,
        WEEK,
        MONTH;

        public Map<LocalDate, DoubleSummaryStatistics> filter(ChartService chartService, User user) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            final String methodName = "by" + this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
            final Method method = chartService.getClass().getMethod(methodName, User.class);

            return (Map<LocalDate, DoubleSummaryStatistics>) method.invoke(chartService, user);

        }
    }

    public Map<LocalDate, DoubleSummaryStatistics> filter(ChartService chartService, User user) {

        try {
            return Range.valueOf(this.getRange().toUpperCase()).filter(chartService, user);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


}
