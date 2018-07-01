package top.scarboroughcoral.vote;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.springframework.util.ResourceUtils.getFile;

public class FileTest {

    @Test
    public void FolderTest(){
        String sourcePath = "/home/scarborough_coral/MNTS/files/" + 50956;
        java.io.File file = new java.io.File(sourcePath);
        // get the folder list
        java.io.File[] array = file.listFiles();

        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
//                // only take file name
//                System.out.println("^^^^^" + array[i].getName());
//                // take file path and name
//                System.out.println("#####" + array[i]);
                // take file path and name
                System.out.println(array[i].getName());
            }else if(array[i].isDirectory()){
                    System.out.println(array[i].getName());
            }
        }
    }
}
