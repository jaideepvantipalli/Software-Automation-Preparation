//We will create a directory, create a file, check its properties, retrieve its information, list directory contents, rename it, and finally delete it.
import java.io.File;
import java.io.IOException;
import java.util.Date;
public class FileClassDemo{
    public static void main(String[] args) throws IOException {
        File folder = new File("JavaFileDemo");

        if(folder.mkdir() || folder.exists()) System.out.println("Directory Exists");

        File file = new File(folder,"student.txt");

        if(file.createNewFile()){
            System.out.println("File Created Succesfully");
        }else System.out.println("File already Exists");

        //properties
        System.out.println("Exists: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Directory: " + file.isDirectory());
        System.out.println("Is Hidden: " + file.isHidden());

        // File information
        System.out.println("Name: " + file.getName());
        System.out.println("Path: " + file.getPath());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Length: " + file.length());
        System.out.println("Last Modified: "
                + new Date(file.lastModified()));

         //Permissions
        System.out.println("Readable: " + file.canRead());
        System.out.println("Writable: " + file.canWrite());
        System.out.println("Executable: " + file.canExecute());

         //Directory listing
        System.out.println("Directory Contents:");

        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) {
                System.out.println(f.getName());
            }
        }

        // Rename the file
        File renamedFile = new File(folder, "employee.txt");

        if (file.renameTo(renamedFile)) {
            System.out.println("File renamed successfully");
        }

        //Convert to Path
        System.out.println("Path object: " + renamedFile.toPath());

        // Convert to URI
        System.out.println("URI: " + renamedFile.toURI());

        // Delete the renamed file
        if (renamedFile.delete()) {
            System.out.println("File deleted successfully");
        }

        // Delete the directory
        if (folder.delete()) {
            System.out.println("Directory deleted successfully");
        }
    }
}