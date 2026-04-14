package ui;

import model.*;
import repository.*;
import service.*;

public class OrderUI {

    public static void main(String[] args) {

        Patient patient = new Patient(1, "Ana", "Lopez", "2000-05-10", "Female", "123456789", "Madrid");
        Physician physician = new Physician(1, "Carlos", "Perez", "Cardiology", "987654321", "carlos@hospital.com");

        OrderRepository repo = new OrderRepositoryDB();
        OrderService service = new OrderService(repo);

        LaboratoryOrder order = new LaboratoryOrder(
                1,
                patient,
                physician,
                "2024-04-14",
                "Pending"
        );

        service.addOrder(order);
        service.updateOrder(order);
        service.deleteOrder(1);
    }
}