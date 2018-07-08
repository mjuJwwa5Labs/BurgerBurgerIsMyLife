package OOP_master_challenge;

import java.io.File;
import java.net.URL;

/**
 * @author Marika Grzebieniowska on 09.07.2018
 * @project internship
 */
class FileLoader {

    File loadFile(String path) {
        URL url = getClass().getClassLoader().getResource(path);

        File file = null;
        try {
            file = new File(url.getPath());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    return file;
    }

}
