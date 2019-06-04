package hbgm.module_dummies;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.entities.Patient;
import at.spengergasse.hbgm.entities.Series;
import at.spengergasse.hbgm.entities.Study;
import gui.Viewer;
import hbgm.module_interfaces.Builder;
import hbgm.module_interfaces.IObservable;
import hbgm.module_interfaces.IObserver;
import hbgm.module_interfaces.IPatientBrowser;

import javax.swing.*;

public class DummyPatientBrowser implements IPatientBrowser {
    @Override
    public Patient selectedPatient() {
        return null;
    }

    @Override
    public Study selectedStudy() {
        return null;
    }

    @Override
    public Series selectedSeries() {
        return null;
    }

    @Override
    public Instance selectedInstance() {
        return null;
    }

    @Override
    public void configure(Builder builder) {

    }

    @Override
    public JComponent uiComponent() {
        return new JLabel("dummy patient browser");
    }

    @Override
    public void registerObserver(hbgm.module_interfaces.IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void changed(IObservable o) {

    }
}
