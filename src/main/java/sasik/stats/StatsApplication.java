package sasik.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import sasik.stats.services.WeightImporter;

import java.util.Locale;

@SpringBootApplication
public class StatsApplication
{

    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.forLanguageTag("ru"));
        SpringApplication.run(StatsApplication.class, args);
    }

    @Bean
    public WeightImporter initBase(WeightImporter weightImporter) throws Exception {
        System.out.println("start");
//        weightImporter.fillBase();
        System.out.println("end");

        return weightImporter;
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("ru"));
        return slr;
    }

}
