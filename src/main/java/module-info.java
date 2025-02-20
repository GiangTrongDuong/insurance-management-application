module com.insurancecompany.insurancemanagementgroupproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires bcrypt;
    requires static lombok;

    opens com.insurancecompany.insurancemanagementgroupproject2 to javafx.fxml;
    opens com.insurancecompany.insurancemanagementgroupproject2.controller to javafx.fxml;

    exports com.insurancecompany.insurancemanagementgroupproject2;
    exports com.insurancecompany.insurancemanagementgroupproject2.controller;

    exports com.insurancecompany.insurancemanagementgroupproject2.model;
    opens com.insurancecompany.insurancemanagementgroupproject2.model to javafx.base, javafx.fxml;
    exports com.insurancecompany.insurancemanagementgroupproject2.view;
    opens com.insurancecompany.insurancemanagementgroupproject2.view to javafx.base, javafx.fxml;
    exports com.insurancecompany.insurancemanagementgroupproject2.controller.policyowner;
    opens com.insurancecompany.insurancemanagementgroupproject2.controller.policyowner to javafx.fxml;
    exports com.insurancecompany.insurancemanagementgroupproject2.controller.policyholder;
    opens com.insurancecompany.insurancemanagementgroupproject2.controller.policyholder to javafx.fxml;
    exports com.insurancecompany.insurancemanagementgroupproject2.controller.surveyor;
    opens com.insurancecompany.insurancemanagementgroupproject2.controller.surveyor to javafx.fxml;
}

