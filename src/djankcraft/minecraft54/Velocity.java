package djankcraft.minecraft54;

import djankcraft.engine.math.vectors.Vector3;

public class Velocity{


    private final Vector3 value;
    private float max;


    public Velocity(){
        value=new Vector3();
        this.max=1;
    }


    public void collidedAxesToZero(Vector3 collidedMovement){
        if(value.x!=0 && collidedMovement.x==0)
            value.x=0;

        if(value.y!=0 && collidedMovement.y==0)
            value.y=0;

        if(value.z!=0 && collidedMovement.z==0)
            value.z=0;
    }

    public void clamp(){
        Vector3 nor=value.clone().nor().module();
        if(value.x>max*nor.x)
            value.x=max*nor.x;
        else if(value.x<-max*nor.x)
            value.x=-max*nor.x;

        if(value.y>max*nor.y)
            value.y=max*nor.y;
        else if(value.y<-max*nor.y)
            value.y=-max*nor.y;

        if(value.z>max*nor.z)
            value.z=max*nor.z;
        else if(value.z<-max*nor.z)
            value.z=-max*nor.z;
    }

    public void reduce(float reduce){
        Vector3 nor=value.clone().nor().module();

        float r=reduce*nor.x;
        if(value.x>0){
            if(value.x>=r)
                value.x-=r;
            else
                value.x=0;
        }else if(value.x<0){
            if(value.x<=-r)
                value.x+=r;
            else
                value.x=0;
        }

        r=reduce*nor.y;
        if(value.y>0){
            if(value.y>=r)
                value.y-=r;
            else
                value.y=0;
        }else if(value.y<0){
            if(value.y<=-r)
                value.y+=r;
            else
                value.y=0;
        }

        r=reduce*nor.z;
        if(value.z>0){
            if(value.z>=r)
                value.z-=r;
            else
                value.z=0;
        }else if(value.z<0){
            if(value.z<=-r)
                value.z+=r;
            else
                value.z=0;
        }
    }


    public void add(Vector3 v){
        value.add(v);
    }
    public void add(float x,float y,float z){
        value.add(x,y,z);
    }
    public void add(double x,double y,double z){
        value.add(x,y,z);
    }


    public Vector3 get(){
        return value;
    }

    public float max(){
        return max;
    }

    public void setMax(float max){
        this.max=max;
    }


}
