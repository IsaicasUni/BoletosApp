package com.example.boletosapp;

import java.util.Objects;

public class Boletos {

    //ATRIBUTOS
    private int numBoleto;
    private String nomCliente;
    private String destino;
    private String tipoViaje;
    private double precio;
    private String fecha;

    //CONSTRUCTOR VACÍO
    public Boletos(){
        this.numBoleto = 0;
        this.nomCliente = "";
        this.destino = "";
        this.tipoViaje = "";
        this.precio = 0.0;
        this.fecha = "";
    }

    //CONSTRUCTOR DE PARÁMETROS
    public Boletos(int numBoleto, String nomCliente, String destino, String tipoViaje, double precio, String fecha){
        this.numBoleto = numBoleto;
        this.nomCliente = nomCliente;
        this.destino = destino;
        this.tipoViaje = tipoViaje;
        this.fecha = fecha;
    }

    //CONSTRUCTOR DE COPIA
    public Boletos(Boletos bole){
        this.numBoleto = bole.numBoleto;
        this.nomCliente = bole.nomCliente;
        this.destino = bole.destino;
        this.tipoViaje = bole.tipoViaje;
        this.fecha = bole.fecha;
    }

    public int getNumBoleto() {
        return numBoleto;
    }

    public void setNumBoleto(int numBoleto) {
        this.numBoleto = numBoleto;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double calcularSubtotal() {
        double subTotal= 0.0f;

        if(Objects.equals(this.tipoViaje, "Sencillo")){ subTotal = this.precio; }
        else if(Objects.equals(this.tipoViaje, "Doble")){ subTotal = this.precio * 1.80; }

        return subTotal;
    }

    public double calcularImpuesto() {
        double impuesto = 0.0f;
        impuesto = this.calcularSubtotal() * 0.16f;
        return impuesto;
    }

    public double calcularDescuento(int edad) {
        double descuento = 0.0f;

        if(edad>=60){
            descuento = this.calcularSubtotal() * 0.50f;
        }
        return descuento;
    }

    public double calcularTotal(int edad) {
        double total = 0.0f;
        total = (this.calcularSubtotal() - this.calcularDescuento(edad)) + this.calcularImpuesto();
        return total;
    }

}
