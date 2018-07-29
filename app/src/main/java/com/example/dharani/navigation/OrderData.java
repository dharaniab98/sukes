package com.example.dharani.navigation;

public class OrderData implements java.io.Serializable{

    String order_id;
    String service_name;
    String service_type;
    String status;


    public OrderData(String service_name, String service_type, String status, String order_id ) {
        this.service_name=service_name;
        this.service_type=service_type;
        switch(status){
            case "1": this.status = "processing";break;
            case "2": this.status = "process for servicing";break;
            case "3": this.status = "serviced";break;
        }

        this.order_id=order_id;


    }

    public String getService_name() {
        return service_name;
    }

    public String getService_type() {
        return service_type;
    }

    public String getStatus()
    {
        return status;
    }

    public String getOrder_id() {
        return order_id;
    }
}
