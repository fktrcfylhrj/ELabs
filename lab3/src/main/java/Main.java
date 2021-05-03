import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try{
            List<Preparation> preparations = (new PreparationXmlReader()).read("E:\\EPAM_LABS\\lab3\\src\\main\\java\\medicinalPreparations.xml");
            for(Preparation p : preparations){
                System.out.println("Preparation: " + p.getName());
                System.out.println("Type: " + p.getType());
                System.out.println("firm-producer: " + p.getPharm());
                System.out.println("Group: " + p.getGroup());
                System.out.println("Analogs: " + p.getAnalogs());
                System.out.println(p.getVersionTypesInfo());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
