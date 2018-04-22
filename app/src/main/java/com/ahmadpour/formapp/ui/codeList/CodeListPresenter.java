package com.ahmadpour.formapp.ui.codeList;

import android.os.Environment;
import android.util.Log;

import com.ahmadpour.formapp.data.DataManager;
import com.ahmadpour.formapp.ui.base.BasePresenter;
import com.ahmadpour.formapp.utils.rx.SchedulerProvider;
import com.ahmadpour.formapp.utils.tools.CSVWriter;

import java.io.File;
import java.io.FileWriter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behrooz on 2/11/18.
 */

public class CodeListPresenter<V extends CodeListMvpView> extends BasePresenter<V> implements CodeListMvpPresenter<V> {

    @Inject
    public CodeListPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void fetchCodes() {
        getCompositeDisposable().add(getDataManager().getCodes()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(codes -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    if (codes != null) {
                        getMvpView().onFetchCodes(codes);
                    }
                }));
    }

    @Override
    public void btnExportClicked() {
        getCompositeDisposable().add(getDataManager().getCodeTable()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(cursor -> {
                    File file = new File(Environment.getExternalStorageDirectory() +
                            "/data/data/com.ahmadpour.formapp/", "codes.csv");
                    try {
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                        csvWrite.writeNext(cursor.getColumnNames());
                        while (cursor.moveToNext()) {
                            //Which column you want to exprort
                            String arrStr[] = {cursor.getString(0), cursor.getString(1), cursor.getString(2)
                                    , cursor.getString(3)};
                            csvWrite.writeNext(arrStr);
                        }
                        csvWrite.close();
                        cursor.close();
                        getMvpView().onExportCompleted(true);
                    } catch (Exception sqlEx) {
                        Log.e("CodeListActivity", sqlEx.getMessage(), sqlEx);
                        getMvpView().onExportCompleted(false);
                    }
                }));
    }
}
