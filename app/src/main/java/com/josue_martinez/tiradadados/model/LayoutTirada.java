package com.josue_martinez.tiradadados.model;

import com.josue_martinez.tiradadados.app.MyApplication;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

//Esta es la clase en la que se basa el item_list y la que guardaré en el realm
public class LayoutTirada extends RealmObject {
    @PrimaryKey
    private int id;

    @Required
    public String tipoDado;
    @Required
    public Integer tirada1;
    @Required
    public Integer tirada2;
    @Required
    public Integer tirada3;
    @Required
    public Integer tirada4;

    public LayoutTirada(){}
    public LayoutTirada(String tipoDado, Integer tirada1, Integer tirada2, Integer tirada3, Integer tirada4) {
        this.id = MyApplication.tiradaId.incrementAndGet();
        this.tipoDado = tipoDado;
        this.tirada1 = tirada1;
        this.tirada2 = tirada2;
        this.tirada3 = tirada3;
        this.tirada4 = tirada4;
    }

    //Getters y Setters

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public Integer getTirada1() {
        return tirada1;
    }

    public void setTirada1(Integer tirada1) {
        this.tirada1 = tirada1;
    }

    public Integer getTirada2() {
        return tirada2;
    }

    public void setTirada2(Integer tirada2) {
        this.tirada2 = tirada2;
    }

    public Integer getTirada3() {
        return tirada3;
    }

    public void setTirada3(Integer tirada3) {
        this.tirada3 = tirada3;
    }

    public Integer getTirada4() {
        return tirada4;
    }

    public void setTirada4(Integer tirada4) {
        this.tirada4 = tirada4;
    }

    public Integer totalTirada(){
        return (tirada1+tirada2+tirada3+tirada4);
    }
}
