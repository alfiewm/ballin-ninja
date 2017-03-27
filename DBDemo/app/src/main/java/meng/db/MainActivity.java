package meng.db;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import meng.db.data.User;
import meng.db.databinding.ActivityMainBinding;
import meng.db.utils.MyComponent;

public class MainActivity extends AppCompatActivity {

    private User user;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setDefaultComponent(new MyComponent());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("Muffin", "Knight", "https://unsplash.it/300?image=4");
        binding.setUser(user);
        binding.firstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstName("Jack");
                user.setLastName("Frost");
            }
        });
        binding.oldWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OldWayActivity.class));
            }
        });
        binding.newWayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewWayActivity.class));
            }
        });
    }
}
