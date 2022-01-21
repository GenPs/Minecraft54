package djankcraft.engine.graphics;

import djankcraft.engine.utils.Color;

public class Material{


    public Texture texture;
    public Texture3D texture3d;
    public Color color;


    public Material(Texture texture){
        this.texture=texture;
    }

    public Material(Texture3D texture3d){
        this.texture3d=texture3d;
    }

    public Material(Color color){
        this.color=new Color(color);
    }

    public Material(Material material){
        this.texture=material.texture;
        this.color=new Color(material.color);
    }


}
