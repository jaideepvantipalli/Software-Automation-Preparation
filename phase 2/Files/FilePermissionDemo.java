import java.io.File;

public class FilePermissionDemo {

    public static void main(String[] args) throws Exception {

        File file = new File("permission.txt");

        file.createNewFile();

        System.out.println("Initial Permissions");

        System.out.println("Read: " + file.canRead());
        System.out.println("Write: " + file.canWrite());
        System.out.println("Execute: " + file.canExecute());

        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);

        System.out.println("\nAfter Enabling Permissions");

        System.out.println("Read: " + file.canRead());
        System.out.println("Write: " + file.canWrite());
        System.out.println("Execute: " + file.canExecute());

        boolean result = file.setWritable(false);

        System.out.println("\nWrite Permission Changed: " + result);

        System.out.println("Write: " + file.canWrite());

        file.setWritable(true);

        file.setReadOnly();

        System.out.println("\nAfter setReadOnly()");

        System.out.println("Read: " + file.canRead());
        System.out.println("Write: " + file.canWrite());

        file.setWritable(true);
        file.delete();
    }
}