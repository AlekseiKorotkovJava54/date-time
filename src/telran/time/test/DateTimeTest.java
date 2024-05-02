package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class DateTimeTest {

	@Test
	void introductionTest() {
//		LocalDate birthDateAS = LocalDate.of(1799, 6, 6);
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		System.out.printf("birthdate of ASP in standart format is %s\n", birthDateAS);
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", birthDateAS.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyyy")));
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyyy",Locale.ENGLISH)));
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyyy",Locale.JAPAN)));
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyyy",Locale.forLanguageTag("he"))));
		System.out.printf("birthdate of ASP in dd/mm/yyyy format is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,dd.yyyy EEEE",Locale.forLanguageTag("ru"))));
	}
	@Test
	void introductionTest1() {
		LocalDate d1 = LocalDate.parse("1990-01-30");
		LocalDate d2= LocalDate.parse("2000-10-20");
//		ChronoUnit unit = ChronoUnit.MONTHS;
		ChronoUnit unit = ChronoUnit.DAYS;
		System.out.printf("difference between %s and %s in %s is %d\n",d1,d2,unit,unit.between(d1, d2));
	}
	@Test
	void introductionTest2() {
		LocalDateTime d1 = LocalDateTime.parse("1990-01-30T00:00:00");
		LocalDateTime d2= LocalDateTime.parse("2000-10-20T00:00:00");
		ChronoUnit unit = ChronoUnit.SECONDS;
		System.out.printf("difference between %s and %s in %s is %d\n",d1,d2,unit,unit.between(d1, d2));
	}
}
