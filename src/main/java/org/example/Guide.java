package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.example.rawMaterial.isRaw;

public class Guide {
    private final ArrayList<Material> materials;

    public Guide() {
        this.materials = new ArrayList<>();
        for (File file: Objects.requireNonNull(new File("materials").listFiles())){
            loadMaterial(file);
        }

    }
    public void newMaterial (Scanner scan)   {
        System.out.print("name: ");
        String name = scan.nextLine();
        System.out.print("created quantity: ");
        float Q = scan.nextFloat();
        System.out.println("\nRecipe Begin");
        String str = "AAA";
        ArrayList<MatQ> mqs= new ArrayList<>();
        do {
            scan.nextLine();
            System.out.print("Material name: ");
            str = scan.nextLine();
            System.out.print("Material Quantity: ");
            float i = scan.nextFloat() / Q;
            MatQ matQ;
            if ((rawMaterial.getMaterial(str) == null)) {matQ = new MatQ(str,i);}
            else {
             matQ = new MatQ(rawMaterial.getMaterial(str),i);}
            mqs.add(matQ);

        } while (!(str.equals("END") || str.equals("CANCEL")));
        scan.nextLine();
        mqs.remove(mqs.size()-1);
        Material material = new Material(name,new Recipe(Q,mqs.toArray(new MatQ[0])));
        if (str.equals("END")){
            saveMaterial(material);}
    }

    private void loadMaterial (File fi){
        try{
            Scanner file = new Scanner(fi);
            String name = file.nextLine();
            ArrayList<MatQ> quantities = new ArrayList<>();
            while (file.hasNextLine()){
                float f = file.nextFloat();

                String mat = file.nextLine();
                mat = mat.substring(1);
                if (isRaw(mat)){
                    quantities.add(new MatQ(rawMaterial.valueOf(mat),f));}
                else{quantities.add(new MatQ(mat,f));}
            }
            materials.add(new Material(name,new Recipe(1,quantities.toArray(new MatQ[0]))));
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write file");
            e.printStackTrace();
        }
    }
    private void saveMaterial(Material m)  {
        try { PrintWriter file = new PrintWriter( "materials/"+m.getName() +".txt");
        file.println(m.getName());
        for (MatQ mQ: m.getRecipe().getIn()) {
            file.println(mQ.print());
        }
        file.close();}
        catch (IOException e){
            System.out.println("Cannot write file");
            e.printStackTrace();
        }
        materials.add(m);
    }
    private Material find(String str){
        for (Material m: materials){
            if (m.getName().equals(str)){return m;}
        }
        return null;}
    private ArrayList<MatQ> getIngredients(Material itm){
        ArrayList<MatQ> raw = new ArrayList<>();
        for (MatQ m: itm.getRecipe().getIn()){
            Object mat = m.getMaterial();
            if (mat.getClass() == rawMaterial.class){
                for (int i = 0; i <  Math.ceil(m.getQuantity()); i++){
                    raw.add(m);
                }
            }else{
                for (int i = 0; i <  Math.ceil(m.getQuantity()); i++){
                raw.addAll(getIngredients(find((String) mat)));}
            }
        }
        return raw;
    }
    public Material getMaterial(Scanner scan){
        System.out.print("Material Name: ");
        String itmName = scan.nextLine();
        return find(itmName);
    }
    public void fullQuery(Scanner scan){
        Material itm = getMaterial(scan);
        if (itm == null) {System.out.println("Item not found");}
        else{
            boolean added;
            ArrayList<MatQ> raws= new ArrayList<>();
            for (MatQ mQ: getIngredients(itm)){
                added = false;
                for (MatQ rQ: raws){
                    if (rQ.getMaterial() == mQ.getMaterial()){
                        added = true;
                        rQ.add(mQ.getQuantity());}
                }
                if (!added){raws.add(mQ);}
            }
            for (MatQ rQ: raws){
                rQ.ceil();
            }
        }
        System.out.println();
    }
    public void query(Scanner scan) {
        Material itm = getMaterial(scan);
        if (itm == null) {System.out.println("Item not found");}
        else {
            System.out.println("query of: " + itm.getName());

            System.out.println("Ingredients:");
            for (MatQ m: itm.getRecipe().getIn()){
                System.out.println(m.print());
            }
        }
        System.out.println();
    }
    public void list(){
        for (Material m: materials){
            System.out.println(m.getName());
        }
    }
}
