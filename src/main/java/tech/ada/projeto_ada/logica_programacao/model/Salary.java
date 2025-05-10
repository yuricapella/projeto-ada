package tech.ada.projeto_ada.logica_programacao.model;

public class Salary {
    private double salarioBruto;
    private double descontoInss;
    private double descontoImpostoDeRenda;
    private double salarioLiquido;
    private int numeroDependentes;

    private double inssPercentual;
    private double irrfPercentual;

    public Salary(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public double getDescontoInss() {
        return descontoInss;
    }

    public void setDescontoInss(double descontoInss) {
        this.descontoInss = descontoInss;
    }

    public double getDescontoImpostoDeRenda() {
        return descontoImpostoDeRenda;
    }

    public void setDescontoImpostoDeRenda(double descontoImpostoDeRenda) {
        this.descontoImpostoDeRenda = descontoImpostoDeRenda;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }


    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    public void setNumeroDependentes(int numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
    }

    public double getInssPercentual() {
        return inssPercentual;
    }

    public void setInssPercentual(double inssPercentual) {
        this.inssPercentual = inssPercentual;
    }

    public double getIrrfPercentual() {
        return irrfPercentual;
    }

    public void setIrrfPercentual(double irrfPercentual) {
        this.irrfPercentual = irrfPercentual;
    }
}
