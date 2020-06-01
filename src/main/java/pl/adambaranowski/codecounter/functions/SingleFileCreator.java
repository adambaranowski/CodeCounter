package pl.adambaranowski.codecounter.functions;

import pl.adambaranowski.codecounter.model.SingleFile;

import java.io.File;

public interface SingleFileCreator {
    SingleFile createSingleFile(File file);

}
