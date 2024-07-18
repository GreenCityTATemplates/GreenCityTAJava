package com.ita.edu.greencity.tests.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GroupedLoggingAppender extends AppenderSkeleton implements IReporter {
    public static final String ext = ".threadlog.txt";
    private final ConcurrentHashMap<Long, BufferedWriter> tid2file = new ConcurrentHashMap<Long, BufferedWriter>();
    private final String outputDir;
    private final String outputFile;

    public GroupedLoggingAppender() {
        setLayout(new PatternLayout("%d{ABSOLUTE} %5p %c{1}:%L - %m%n"));
        //TODO this layout isn't applied. Apply it so that time is logged!
        String outdir = System.getProperty("outputdir");
        if (!outdir.endsWith("/"))
            outdir += "/";
        outputDir = outdir;

        outputFile = outputDir + "output.log.grouped.txt";
        try {
            if (outputDir != null) {
                Files.deleteIfExists(FileSystems.getDefault().getPath(outputFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        System.out.println("Reporter getting called! " + outputDir);
        // we don't do any report generation here, just clean up the log files
        mergeLogFiles();
    }

    @Override
    public void close() {
    }

    private void mergeLogFiles() {
        try {
            File file = new File(outputDir);
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(ext);
                }
            });

            List<Path> paths = new ArrayList<Path>();
            for (File f : files) {
                Path path = f.toPath();
                paths.add(path);
            }
            Collections.sort(paths);
            Path pathAll = FileSystems.getDefault().getPath(outputFile);
            for (Path path : paths) {
                Files.write(pathAll, Files.readAllBytes(path), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void append(LoggingEvent event) {
        if (outputDir == null)
            return; // by default nothing appended, comments on top
        try {
            long tid = Thread.currentThread().getId();
            BufferedWriter fw = tid2file.get(tid);
            if (fw == null) {
                fw = new BufferedWriter(new FileWriter(getFileNameFromThreadID(tid)));
                tid2file.put(tid, fw);
            }
            fw.write(event.getMessage().toString());
            fw.write("\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getFileNameFromThreadID(long tid) {
        return String.format("%sthread_output_%04d%s", outputDir, tid, ext);
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
