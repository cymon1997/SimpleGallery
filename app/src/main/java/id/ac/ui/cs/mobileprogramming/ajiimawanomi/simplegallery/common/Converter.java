package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date longToDate(Long value) {
        return (value == null)? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToLong(Date date) {
        return (date == null)? null : date.getTime();
    }
}
