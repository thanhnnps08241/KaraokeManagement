/*
 * ĐỂ DÙNG ĐƯỢC TÍNH NĂNG NÀY, YÊU CẦU PHẢI CÓ ĐT ANDROID, SAU ĐÓ CÀI PM myMobKit,
 */
package com.karaoke.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

public class SendSMS {

    private static HttpURLConnection con;

    public static void send(String urlapi, String sdt, String message) throws MalformedURLException,
            ProtocolException, IOException {
        String url = "http://" + urlapi + "/services/api/messaging/";
        String urlParameters = "To=" + sdt + "&Message=" + message + "&Slot=1";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            } catch (IOException ex) {
                Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            } catch (IOException ex) {
                Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
                XuLy.popup("loi", new JDialog(), "Lỗi " + ex, "LỖI GỬI SMS");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
            XuLy.popup("loi", new JDialog(), "Lỗi " + ex, "LỖI GỬI SMS");

        } catch (IOException ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
            XuLy.popup("loi", new JDialog(), "Lỗi " + ex, "LỖI GỬI SMS");

        } finally {

            con.disconnect();
        }
    }
}
