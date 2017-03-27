package meng.db;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import meng.db.data.User;
import meng.db.databinding.NewWayBinding;
import meng.db.utils.MyComponent;

public class NewWayActivity extends AppCompatActivity {

    private User user;
    private NewWayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User("Nicolas", "Zhao si",
                "http://u1.tdimg.com/6/132/197/_45212687804729136199638185055904720336.jpg");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_way, new MyComponent());
        binding.setUser(user);
        binding.setPresenter(this);
    }

    public void onFirstNameClicks(View v) {
        // handle click
        Toast.makeText(this, "First Name Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onLastNameClick(View v, User user) {
        Toast.makeText(this, "Last Name Clicked", Toast.LENGTH_SHORT).show();
    }

    public void onUserCheckChanged(User user, boolean isChecked) {
        Toast.makeText(this, user + " isChecked = " + isChecked, Toast.LENGTH_SHORT).show();
    }
}
