package meng.recyclerviewtest.data;

import java.util.List;

/**
 * Created by meng on 2016/10/21.
 */

public interface DataProducer<T> {
    class Result<T> {
        boolean hasMore;
        List<T> data;
    }

    Result<T> refresh(int count);

    Result<T> loadMore(int count, int page);
}
