package exemple.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileManagerService {


   private final FileService fileService;

    public boolean deleteImage(String name){
        try {
            String [] fileName = name.split("/");
            fileService.deleteFile(fileName[fileName.length - 1]);
            return true;
        } catch ( Exception  e) {
            return false;
        }
    }
}
