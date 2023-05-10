import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String address, GameProgress player) {
        try (FileOutputStream fos = new FileOutputStream(address);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zip, String player) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip));
             FileInputStream fis = new FileInputStream(player)) {
            ZipEntry entry = new ZipEntry("packed_playerProgress.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(99, 130, 25, 5940.23);
        GameProgress player2 = new GameProgress(50, 55, 17, 3577.32);
        GameProgress player3 = new GameProgress(72, 93, 51, 10345.83);

        saveGame("C://Users//Games//savegames//player1.dat", player1);
        saveGame("C://Users//Games//savegames//player2.dat", player2);
        saveGame("C://Users//Games//savegames//player3.dat", player3);

        zipFiles("C://Users//Games//savegames//zip.zip", "C://Users//Games//savegames//player1.dat");
        zipFiles("C://Users//Games//savegames//zip.zip", "C://Users//Games//savegames//player2.dat");
        zipFiles("C://Users//Games//savegames//zip.zip", "C://Users//Games//savegames//player3.dat");

        File dir = new File("C://Users//Games//savegames");
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (!item.getName().equals("zip.zip")) {
                    item.delete();
                }
            }
        }
    }
}