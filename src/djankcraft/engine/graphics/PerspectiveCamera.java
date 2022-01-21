package djankcraft.engine.graphics;

import djankcraft.engine.math.EulerAngle;
import djankcraft.engine.math.Maths;
import djankcraft.engine.math.Matrix4;
import djankcraft.engine.math.Quaternion;
import djankcraft.engine.math.vectors.Vector3;

public class PerspectiveCamera{


    private Vector3 position;
    private EulerAngle rotation;
    private Matrix4 projection,view;

    private int width,height;
    private float field_of_view,near,far;


    public PerspectiveCamera(int width,int height,float near,float far,float field_of_view){
        this.width=width;
        this.height=height;
        this.near=near;
        this.far=far;
        this.field_of_view=field_of_view;

        position=new Vector3();
        rotation=new EulerAngle();

        update();
    }

    public void update(){
        projection=new Matrix4().setToPerspectiveSphere(width,height,near,far,field_of_view);

        Matrix4 translationMatrix=new Matrix4().translate(position.clone().mul(-1));

        Quaternion q1=new Quaternion().setEulerAngles(0,rotation.getYaw(),0);
        Quaternion q2=new Quaternion().setEulerAngles(rotation.getPitch(),0,0);
        Quaternion q3=new Quaternion().setEulerAngles(0,0,rotation.getRoll());
        Matrix4 rotationMatrix=q3.mul(q1.mul(q2)).toMatrix();

        view=Matrix4.mul(rotationMatrix,translationMatrix);
    }


    public void move(float x,float y,float z){
        position.add(x,y,z);
    }
    public void moveY(float y){
        position.y+=y;
    }

    public Vector3 getDefaultMove(float camX,float y,float camZ){
        return new Vector3(
                camX*Maths.cos((rotation.getPitch()+90)*Maths.toRadians) + camZ*Maths.cos(rotation.getPitch()*Maths.toRadians),
                y,
                camX*Maths.sin((rotation.getPitch()+90)*Maths.toRadians) + camZ*Maths.sin(rotation.getPitch()*Maths.toRadians)
        );
    }

    public Vector3 getDefaultMove(Vector3 v){
        return getDefaultMove(v.x,v.y,v.z);
    }

    public void defaultMoveX(float value){
        position.add(
                value*Maths.cos((rotation.getPitch()+90)*Maths.toRadians),
                0,
                value*Maths.sin((rotation.getPitch()+90)*Maths.toRadians)
        );
    }
    public void defaultMoveZ(float value){
        position.add(
                value*Maths.cos(rotation.getPitch()*Maths.toRadians),
                0,
                value*Maths.sin(rotation.getPitch()*Maths.toRadians)
        );
    }

    public void moveAlongCamX(float value){
        position.add(
                value*Maths.cos((rotation.getPitch()+90)*Maths.toRadians)*Maths.cos(rotation.getYaw()*Maths.toRadians),
                value*Maths.sin(rotation.getYaw()*Maths.toRadians),
                value*Maths.sin((rotation.getPitch()+90)*Maths.toRadians)*Maths.cos(rotation.getYaw()*Maths.toRadians)
        );
    }
    /*public void moveAlongCamY(float value){
        position.add(
                value*Maths.sin((rotation.getPitch())*Maths.toRadians)*Maths.sin((rotation.getYaw())*Maths.toRadians)*Maths.sin((rotation.getRoll())*Maths.toRadians)+value*Maths.sin((rotation.getRoll())*Maths.toRadians),
                value*Maths.cos((rotation.getRoll())*Maths.toRadians)*Maths.cos((rotation.getYaw())*Maths.toRadians),
                -value*Maths.cos((rotation.getPitch())*Maths.toRadians)*Maths.sin((rotation.getYaw())*Maths.toRadians)*Maths.cos((rotation.getRoll())*Maths.toRadians)+value*Maths.cos((rotation.getRoll())*Maths.toRadians)
        );
    }*/
    public void moveAlongCamZ(float value){
        position.add(
                value*Maths.cos(rotation.getPitch()*Maths.toRadians)*Maths.cos(rotation.getRoll()*Maths.toRadians),
                value*Maths.cos((rotation.getRoll()+90)*Maths.toRadians),
                value*Maths.sin(rotation.getPitch()*Maths.toRadians)*Maths.cos(rotation.getRoll()*Maths.toRadians)
        );
    }


    public Vector3 getDirection(){
        return new Vector3(
                Math.sin(Maths.toRadians*(rotation.getPitch()+180))*Math.cos(Maths.toRadians*rotation.getYaw()),
                Math.sin(Maths.toRadians*rotation.getYaw()),
                -Math.cos(Maths.toRadians*(rotation.getPitch()+180))*Math.cos(Maths.toRadians*rotation.getYaw())
        );
    }

    public void resize(int width,int height){
        this.width=width;
        this.height=height;
    }

    public Matrix4 getView(){
        return view;
    }
    public Matrix4 getProjection(){
        return projection;
    }

    public Vector3 getPosition(){
        return position;
    }
    public void setPosition(Vector3 position){
        this.position=position;
    }
    public void translate(float x,float y,float z){
        position.add(x,y,z);
    }

    public EulerAngle getRotation(){
        return rotation;
    }
    public void setRotation(EulerAngle rotation){
        this.rotation=rotation;
    }
    public void setRotation(float yaw,float pitch,float roll){
        this.rotation.setAngles(pitch,yaw,roll);
    }
    public void rotate(float x,float y,float z){
        this.rotation.rotateAngles(x,y,z);
    }

    public float getNear(){
        return near;
    }
    public void setNear(float near){
        this.near=near;
    }

    public void setFar(float far){
        this.far=far;
    }
    public float getFar(){
        return far;
    }

    public float getFOV(){
        return field_of_view;
    }
    public void setFOV(float fov){
        this.field_of_view=fov;
    }


}
