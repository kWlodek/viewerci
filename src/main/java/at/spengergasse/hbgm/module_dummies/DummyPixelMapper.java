package at.spengergasse.hbgm.module_dummies;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import at.spengergasse.hbgm.module_interfaces.IPixelMapper;
import gui.Viewer;

import java.awt.image.BufferedImage;

public class DummyPixelMapper implements IPixelMapper {
    @Override
    public BufferedImage map(Instance instance) throws Exception {
        return null;
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
