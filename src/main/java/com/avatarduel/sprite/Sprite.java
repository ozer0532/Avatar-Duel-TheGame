import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite {
    private Image image;
    private double X, Y;
    private double W, H; // Width, Height
    private double PivotX, PivotY; // Posisi Scaling
    private double AnchorX, AnchorY; // Posisi relatif dari layar
    private double TargetX, TargetY; // Target posisi animasi
    private double TargetW, TargetH;
    private double TargetAnchorX, TargetAnchorY;

    public Sprite() {
        this.X=0;
        this.Y=0;
        this.W=0;
        this.H=0;
        this.PivotX=0;
        this.PivotY=0;
        this.AchorX=0;
        this.AchorY=0;
        this.TargetX=0;
        this.TargetY=0;
        this.TargetW=0;
        this.TargetH=0;
        this.TargetAnchorX=0;
        this.TargetAnchorY=0;
    }

    public Sprite(double x,double y, double w, double h, double pivX, double pivY, double achX, double achY, double tarX, double tarY, double tarW, double tarH, double taX, double taY) {
        this.X=x;
        this.Y=y;
        this.W=w;
        this.H=h;
        this.PivotX=pivX;
        this.PivotY=pivY;
        this.AchorX=achX;
        this.AchorY=achY;
        this.TargetX=tarX;
        this.TargetY=tarY;
        this.TargetW=tarW;
        this.TargetH=tarH;
        this.TargetAnchorX=taX;
        this.TargetAnchorY=taY;
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        this.image = i;
        this.W=i.getWidth();
        this.H=i.getHeight();
    }

    public void setPos(double X, double Y) {
        this.TargetX=X;
        this.TargetY=Y;
    }

    public void setSize(double W, double H) {
        this.TargetW=W;
        this.TargetH=H; 
    }

    public void setAchor(double X, double Y) {
        this.TargetAnchorX=X;
        this.TargetAnchorY=Y;
    }

    public void update() {
        //Update attributes closer to target
        //masi bingung
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, X, Y);
    }

    public boolean isPointOverlap(float x, float y){
        //Return true kalo titik X,Y menyentuh kartu
        if (((x>=this.X) && (x<=this.X+this.W)) && ((y>=this.Y)&&(y<=this.Y+this.Height))) {
            return true;
        }
        else {
            return false;
        }
    }


    
}