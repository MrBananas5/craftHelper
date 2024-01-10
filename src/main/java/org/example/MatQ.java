package org.example;



public class MatQ {
    private Object material;
    private float quantity;

    public MatQ(String material, float quantity) {
        this.material = material;
        this.quantity = quantity;
    }
    public MatQ(rawMaterial material, float quantity){
        this.material = material;
        this.quantity = quantity;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    public void setMaterial(rawMaterial material) {
        this.material = material;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Object getMaterial() {
        return material;
    }

    public String print(){
        if (material.getClass() == rawMaterial.class){return (quantity + " " + ((rawMaterial) material).name());}
        return(quantity + " " +material);
    }
    public void ceil(){
        System.out.println(Math.ceil(quantity) +" "+((rawMaterial) material).name());
    }
    public void add(float quantity) {
        this.quantity += quantity;
    }
}
