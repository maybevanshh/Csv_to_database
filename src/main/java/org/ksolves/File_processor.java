package org.ksolves;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class File_processor {
    private static final Logger log = LogManager.getLogger(File_processor.class);
    public void file_process(File file) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CSVReader csvReader = new CSVReader(new FileReader(file));
        log.info("File about to read by csv");
        csvReader.skip(1);

        try{
            Stream<String[]> stream= csvReader.readAll().parallelStream();
            stream.forEach(line->executorService.submit(new Filereader(line)));
            executorService.shutdown();
            csvReader.close();

        }catch (Exception e){
            log.error("Error in file processing stream. \n");
        }
    }
}
