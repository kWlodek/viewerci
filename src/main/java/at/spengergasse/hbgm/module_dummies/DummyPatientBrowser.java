package at.spengergasse.hbgm.module_dummies;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.entities.Patient;
import at.spengergasse.hbgm.entities.Series;
import at.spengergasse.hbgm.entities.Study;
import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import at.spengergasse.hbgm.module_interfaces.IPatientBrowser;
import gui.Viewer;

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
    public void registerObserver(IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void changed(IObservable o) {

    }
}
