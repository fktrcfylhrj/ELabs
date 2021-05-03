import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PreparationXmlReader {
    public List<Preparation> read(String fileName) throws FileNotFoundException{
        XMLStreamReader reader = null;
        try{
            List<Preparation> preparations = new ArrayList<Preparation>();
            Preparation preparation = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            String prev = null;
            String cost = null;
            String timesPerPeriod = null;
            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    String tagName = reader.getLocalName();
                    if("preparation".equals(tagName)){
                        preparation = new Preparation();
                        //preparation.addEmptyVersion();
                        preparation.setIdentity(reader.getAttributeValue(null, "id"));
                        prev = "preparation";
                    } else if("name".equals(tagName)){
                        if(prev.equals("preparation")) {
                            preparation.setName(reader.getElementText());
                        } else if(prev.equals("manufacturer")){
                            preparation.addVersionTypeManufacturerName(reader.getElementText());
                        }
                        prev = null;
                    } else if("type".equals(tagName)){
                        if(prev == "package"){
                            preparation.addVersionTypeManufacturerPackageType(reader.getElementText());
                            prev = null;
                        } else {
                            preparation.setType(reader.getElementText());
                        }
                    } else if("pharm".equals(tagName)){
                        preparation.setPharm(reader.getElementText());
                    } else if("group".equals(tagName)){
                       prev = "group";
                    } else if(prev == "group"){
                        switch(tagName){
                            case "antibiotic": {
                                preparation.setGroup("antibiotic");
                                break;
                            }
                            case "analgesic": {
                                preparation.setGroup("analgesic");
                                break;
                            }
                            case "vitamin": {
                                preparation.setGroup("vitamin");
                                break;
                            }
                            case "antimicrobial": {
                                preparation.setGroup("antimicrobial");
                                break;
                            }
                            case "antidepressant": {
                                preparation.setGroup("antidepressant");
                                break;
                            }
                            case "complex-action": {
                                preparation.setGroup("complexAction");
                                break;
                            }
                            case "tranquilizing_agent": {
                                preparation.setGroup("tranquilizingAgent");
                                break;
                            }
                        }
                        prev = null;
                    }
                    else if("analogs".equals(tagName)){
                        continue;
                    } else if("analog".equals(tagName)){
                        preparation.addAnalog(reader.getElementText());
                    } else if("versions".equals(tagName)){
                        //prev = "versions";
                        continue;
                    } else if("version".equals(tagName)){
                        preparation.addEmptyVersion();
                        prev = "version";
                    } else if(prev == "version"){
                        switch(tagName){
                            case "pills":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                            case "capsules":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                            case "powder":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                            case "drops":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                            case "syrup":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                            case "spray":{
                                preparation.addVersionType(tagName);
                                break;
                            }
                        }
                        prev = null;
                    } else if("manufacturers".equals(tagName)){
                        continue;
                    } else if("manufacturer".equals(tagName)){
                        prev = "manufacturer";
                    } else if("number".equals(tagName)){
                        preparation.addVersionTypeManufacturerSertificateNumber(reader.getElementText());
                    } else if("dateOfIssuance".equals(tagName)){
                        preparation.addVersionTypeManufacturerSertificateDateOfIssuance(reader.getElementText());
                    } else if("expirationDate".equals(tagName)){
                        preparation.addVersionTypeManufacturerSertificateExpirationDate(reader.getElementText());
                    } else if("registeringOrganization".equals(tagName)){
                        preparation.addVersionTypeManufacturerSertificateRegisteringOrganization(reader.getElementText());
                    } else if("package".equals(tagName)){
                        prev = "package";
                    } else if("amount".equals(tagName)){
                        preparation.addVersionTypeManufacturerAmountInPackage(reader.getElementText());
                    } else if("cost".equals(tagName)){
                        continue;
                    } else if("currency".equals(tagName)){
                        cost = reader.getElementText();
                        prev = "currency";
                    } else if("value".equals(tagName)){
                        if(prev == "currency"){
                            prev = null;
                            cost = cost + " " + reader.getElementText();
                            preparation.addVersionTypeManufacturerPackageCost(cost);
                            cost = null;
                        } else{
                            preparation.addVersionTypeManufacturerDosageValue(reader.getElementText());
                        }
                    } else if("times".equals(tagName)){
                        timesPerPeriod = reader.getElementText();
                    } else if("period".equals((tagName))){
                        preparation.addVersionTypeManufacturerDosageTimesPerPeriod(timesPerPeriod + " per " + reader.getElementText());
                    }
                }
                if(type == XMLStreamConstants.END_ELEMENT){
                    String tagName = reader.getLocalName();
                    if("preparation".equals(tagName)){
                        preparations.add(preparation);
                    }
                }
            }
            return preparations;
        } catch(XMLStreamException e){
            return null;
        } finally{
            try{
                reader.close();
            } catch (Exception e){

            }
        }
    }
}
