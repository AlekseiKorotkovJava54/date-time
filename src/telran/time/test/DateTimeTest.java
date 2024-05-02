package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
