package meng.db.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import meng.db.BR;

/**
 * Created by meng on 2017/3/26.
 */
public class Person extends BaseObservable {
    @Bindable
    private String firstName;
    @Bindable
    private String lasName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
        notifyPropertyChanged(BR.lasName);
    }
}
