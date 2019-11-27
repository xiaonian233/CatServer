package catserver.server.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LanguageUtils {
    private static Map<String, String> CN = new HashMap<>();
    private static Map<String, String> EN = new HashMap<>();
    private static Map<String, String> current;

    static {
        CN.put("launch.lib_exception", "校验库文件时发生错误, 请检查网络或手动下载, 服务端将尝试继续启动!");
        CN.put("launch.lib_failure_check", "文件 %s 校验失败, 你也可以手动下载: %s");
        CN.put("launch.lib_failure_download", "下载文件失败(HTTP状态: %s), 你也可以手动下载: %s");
        CN.put("launch.lib_downloading", "正在下载文件 %s 大小: %s");
        CN.put("async.caught_async", "拦截异步操作List, 请检查插件和MOD!");
        CN.put("world.chunk_update_error", "区块更新时发生错误, 已将区块卸载防止崩溃!");
        CN.put("world.region_corrupt", "区块文件损坏: ");

        EN.put("launch.lib_exception", "An error occurred while checking the library file, will try to continue to start!");
        EN.put("launch.lib_failure_check", "File %s verification failed, URL: %s");
        EN.put("launch.lib_failure_download", "File download failed(HTTP Status: %s), URL: %s");
        EN.put("launch.lib_downloading", "Downloading %s Size: %s");
        EN.put("async.caught_async", "Caught asynchronously modify List, please check plugins and mods!");
        EN.put("world.chunk_update_error", "An error occurred while chunk updating, unloaded chunk to prevent crash!");
        EN.put("world.region_corrupt", "Region file is corrupted: ");

        current = ("zh".equals(Locale.getDefault().getLanguage()) ? CN : EN);
    }

    public static String I18nToString(String text) {
        return current.getOrDefault(text, current != EN ? EN.getOrDefault(text, "null") : "null");
    }
}
