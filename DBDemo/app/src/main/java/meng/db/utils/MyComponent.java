package meng.db.utils;

import meng.db.binding.BindingAdapters;
import meng.db.binding.DebugBindingAdapter;
import meng.db.binding.ReleaseBindingAdapters;

/**
 * Created by meng on 2017/3/27.
 */
public class MyComponent implements android.databinding.DataBindingComponent {

    private final static boolean isTest = false;

    @Override
    public BindingAdapters getBindingAdapters() {
        if (isTest) {
            return new DebugBindingAdapter();
        } else {
            return new ReleaseBindingAdapters();
        }
    }
}
