package chen.ticket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 公众平台通用接口工具类
 */
public class HttpsUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

    private final static HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;

    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static String http(String requestUrl, String requestMethod, String outputStr, Integer timeOut) throws Exception {
        StringBuilder buffer = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        HttpURLConnection httpUrlConn = null;
        OutputStream outputStream = null;
        try {

            URL url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            if (timeOut != null) {
                httpUrlConn.setConnectTimeout(timeOut);
            } else {
                httpUrlConn.setConnectTimeout(6000);
            }
            httpUrlConn.setReadTimeout(120000);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }
            // 当有数据需要提交时
            if (null != outputStr) {
                httpUrlConn.setRequestProperty("Content-Type", "application/json;chartset=UTF-8");
                httpUrlConn.setRequestProperty("Content-Length", outputStr.length() + "");
                outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (Exception e) {
            logger.error("捕获异常:", e);
            throw e;
        } finally {
            try {

                if (outputStream != null) {
                    outputStream.close();
                    logger.info("outputStream close");
                }

                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                    logger.info("httpUrlConn disconnect");
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                    logger.info("bufferedReader close");
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                    logger.info("inputStreamReader close");
                }

                if (inputStream != null) {
                    inputStream.close();
                    logger.info("inputStream close");
                }
            } catch (Exception e) {
                logger.error("api:" + requestUrl + ", finally异常:", e);
            }
        }
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static String https(String requestUrl, String requestMethod, String outputStr, Integer timeOut) throws Exception{
        StringBuilder buffer = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        HttpsURLConnection httpUrlConn = null;
        OutputStream outputStream = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            if (timeOut != null) {
                httpUrlConn.setConnectTimeout(timeOut);
            } else {
                httpUrlConn.setConnectTimeout(6000);
            }
            httpUrlConn.setReadTimeout(120000);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            httpUrlConn.setHostnameVerifier(DO_NOT_VERIFY);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }
            // 当有数据需要提交时
            if (null != outputStr) {
                httpUrlConn.setRequestProperty("Content-Type", "application/json;chartset=UTF-8");
                httpUrlConn.setRequestProperty("Content-Length", outputStr.length() + "");
                outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            }

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (Exception e) {
            logger.error("捕获异常:", e);
            throw e;
        } finally {
            try {

                if (outputStream != null) {
                    outputStream.close();
                    logger.info("outputStream close");
                }

                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                    logger.info("httpUrlConn disconnect");
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                    logger.info("bufferedReader close");
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                    logger.info("inputStreamReader close");
                }

                if (inputStream != null) {
                    inputStream.close();
                    logger.info("inputStream close");
                }
            } catch (Exception e) {
                logger.error("api:" + requestUrl + ", finally异常:", e);
            }
        }
    }

}

