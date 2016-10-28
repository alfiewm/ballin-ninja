package meng.recyclerviewtest.widget;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by meng on 2016/10/21.
 */

public class BaseAdapater extends RecyclerView.Adapter<BaseViewHolder> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
