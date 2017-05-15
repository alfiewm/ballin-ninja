package meng.db.widget.ptr;

import android.content.Context;
import android.util.AttributeSet;

public class PtrTutorLayout extends PtrFrameLayout {

    public PtrTutorLayout(Context context) {
        super(context);
        initViews();
    }

    public PtrTutorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrTutorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        PtrTutorHeader ptrClassicHeader = new PtrTutorHeader(getContext());
        setHeaderView(ptrClassicHeader);
        addPtrUIHandler(ptrClassicHeader);
        setDurationToClose(200);
        setDurationToCloseHeader(1000);
        setKeepHeaderWhenRefresh(true);
        setRatioOfHeaderHeightToRefresh(1.2f);
        setResistance(1.7f);
    }
}
