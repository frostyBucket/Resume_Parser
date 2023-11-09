import java.io.File;
import Handlers.redactionHandler;
public class extractionTests {
    public static void main(String[] args) throws Exception {
        String path = "src/test/testDocs/2023-chronological-cv.pdf";
        File file = new File(path);
        //String text = docHandler.readPDFFile(file);
        String[] params = {"email", "names"};
        redactionHandler.redaction(path, params);
    }
}
