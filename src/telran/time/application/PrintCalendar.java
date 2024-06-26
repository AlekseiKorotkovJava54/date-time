package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

record MonthYear(int month, int year, int startWeekDay) {
	
}
public class PrintCalendar {

	private static final int TITLE_OFFSET = 10;
	private static final int COLUMN_WIDTH = 4;
	private static DayOfWeek[] weekDays = DayOfWeek.values();
	
	public static void main(String[] args)  {
		try {
			MonthYear monthYear = getMonthYear(args);
			printCalendar(monthYear);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static  MonthYear getMonthYear(String[] args) throws Exception{
		int monthNumber = getMonth(args);
		int year = getYear(args);
		int startWeekDay = getStartWeekDay(args);
		return new MonthYear(monthNumber, year,startWeekDay);
	}

	private static int getStartWeekDay(String[] args) throws Exception {
		int startWeekDay = args.length<3 ? 1 : getStartWeekDay(args[2]);
		return startWeekDay;
	}

	private static int getStartWeekDay(String string) throws Exception {
		try {
			int startWeekDay = DayOfWeek.valueOf(string.toUpperCase()).getValue();
			return startWeekDay;
		} catch (IllegalArgumentException  e) {
			throw new Exception("the entered data is not a day of the week");
		}
	}

	private static int getYear(String[] args) throws Exception {
		int year = args.length < 2 ? getCurrentYear() : getYear(args[1]);
		return year;
	}

	private static int getYear(String yearStr) throws Exception {
		try {
			int res = Integer.parseInt(yearStr);
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("year must be an integer number");
		}
	}

	private static int getCurrentYear() {
		return LocalDate.now().getYear();
	}

	private static int getMonth(String[] args) throws Exception{
		int month = args.length == 0 ? getCurrentMonth() : getMonthNumber(args[0]);
		return month;
	}

	private static int getMonthNumber(String monthStr)throws Exception {
		try {
			int result = Integer.parseInt(monthStr);
			if (result < 1) {
				throw new Exception("Month cannot be less than 1");
			}
			if(result > 12) {
				throw new Exception("Month cannot be greater than 12");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new Exception("Month must be a number");
		}
	}

	private static int getCurrentMonth() {
		return LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
	}

	private static void printCalendar(MonthYear monthYear) {
		printTitle(monthYear);
		printWeekDays(monthYear);
		printDays(monthYear);
	}

	private static void printDays(MonthYear monthYear) {
		int nDays = getDaysInMonth(monthYear);
		int currentWeekDay = getFirstDayOfMonth(monthYear);
		int firstOffset = getFirstOffset(currentWeekDay);
		System.out.printf("%s", " ".repeat(firstOffset));
		for(int day = 1; day <= nDays; day++) {
			System.out.printf("%" + COLUMN_WIDTH +"d", day);
			if(currentWeekDay == weekDays.length) {
				currentWeekDay = 0;
				System.out.println();
			}
			currentWeekDay++;
		}
	}

	private static int getFirstOffset(int currentWeekDay) {
		return COLUMN_WIDTH * (currentWeekDay-1);
	}

	private static int getFirstDayOfMonth(MonthYear monthYear) {
		int ld = LocalDate.of(monthYear.year(), monthYear.month(),1).getDayOfWeek().getValue();
		return ld-monthYear.startWeekDay()<0 ? ld-monthYear.startWeekDay()+8:ld-monthYear.startWeekDay()+1;
	}

	private static int getDaysInMonth(MonthYear monthYear) {
		YearMonth ym = YearMonth.of(monthYear.year(), monthYear.month());
		return ym.lengthOfMonth();
	}

	private static void printWeekDays(MonthYear monthYear) {
		for(int i=0; i<weekDays.length;i++) {
			int index = i + monthYear.startWeekDay() - 1 < weekDays.length ? i + monthYear.startWeekDay() - 1 : i + monthYear.startWeekDay() - 8;
			System.out.printf("%" + COLUMN_WIDTH +"s",weekDays[index].getDisplayName(TextStyle.SHORT,Locale.forLanguageTag("en")));
		}
		System.out.println();
	}

	private static void printTitle(MonthYear monthYear) {
		String monthName = Month.of(monthYear.month()).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("en"));
		System.out.printf("%s%s %d\n"," ".repeat(TITLE_OFFSET), monthName, monthYear.year());
	}
}