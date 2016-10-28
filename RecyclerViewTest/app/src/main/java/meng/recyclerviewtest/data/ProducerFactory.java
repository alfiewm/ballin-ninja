package meng.recyclerviewtest.data;

/**
 * Created by meng on 2016/10/21.
 */

public class ProducerFactory {
    public static DataProducer<String> getFakeProducer() {
        return new UrlProducer();
    }
}
