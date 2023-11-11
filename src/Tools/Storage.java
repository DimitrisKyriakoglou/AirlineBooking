package Tools;

import Controller.BookingController;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Storage {

    //LoadData
    //BufferReader is used to read text
    public static BookingController loadData() {

        BookingController controller = new BookingController();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.json"));


            /* String */
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");


            //while there is a next line read
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            //always close reader
            reader.close();

            String content = stringBuilder.toString();
            Gson gson = new Gson();
            controller = gson.fromJson(content, BookingController.class);

        } catch (Exception e) {
            System.out.println(e);
        }

        return controller;
    }

    //save the loaded data from above
    public static boolean savingData(String data) {
        try {
            FileWriter fw = new FileWriter("data.json");
            fw.write(data);
            //always close file writer
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
