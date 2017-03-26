package meng.db.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import meng.db.BR;

/**
 * Created by meng on 2017/2/25.
 */
public class User extends BaseObservable {
    @Bindable
    private String firstName;
    @Bindable
    private String lastName;
    @Bindable
    private String middleName = "Milk";
    @Bindable
    private String avatar;

    public User(String firstName, String lastName, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (TextUtils.equals(middleName, this.middleName)) {
            return;
        }
        this.middleName = middleName;
        notifyPropertyChanged(BR.middleName);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
