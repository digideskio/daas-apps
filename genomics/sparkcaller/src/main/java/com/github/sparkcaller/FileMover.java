package com.github.sparkcaller;

import org.apache.spark.api.java.function.Function;
import java.io.File;

public class FileMover implements Function<File, File> {
    String targetPath;

    public FileMover(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public File call(File file) throws Exception {
        return Utils.moveToDir(file, this.targetPath);
    }
}
