package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

@Dao
public interface LoginLogDao {

    @Query("SELECT * FROM loginlog")
    List<LoginLog> getAllLogs();

    @Query("SELECT * FROM loginlog WHERE email = :email")
    List<LoginLog> getLogsByEmail(String email);

    @Insert
    void insertLoginLog(LoginLog log);
}
