package at.spengergasse.hbgm.module_dummies;

import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IControlPanel;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;

import javax.swing.*;

public class DummyControlPanel implements IControlPanel {
    @Override
    public int getCenter() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getAlpha() {
        return 0;
    }

    @Override
    public JComponent uiComponent() {
        return new JLabel("dummy control panel");
    }

    @Override
    public void configure(Builder builder) {

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
