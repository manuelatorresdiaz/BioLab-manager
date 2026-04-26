package repository;

import model.OrderTest;
import java.util.List;

public interface ResultRepository {
	void saveResult (OrderTest ot);
	List<OrderTest> findByPatientId (int patientId);
}
