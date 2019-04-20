package gt.edu.umg.ingenieria.sistemas.laboratorio1.model;

import java.io.Serializable;

public class ErroresModel implements Serializable {
    private String name;
    private String message;

    public ErroresModel(String name, String message){
        this.name=name;
        this.message=message;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public String getMsj() {
        return message;
    }

    public void setMsj(String message) {
        this.message = message;
    }
}
