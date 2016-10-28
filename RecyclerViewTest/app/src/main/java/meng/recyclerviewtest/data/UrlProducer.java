package meng.recyclerviewtest.data;

/**
 * Created by meng on 2016/10/21.
 */

public class UrlProducer implements DataProducer<String> {
    @Override
    public Result<String> refresh(int count) {
        return null;
    }

    @Override
    public Result<String> loadMore(int count, int page) {
        return null;
    }
}
