/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.bind.adapter.JsonbAdapter;
public class JsonDateSerializer implements JsonbAdapter<Date, String> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public String adaptToJson(Date date) throws Exception {
        if (date == null) {
            return null;
        }
        return sdf.format(date);
    }
    @Override
    public Date adaptFromJson(String dateStr) throws Exception {
        try {
            Date now = sdf.parse(dateStr);
            return now;
        } catch (Exception ex) {
            return null;
        }
    }
}
