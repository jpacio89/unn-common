package com.unn.common.boosting;

import com.unn.common.dataset.Dataset;
import com.unn.common.utils.RandomManager;
import com.unn.common.utils.Serializer;
import com.unn.common.utils.Utils;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Archive implements Serializable {
    private static Archive instance;

    UUID uuid;
    String folderPath;

    ArrayList<String> memory = new ArrayList<>();

    public Archive() {
        if (TuringConfig.get().ARCHIVE_ID != null) {
            this.uuid = UUID.fromString(TuringConfig.get().ARCHIVE_ID);
        } else {
            this.uuid = UUID.randomUUID();
        }
        this.folderPath = String.format("%s/archives/%s",
                TuringConfig.get().BASE_PATH, uuid.toString());
    }

    public Archive(String _uuid) {
        this.uuid = UUID.fromString(_uuid);
        this.folderPath = String.format("%s/archives/%s",
                TuringConfig.get().BASE_PATH, uuid.toString());
    }

    public static Archive get() {
        if (instance == null) {
            instance = new Archive();
            instance.init();
        }
        return instance;
    }

    public void init() {
        File theDir = new File(folderPath);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        String iniPath = String.format("%s/%s", folderPath, "_archive");
        File ini = new File(String.format("%s.v1.conf", iniPath));
        if (ini.exists()) {
            this.preload();
            return;
        }
        Serializer.write(TuringConfig.get(), iniPath, "conf");
    }

    public void save(String program, Dataset dataset,
                     Dataset transform, ArrayList<Integer> validFeatureIndexes) {
        String hash = this.getHashByProgram(program);
        if (hash == null) {
            return;
        }
        Serializer.write(
                new ArchiveRecord(program, dataset, transform, validFeatureIndexes),
                String.format("%s/%s", folderPath, hash),
                "booster");
    }

    public void preload() {
        File archiveDir = new File(this.folderPath);
        File[] files = archiveDir.listFiles((File dir, String name) ->
                name.endsWith(".booster"));
        this.memory.clear();
        Arrays.stream(files)
                .forEach(file -> this.memory.add(
                        file.getName().split("\\.")[0]));
    }

    public boolean hasProgram(String program) {
        String hash = this.getHashByProgram(program);
        if (hash == null) {
            return false;
        }
        return this.memory.contains(hash);
    }

    public String getHashByProgram(String program) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] encodedhash = digest.digest(
                program.getBytes(StandardCharsets.UTF_8));
        String hash = Utils.bytesToHex(encodedhash);
        return hash;
    }

    public ArchiveRecord getRandomProgram() {
        String random = RandomManager.getOne(this.memory);
        ArchiveRecord record = (ArchiveRecord) Serializer.read(
            String.format("%s/%s", folderPath, random),
            "booster");
        return record;
    }

    public class ArchiveRecord implements Serializable {
        String program;
        Dataset dataset;
        Dataset transform;
        ArrayList<Integer> validFeatureIndexes;

        public ArchiveRecord(String _program, Dataset _dataset,
                             Dataset _transform, ArrayList<Integer> _validFeatureIndexes) {
            this.program = _program;
            this.dataset = _dataset;
            this.transform = _transform;
            this.validFeatureIndexes = _validFeatureIndexes;
        }

        public String getProgram() {
            return program;
        }

        public ArrayList<Integer> getValidFeatureIndexes() {
            return validFeatureIndexes;
        }
    }
}
