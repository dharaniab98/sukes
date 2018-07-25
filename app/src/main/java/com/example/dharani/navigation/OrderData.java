package com.example.dharani.navigation;

public class OrderData {

    String service_name;
    String complaint_date;
    String status;


    public OrderData(String service_name, String complaint_date, String status ) {
        this.service_name=service_name;
        this.complaint_date=complaint_date;
        this.status=status;


    }

    public String getService_name() {
        return service_name;
    }

    public String getComplaint_date() {
        return complaint_date;
    }

    public String getStatus() {
        return status;
    }


}
