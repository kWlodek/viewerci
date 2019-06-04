package we_are_number_one;

import at.spengergasse.hbgm.data.Image;
import at.spengergasse.hbgm.ui.AbstractImagePanel;
import at.spengergasse.hbgm.ui.Viewer;
import at.spengergasse.hbgm.utils.IBaseComponent;
import at.spengergasse.hbgm.utils.IObserver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class RealImagePanel extends AbstractImagePanel implements MouseMotionListener, MouseListener, MouseWheelListener  {


    private Viewer viewer;
    private Image image;
    private BufferedImage buffimage;
    private Graphics graph;
    private BufferedImage test;
    private int width = 800;
    private int height = 800;
    private Point origin = new Point(width/2, height/2);
    private Point point;
    private int posx, posy;
    private Point last;
    private boolean dragging = false;
    private int x,y;
    private int cwidth;
    private int cheight;
    private JButton move = new JButton();
    private JButton zoom = new JButton();
    private JButton draw = new JButton();
    private boolean moving = false;
    private boolean zooming = false;
    private boolean drawing = false;
    private ArrayList<Point> points = new ArrayList<Point>();
    private JTextArea text = new JTextArea();

    public RealImagePanel (){
        new TitledBorder("Image Panel");
        this.addMouseMotionListener(this);
        addMouseMotionListener(this);

        this.addMouseListener(this);
        addMouseListener(this);
        this.addMouseWheelListener(this);
        addMouseWheelListener(this);

        move.setText("toggle moving");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(moving) {
                    moving = false;
                    move.setBackground(Color.red);
                    System.out.println("moving disabled");
                }
                else{
                    moving = true;
                    move.setBackground(Color.lightGray);
                    System.out.println("moving enabled");
                }
            }
        });


        zoom.setText("toggle zooming");
        zoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(zooming) {
                    zooming = false;
                    draw.setContentAreaFilled(true);
                    zoom.setBackground(Color.yellow);
                    System.out.println("zooming disabled");
                }
                else{
                    zooming = true;
                    draw.setContentAreaFilled(true);
                    zoom.setBackground(Color.lightGray);
                    System.out.println("zooming enabled");
                }
            }
        });
        draw.setText("toggle drawing");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawing) {
                    drawing = false;
                    draw.setContentAreaFilled(true);
                    draw.setBackground(Color.green);
                    System.out.println("drawing disabled");

                }
                else{
                    drawing = true;
                    draw.setBackground(Color.lightGray);
                    repaint();
                    System.out.println("drawing enabled");
                }
            }
        });
        draw.setVisible(true);
        move.setVisible(true);
        zoom.setVisible(true);
        draw.setSize(100,100);
        move.setSize(100,100);
        zoom.setSize(100,100);
        this.add(draw).setLocation(500,500);
        this.add(move).setLocation(50,50);
        this.add(zoom).setLocation(300,300);
        this.add(text).setLocation(800,800);
        text.setEditable(false);
        text.setSelectedTextColor(Color.red);

    }

    public void registerObserver(IObserver observer) {

    }

    public void removeObserver(IObserver observer) {

    }

    public void configure(Viewer viewer) {
        this.viewer = viewer;
        viewer.getControlPanel().registerObserver(this);
        viewer.getPatientBrowser().registerObserver(this);
    }

    public void changed(IBaseComponent c) {
        repaint();
    }

    @Override
    public Image getImage(){
        return this.image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        graph = g;
        super.paintComponent(graph);
        try {

            this.image = viewer.getPatientBrowser().selectedImage();
            if ((image != null)){
                this.buffimage = viewer.getPixelMapper().mapImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(buffimage != null) {
            int h = buffimage.getHeight();
            int w = buffimage.getWidth();

            if(zooming) {
                graph.drawImage(buffimage, posx, posy, width, height, this);
            }
            else {
                graph.drawImage(buffimage, posx, posy, this);
            }
            /*
            if(!mustDraw) return;
            Graphics2D g2 = (Graphics2D) g;
            g2.drawOval(x, y, cwidth, cheight);
            */
            if (drawing) {
                points = new ArrayList();
                System.out.println("Coordinates: " + x + ", " + y + " | " + "height: " + cheight + ", " + "cwidth: " + cwidth);
                graph.drawOval(x, y, cwidth, cheight);
                graph.setColor(Color.cyan);
            }
        }
    }
//test
    public void Test(){

    }
    public void setImage(BufferedImage buff){
        this.buffimage = buff;
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(moving) {
           point = e.getPoint();
      }
       if(drawing){
           dragging = true;
           cwidth = 0;
           cheight = 0;
           last = null;
           last = e.getPoint();
           x = (int) Math.round(last.getX());
           y = (int) Math.round(last.getY());
       }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        last = null;

        if(drawing){
            String txt = "";
            innercircle();
            double intensity = 0;
            short[] pixels = image.getPixelValues();
            int col = image.getColumns();
            for(int i = 0; i < points.size(); i++) {
                int pointx = (int) Math.round(points.get(i).getX());
                int pointy = (int) Math.round(points.get(i).getY());
                intensity += ((pixels[pointx*col + pointy]));
                //System.out.println("int: " + intensity);
            }
            txt = String.valueOf(intensity / points.size());
            text.setText(txt);
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(moving) {
            double psx = e.getX() - point.getX();
            double psy = e.getY() - point.getY();

            posx = (int) Math.round(psx);
            posy = (int) Math.round(psy);
            //point = new Point(posx, posy);

        }

        if(drawing){
            int dx = 0;
            int dy = 0;

                /*
                if(e.getX() > last.x){
                    dx = Math.abs(e.getX() - last.x);
                    cwidth += dx;
                }
                else{
                    dx = Math.abs(last.x - e.getX());
                    cwidth -= dx;
                }
                if(e.getY() > last.y){
                  dy  = Math.abs(e.getY() - last.y);
                    cheight += dy;
                }
                else{
                  dy = Math.abs(last.y - e.getY());
                  cheight -= dy;
                }

                */
                cheight += Math.abs(e.getX() - last.x);
                cwidth += Math.abs(e.getY() - last.y);
                if(cheight > cwidth){
                    cwidth = cheight;
                }
                else{
                    cheight = cwidth;
                }

            last = e.getPoint();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + ", "+ e.getY());

        //System.out.println("testmoved");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(zooming) {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                width += 50;
                height += 50;
            } else {
                width -= 50;
                height -= 50;
            }
            repaint();
        }
    }
    public boolean isInsideEllipse(Point point) {
        return new Ellipse2D.Float(x, y, width, height).contains(point);
    }

    private void innercircle(){
        Point middle = new Point( x + cwidth / 2, y + cheight / 2);
        int rad = cwidth / 2;
        for (int r = 0; r < image.getRows(); r++)
        {
            for (int c = 0; c < image.getColumns(); c++)
            {
                Point comp = new Point(r, c);
                double com = (comp.getX() - middle.getX()) * (comp.getX() - middle.getX()) + (comp.getY() - middle.getY()) * (comp.getY() - middle.getY());
                if(com < rad * rad){
                    //drin
                    points.add(comp);
                }
                else if(com > rad * rad){
                    //draußen
                    //lolololololol
                }
                else if( com == rad){
                    //am rand
                    points.add(comp);
                }
            }
        }
    }
}
