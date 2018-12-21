package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Converter;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

@Database(entities = {LoginLog.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class LoginLogDatabase extends RoomDatabase {
    private static LoginLogDatabase db;

    public static LoginLogDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, LoginLogDatabase.class, Constant.ROOM_DATABASE_LOGIN_LOG)
                    .build();
        }
        return db;
    }

    public abstract LoginLogDao loginLogDao();

    public LoginLog addLoginLog(LoginLog log) {
        db.loginLogDao().insertLoginLog(log);
        return log;
    }

    public List<LoginLog>  getAllLogs() {
        return db.loginLogDao().getAllLogs();
    }

    public List<LoginLog> getLogsByEmail(String email) {
        return db.loginLogDao().getLogsByEmail(email);
    }
}
