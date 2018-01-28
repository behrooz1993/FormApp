package com.ahmadpour.formapp.data.db;

import android.content.Context;

import com.ahmadpour.formapp.di.ApplicationContext;
import com.ahmadpour.formapp.di.DatabaseInfo;
import com.ahmadpour.formapp.utils.AppLogger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by behrooz on 1/29/18.
 */

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}