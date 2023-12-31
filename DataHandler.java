import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler<T extends LoadSave> {
    private String filePath;
    private File file;
    private FileWriter fileWriter;
    private Scanner fileReader;
    private ArrayList<T> list;

    @SuppressWarnings("unchecked")
    public DataHandler(String path, T oi) throws IOException{
        filePath=path;
        file=new File(filePath);
        file.createNewFile();
        list = new ArrayList<T>();
        String line;
        fileReader = new Scanner(file);
        while(fileReader.hasNextLine())
        {   
                line = fileReader.nextLine();
                list.add((T)oi.fromString(line));
        }  
        fileReader.close();
    }
    private void save() throws IOException{
        fileWriter = new FileWriter(file,false);
        String fileContents="";
        for (T item : list) {
            fileContents+=item+"\n";
        }
        fileWriter.write(fileContents);
        fileWriter.close();
    }
    public T get(int index){
        return list.get(index);
    }
    public int getIndex(T object){
        return list.indexOf(object);
    }
    public int getLength(){
        return list.size();
    }
    public void add(T item) throws IOException{
        list.add(item);
        save();
    }
    public void update(int index, T item) throws IOException{
        list.set(index,item);
        save();
    }
    public void delete(int index) throws IOException{
        list.remove(index);
        save();
    }
}
