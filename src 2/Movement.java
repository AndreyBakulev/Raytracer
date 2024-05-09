import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
//UNUSED CLASS DONT EVEN LOOK AT THIS
public class Movement implements MouseListener, MouseMotionListener {
    private int X,Y;

    public Movement(Component...pns){
        for(Component panel : pns){
            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);
        }


    }
    @Override
    public void mouseDragged(MouseEvent e) {
        e.getComponent().setLocation((e.getX()+e.getComponent().getX()-X),(e.getY()+e.getComponent().getY()-Y));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    
}
