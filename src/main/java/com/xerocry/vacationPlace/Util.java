package com.xerocry.vacationPlace;

import com.xerocry.vacationPlace.logic.Company;
import com.xerocry.vacationPlace.logic.TravelAgency;
import com.xerocry.vacationPlace.repository.VacationPlaceRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Util {

    public static final String USER_TAG = "user";
    public static final String PASSWD_TAG = "passwd";

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * Gets current http session
     * @return HttpSession instance
     */
    public static HttpSession getSession(){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    /**
     * Logout method. This method makes current http session invalid
     *
     */
    public static void logout(){
        getSession().invalidate();
    }

    public static String getStringFromFormattedDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return DATE_FORMAT.format(date);
        }
    }

    public static String getStringTimeFromFormattedDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return TIME_FORMAT.format(date);
        }
    }

    public static Date getDateFromFormattedString(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            try {
                return DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Gets currentDate in format: yyyy-MM-dd HH:mm:ss
     * @return currentDate in format: yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime(){

        Calendar calendar = new GregorianCalendar();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        return currentDate;

    }

    /**
     * Gets currentDate in format: yyyy-MM-dd
     *
     * @return currentDate in format: yyyy-MM-dd
     */
    public static String getCurrentDate(){

        Calendar calendar = new GregorianCalendar();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return currentDate;
    }

    /**
     * Generates TourOperatorTourId
     * as currentDate in format yMMddHHmmss mod 10000000000l
     * @return  generated TourOperatorTourId
     */
    public static long generateTourOperatorTourId(){
        Calendar calendar = new GregorianCalendar();
        String currentDate = new SimpleDateFormat("yMMddHHmmss").format(calendar.getTime());

        return Long.parseLong( currentDate) % 10000000000l;
    }

    /**
     * Gets current number of milliseconds since 1970.01.01
     * @return current number of milliseconds since 1970.01.01
     */
    public static long getCurrentTimeInMills(){
        Calendar calendar = new GregorianCalendar();
        return calendar.getTimeInMillis();
    }

    /**
     * Maps company list to agency list
     *
     * @param companyList list of companies to map to agencies
     * @return agency list
     */
    public static List<TravelAgency> toAgencies(List<Company> companyList){
        List<TravelAgency> result = new ArrayList<>();
        for(Company company : companyList){
            TravelAgency ta = (TravelAgency) company;
            result.add(ta);
        }
        return result;
    }

    /**
     * Check is the company agency ?
     * @param company for checks type
     * @return true if logged agency, otherwise false
     */
    public static boolean isLoggedAgency(Company company){
        return company instanceof TravelAgency;
    }

    public static boolean isLoggedAgency(){
        VacationPlaceRepository repository = new VacationPlaceRepository();
        Company currentCompany = repository.findCurrentCompany();
        return isLoggedAgency(currentCompany);
    }
}