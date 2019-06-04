package hbgm.module_dummies;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.entities.Patient;
import at.spengergasse.hbgm.entities.Series;
import at.spengergasse.hbgm.entities.Study;
import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import at.spengergasse.hbgm.module_interfaces.IPatientRepository;
import gui.Viewer;

import java.util.List;

public class DummyPatientRepository implements IPatientRepository {
    @Override
    public List<Patient> patients() throws Exception {
        return null;
    }

    @Override
    public void add(Patient p) throws Exception {

    }

    @Override
    public void remove(Patient p) throws Exception {

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
