package tech.ada.projeto_ada.logica_programacao.model;

public class Salary {
    private double salarioBruto;
    private double descontoInss;
    private double descontoImpostoDeRenda;
    private double salarioLiquido;

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

    public void calcularSalarioLiquido() {
        this.salarioLiquido = this.salarioBruto - this.descontoInss - this.descontoImpostoDeRenda;
    }
}
