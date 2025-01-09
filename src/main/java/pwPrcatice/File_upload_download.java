package pwPrcatice;

import java.nio.file.Path;
import java.nio.file.Paths;

public class File_upload_download extends CSSRelativeLocators_basedLayout {
    public static void main(String[] args) {
        getPage();
        page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

//        uploading file
        page.setInputFiles("#filesToUpload", Paths.get("imageupload.png"));
        //detaching file
        page.setInputFiles("#filesToUpload", new Path[0]);

        //uploading multiple files
        page.setInputFiles("#filesToUpload", new Path[]{
                Paths.get("imageupload.png"),
                Paths.get("imageupload.png"),
                Paths.get("imageupload.png")
        });

        closebrowser();
    }
}
