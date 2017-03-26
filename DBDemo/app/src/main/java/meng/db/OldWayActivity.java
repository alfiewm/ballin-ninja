package meng.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import meng.db.data.User;

public class OldWayActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_way);
        user = new User("Nicolas", "Zhao si", "avatar");
        TextView firstNameView = (TextView)
                findViewById(R.id.first_name);
        TextView lastNameView = (TextView)
                findViewById(R.id.last_name);
        firstNameView.setText(user.getFirstName());
        lastNameView.setText(user.getLastName());
    }
}
