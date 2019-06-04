package at.spengergasse.hbgm.module_dummies;

import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IInfoPanel;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import gui.Viewer;

import javax.swing.*;

public class DummyInfoPanel implements IInfoPanel {
    @Override
    public void configure(Builder builder) {

    }

    @Override
    public JComponent uiComponent() {
        return new JLabel("dummy info panel");
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
