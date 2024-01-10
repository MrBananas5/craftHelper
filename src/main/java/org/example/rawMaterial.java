package org.example;

public enum rawMaterial {
    //common
    COBBLESTONE("cobStone"), SAND("sand"),WOOD("wood"), RESIN("resin"),CLAY("clay"), NETHERRACK("netherrack"),GRAVEL("gravel"),
    //ores
    IRON("irOre"),COPPER("coOre"),GOLD("goOre"),OSMIUM("osOre"),TIN("tiOre"), NICKEL("niOre"), LEAD("leOre"), ALUMINIUM("alOre"),
    //mined, non-ores
    REDSTONE("redstone"),DIAMOND("diamond"), COAL("coal"), LAPISLAZULI("lapis"),
    //other
    LAVA("lava"), WATER("water");

    private final String name;

    rawMaterial(String name) {
        this.name = name;
    }

    static rawMaterial getMaterial(String name) {
        for (rawMaterial i: rawMaterial.values()){
            if (name.equals(i.name)) {return i;}
        }
        return null;
    }
    static boolean isRaw(String n){
        for (rawMaterial i: rawMaterial.values()){
            if(String.valueOf(i).equals(n)){return true;}
        }
        return false;
    }
}
