package sasik.stats.vo;

import lombok.Data;
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

        public Map<LocalDate, DoubleSummaryStatistics> filter(ChartService chartService) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            final String methodName = "by" + this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
            final Method method = chartService.getClass().getMethod(methodName);

            return (Map<LocalDate, DoubleSummaryStatistics>) method.invoke(chartService);

        }
    }

    public Map<LocalDate, DoubleSummaryStatistics> filter(ChartService chartService) {

        try {
            return Range.valueOf(this.getRange().toUpperCase()).filter(chartService);
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
