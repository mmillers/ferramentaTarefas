/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Maximiller
 */
public class Sub {

    private String name;
    private String obs;
    private String status;

    public Sub() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
                //"Sub{" + "obs=" + obs + ", status=" + status + '}';
    }


    public String ChamaObs() {
        return "Observação: " + obs + "\n" + "Status: " + status;
    }
}
