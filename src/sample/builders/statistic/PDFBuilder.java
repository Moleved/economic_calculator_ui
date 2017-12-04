package sample.builders.statistic;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sample.entities.Entity;
import sample.entities.ProfitabilityEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PDFBuilder {
    private ArrayList<Entity> list;
    private String type;
    private final String DEST = "test_example.pdf";

    public static void main(String... args) {
        ArrayList<Entity> list = new ArrayList<>();

        list.add(new ProfitabilityEntity("", ""));
        list.add(new ProfitabilityEntity("", ""));

        new PDFBuilder("profit", list);
    }

    public PDFBuilder(String type, ArrayList<Entity> list) {
        this.type = type;
        this.list = list;
    }

    public void perform() {
        File file = new File(DEST);
//        file.getParentFile().mkdirs();

        if (type == "absolute") handleAbsolute(list);
        if (type == "current") handleCurrent(list);
        if (type == "profit") handleProfit(list);
    }

    private void handleAbsolute(ArrayList<Entity> list) {

    }

    private void handleCurrent(ArrayList<Entity> list) {

    }

    private void handleProfit(ArrayList<Entity> list) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            PdfPTable table = new PdfPTable(list.size());
            table.addCell("");
            document.add(table);
            document.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        } catch (DocumentException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
