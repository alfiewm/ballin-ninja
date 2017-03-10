package meng.imageviewer.imageview;

/**
 * Created by meng on 2017/3/10.
 */

public class ImageProviderFactory {
    public static ImageProvider createSingleUrlProvider(String url, boolean allowDownload) {
        return new SingleUrlImageProvider(url, allowDownload);
    }

    public static ImageProvider createUrlsProvider(String[] urls, int defaultPos,
            boolean allowDownload) {
        return new UrlsImageProvider(urls, defaultPos, allowDownload);
    }
}
