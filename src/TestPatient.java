import repository.DBConnection;
import repository.PatientRepository;
import repository.PatientRepositoryDB;
import service.PatientService;
import ui.PatientUI;

public class TestPatient {
    public static void main(String[] args) {

        DBConnection dbConnection = new DBConnection();

        PatientRepository patientRepository = new PatientRepositoryDB(dbConnection);

        PatientService patientService = new PatientService(patientRepository);

        PatientUI patientUI = new PatientUI(patientService);

        patientUI.showMenu();
    }
}
