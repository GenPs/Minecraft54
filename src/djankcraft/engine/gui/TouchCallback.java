package djankcraft.engine.gui;

public interface TouchCallback{

    void touchOn(LayoutElement current);
    void touched(LayoutElement current);
    void touchOff(LayoutElement current);

}

