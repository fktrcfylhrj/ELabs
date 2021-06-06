package service;

import java.sql.Date;

public class DateWrongOrderException extends ServiceException{
    private final Date from;
    private final Date to;

    public DateWrongOrderException(Date from, Date to){
        this.from = from;
        this.to = to;
    }

    public Date getStartDate() {
        return from;
    }

    public Date getEndDate() {
        return to;
    }
}
