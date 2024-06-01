package ec.edu.uce.ProyectoJuego.model;


public abstract class Role {
    private int [] coordX;
    private int [] coordY;

    public Role() {
    }

    public Role(int size) {
        this.coordX = new int[size];
        this.coordY = new int[size];
    }

    public Role(int[] coordX, int[] coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public int[] getCoordX() {
        return coordX;
    }

    public void setCoordX(int[] coordX) {
        this.coordX = coordX;
    }

    public int[] getCoordY() {
        return coordY;
    }

    public void setCoordY(int[] coordY) {
        this.coordY = coordY;
    }
}
