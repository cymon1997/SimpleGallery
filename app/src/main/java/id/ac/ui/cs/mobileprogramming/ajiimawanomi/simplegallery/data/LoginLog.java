package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Converter;

@Entity
public class LoginLog {
    @PrimaryKey
    private int uid;

    private String email;

    @TypeConverters({Converter.class})
    private Date timestamp;

    public LoginLog(String email, Date timestamp) {
        this.email = email;
        this.timestamp = timestamp;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return this.uid;
    }

    public String getEmail() {
        return this.email;
    }

    @TypeConverter
    public Date getTimestamp() {
        return this.timestamp;
    }
}
