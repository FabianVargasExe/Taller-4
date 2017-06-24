


package taller4app;


public class RegistroError {
    
    private String Categoria;
    private String Campo;
    private String ValorCampo;
    private String tipo;

    public RegistroError(String Categoria, String Campo, String ValorCampo, String tipo) {
        this.Categoria = Categoria;
        this.Campo = Campo;
        this.ValorCampo = ValorCampo;
        this.tipo = tipo;

    }
    public String errorEncontrato(){
        
        if (tipo.equals("1")){
            tipo = "El rut no es valido";
        }
        if (tipo.equals("2")){
            tipo = "El codigo no es numerico";
        }
        if (tipo.equals("3")){
            tipo = "El Correo no es valido";
        }
        if (tipo.equals("4")){
            tipo = "El rut no es valido (No es numerico)";
        }
        if (tipo.equals("5")){
            tipo = "El codigo de asig. no es valido";
        }
        if (tipo.equals("6")){
            tipo = "El nombre no es valido (contiene numeros)";
        }
        if (tipo.equals("7")){
            tipo = "El apellido no es valido (contiene numeros)";
        }
        if (tipo.equals("9")){
            tipo = "El tipo no es valido (1 = Alum // 2 = Prof)";
        }
        return tipo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String Campo) {
        this.Campo = Campo;
    }

    public String getValorCampo() {
        return ValorCampo;
    }

    public void setValorCampo(String ValorCampo) {
        this.ValorCampo = ValorCampo;
    }
    
    
    
}
