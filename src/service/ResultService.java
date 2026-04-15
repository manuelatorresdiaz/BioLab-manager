package service;

import model.OrderTest;
import model.LaboratoryOrder;
import java.util.List;
import java.util.ArrayList;

public class ResultService {

    /**
     * Feature 12: Enter test result
     * Feature 13: Validate test result
     * Feature 14: Detect critical values
     */
    public void enterAndValidateResult(OrderTest test, double value) {
        // Feature 12: Enter test result
        test.setResultValue(value);

        // Feature 14: Detect critical values (Se evalúa primero por seguridad)
        if (value <= test.getCriticalMin() || value >= test.getCriticalMax()) {
            test.setResultStatus("CRITICAL");
        } 
        // Feature 13: Validate test result (Compara contra rangos normales)
        else if (value < test.getMinValue() || value > test.getMaxValue()) {
            test.setResultStatus("ABNORMAL");
        } else {
            test.setResultStatus("NORMAL");
        }
    }

    /**
     * Feature 15: View test history
     * Recupera todos los exámenes realizados a un paciente específico.
     */
    public List<OrderTest> getPatientHistory(int patientId, List<LaboratoryOrder> allOrders) {
        List<OrderTest> history = new ArrayList<>();
        
        for (LaboratoryOrder order : allOrders) {
            // Verificamos si la orden pertenece al paciente usando el Getter
            if (order.getPatient().getPatientId() == patientId) {
                // Obtenemos la lista de tests de esa orden
                history.addAll(order.getTests());
            }
        }
        return history;
    }
}
