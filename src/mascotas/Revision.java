package mascotas;

import java.time.LocalDate;

public class Revision {
    
    //propiedades
    private LocalDate fecha;
    private String motivo;
    
    //constructor
    public Revision(LocalDate fecha, String motivo) {
        this.fecha= fecha;
        this.motivo=motivo;
    }
    
    //no desarrollamos métodos get/set
    
    public String toString() {
        return String.format("Fecha de la Revisión:  %d-%s-%d   * Motivo:  %s"
                , this.fecha.getDayOfMonth(), this.fecha.getMonth(), this.fecha.getYear(), this.motivo);
    }

}
