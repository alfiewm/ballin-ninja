package meng.recyclerviewtest.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by meng on 2016/10/20.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemViewClick(v);
            }
        });
    }

    protected abstract void onItemViewClick(View itemView);
}
