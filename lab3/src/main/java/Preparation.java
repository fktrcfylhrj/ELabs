import java.util.ArrayList;
import java.util.Comparator;

public class Preparation implements Comparator<Preparation> {
    private String identity;
    private String name;
    private String type;
    private String pharm;
    private Group group;
    private ArrayList<String> analogs = new ArrayList<>();
    private ArrayList<Consistency> versionType = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerName = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerSertificateNumber = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerSertificateDateOfIssuance = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerSertificateExpirationDate = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerSertificateRegisteringOrganization = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerPackageType = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerAmountInPackage = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerPackageCost = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerDosageValue = new ArrayList<>();
    private ArrayList<ArrayList<String>> versionTypeManufacturerDosageTimesPerPeriod = new ArrayList<>();

    public void addEmptyVersion(){
        versionTypeManufacturerName.add(new ArrayList<>());
        versionTypeManufacturerSertificateNumber.add(new ArrayList<>());
        versionTypeManufacturerSertificateDateOfIssuance.add(new ArrayList<>());
        versionTypeManufacturerSertificateExpirationDate.add(new ArrayList<>());
        versionTypeManufacturerSertificateRegisteringOrganization.add(new ArrayList<>());
        versionTypeManufacturerPackageType.add(new ArrayList<>());
        versionTypeManufacturerAmountInPackage.add(new ArrayList<>());
        versionTypeManufacturerPackageCost.add(new ArrayList<>());
        versionTypeManufacturerDosageValue.add(new ArrayList<>());
        versionTypeManufacturerDosageTimesPerPeriod.add(new ArrayList<>());
    }

    public String getVersionTypesInfo() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < this.versionType.size(); ++i){
            builder.append("Version: ").append(this.versionType.get(i).toString()).append('\n');
            builder.append("Manufacturers:\n");
            for(int j = 0; j < versionTypeManufacturerName.get(i).size(); ++j) {
                builder.append("\tManufacturer: ").append(versionTypeManufacturerName.get(i).get(j)).append('\n');
                builder.append("\tSertificate Number: ").append(versionTypeManufacturerSertificateNumber.get(i).get(j)).append('\n');
                builder.append("\tSertificate Date Of Issuance: ").append(versionTypeManufacturerSertificateDateOfIssuance.get(i).get(j)).append('\n');
                builder.append("\tSertificate Expiration Date: ").append(versionTypeManufacturerSertificateExpirationDate.get(i).get(j)).append('\n');
                builder.append("\tSertificate Registering Organization: ").append(versionTypeManufacturerSertificateRegisteringOrganization.get(i).get(j)).append('\n');
                builder.append("\tPackage Type: ").append(versionTypeManufacturerPackageType.get(i).get(j)).append('\n');
                builder.append("\tAmount In Package: ").append(versionTypeManufacturerAmountInPackage.get(i).get(j)).append('\n');
                builder.append("\tPackage Cost: ").append(versionTypeManufacturerPackageCost.get(i).get(j)).append('\n');
                builder.append("\tDosage Value: ").append(versionTypeManufacturerDosageValue.get(i).get(j)).append('\n');
                builder.append("\tFrequency of taking preparation: ").append(versionTypeManufacturerDosageTimesPerPeriod.get(i).get(j)).append('\n');
            }
        }
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group.toString();
    }

    public void setGroup(String group) {
        this.group = Group.valueOf(group);
    }

    public String getAnalogs() {
        StringBuilder builder = new StringBuilder();

        for (String analog : this.analogs) {
            builder.append(analog).append("; ");
        }
        return builder.toString();
    }

    public void addAnalog(String analog) {
        this.analogs.add(analog);
    }

    public void addVersionType(String versionType) {
        this.versionType.add(Consistency.valueOf(versionType));
    }

    public void addVersionTypeManufacturerName(String ManufacturerName) {
        this.versionTypeManufacturerName.get(versionTypeManufacturerName.size() - 1).add(ManufacturerName);
    }

    public void addVersionTypeManufacturerSertificateNumber(String SertificateNumber) {
        this.versionTypeManufacturerSertificateNumber.get(versionTypeManufacturerSertificateNumber.size() - 1).add(SertificateNumber);
    }

    public void addVersionTypeManufacturerSertificateDateOfIssuance(String SertificateDateOfIssuance) {
        this.versionTypeManufacturerSertificateDateOfIssuance.get(versionTypeManufacturerSertificateDateOfIssuance.size() - 1).add(SertificateDateOfIssuance);
    }

    public void addVersionTypeManufacturerSertificateExpirationDate(String SertificateExpirationDate) {
        this.versionTypeManufacturerSertificateExpirationDate.get(versionTypeManufacturerSertificateExpirationDate.size() - 1).add(SertificateExpirationDate);
    }

    public void addVersionTypeManufacturerSertificateRegisteringOrganization(String SertificateRegisteringOrganization) {
        this.versionTypeManufacturerSertificateRegisteringOrganization.get(versionTypeManufacturerSertificateRegisteringOrganization.size() - 1).add(SertificateRegisteringOrganization);
    }

    public void addVersionTypeManufacturerPackageType(String PackageType) {
        this.versionTypeManufacturerPackageType.get(versionTypeManufacturerPackageType.size() - 1).add(PackageType);
    }

    public void addVersionTypeManufacturerAmountInPackage(String AmountInPackage) {
        this.versionTypeManufacturerAmountInPackage.get(versionTypeManufacturerAmountInPackage.size() - 1).add(AmountInPackage);
    }

    public void addVersionTypeManufacturerPackageCost(String PackageCost) {
        this.versionTypeManufacturerPackageCost.get(versionTypeManufacturerPackageCost.size() - 1).add(PackageCost);
    }

    public void addVersionTypeManufacturerDosageValue(String DosageValue) {
        this.versionTypeManufacturerDosageValue.get(versionTypeManufacturerDosageValue.size() - 1).add(DosageValue);
    }

    public void addVersionTypeManufacturerDosageTimesPerPeriod(String DosageTimesPerPeriod) {
        this.versionTypeManufacturerDosageTimesPerPeriod.get(versionTypeManufacturerDosageTimesPerPeriod.size() - 1).add(DosageTimesPerPeriod);
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public int compare(Preparation o1, Preparation o2) {
        return o1.versionType.size() - o2.versionType.size();
    }
}
