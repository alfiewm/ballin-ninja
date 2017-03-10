package meng.imageviewer.imageview;

/**
 * Created by meng on 2017/3/8.
 */

public interface DataSetChangeListener {
    /**
     * 通知数据发生变化
     * @param offset 原数据在新数据集中偏移
     */
    void notifyDataSetChanged(int offset);
}
