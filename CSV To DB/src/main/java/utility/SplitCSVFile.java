package utility;

import java.io.*;
import java.util.ArrayList;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.time.StopWatch.createStarted;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/19/2024, Friday
 **/
public class SplitCSVFile {
    private static final int CHUNK_SIZE = 5;
    private static final String CHUNK_FILE_PATH="src/main/resources/outputFiles/CSVFiles/ChunkFiles/";

    public static int splitRecordsFileIntoChunks(File recordsFile) throws IOException {
        var chunkFilesStopwatch = createStarted();
        var chunkFiles = new ArrayList<File>();

        try (
                var fileReader = new FileReader(recordsFile);
                var bufferedReader = new BufferedReader(fileReader)
        ) {
            // open initial file
            var currentFileRecordCount = 0;
            var currentFile = new File(format(CHUNK_FILE_PATH+"in_chunk_%d.csv", chunkFiles.size()));
            var currentFileWriter = new FileWriter(currentFile);
            var currentBufferedWriter = new BufferedWriter(currentFileWriter);
            var header = bufferedReader.readLine();
            var line = bufferedReader.readLine();


            while (nonNull(line)) {
                if (currentFileRecordCount==0) {
                    currentBufferedWriter.write(header);
                    currentBufferedWriter.newLine();
                }

                // dump line into chunk file
                currentBufferedWriter.write(line);
                currentBufferedWriter.newLine();

                currentFileRecordCount++;

                line = bufferedReader.readLine();

                if (nonNull(line) && currentFileRecordCount > CHUNK_SIZE-1) {
                    // open next file if we are at chunk limit and still reading full file
                    currentBufferedWriter.close();
                    currentFileWriter.close();

                    chunkFiles.add(currentFile);

                    currentFileRecordCount = 0;
                    currentFile = new File(format(CHUNK_FILE_PATH+"in_chunk_%d.csv", chunkFiles.size()));
                    currentFileWriter = new FileWriter(currentFile);
                    currentBufferedWriter = new BufferedWriter(currentFileWriter);


                } else if (isNull(line)) {
                    currentBufferedWriter.close();
                    currentFileWriter.close();
                    // ensure we add the last file to chunks list
                    chunkFiles.add(currentFile);
                }
            }
        }

        chunkFilesStopwatch.stop();

        System.out.printf(
                "Splitting file '%s' into '%d' chunks of (at most) 5 records each took %dms%n",
                recordsFile.getName(),
                chunkFiles.size(),
                chunkFilesStopwatch.getTime()
        );

        return chunkFiles.size();
    }
}
