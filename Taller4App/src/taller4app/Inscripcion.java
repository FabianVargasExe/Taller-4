/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4app;

/**
 *
 * @author favya
 */
public class Inscripcion {
     
    private String Alias;
    private String CodigoAsig;

    public Inscripcion(String Alias, String CodigoAsig) {
        this.Alias = Alias;
        this.CodigoAsig = CodigoAsig;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public String getCodigoAsig() {
        return CodigoAsig;
    }

    public void setCodigoAsig(String CodigoAsig) {
        this.CodigoAsig = CodigoAsig;
    }     
}
