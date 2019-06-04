package at.spengergasse.hbgm.module_dummies;

import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IImagePanel;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import gui.Viewer;

import javax.swing.*;
import java.net.URL;

public class DummyImagePanel implements IImagePanel {
    @Override
    public void configure(Builder builder) {

    }

    @Override
    public JComponent uiComponent() {
        URL url = getClass().getResource("/homer.jpg");
        //return new JLabel(new ImageIcon(url));
        return new JLabel("dummy image panel");
    }

    @Override
    public void registerObserver(IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void changed(IObservable o) {

    }
}
